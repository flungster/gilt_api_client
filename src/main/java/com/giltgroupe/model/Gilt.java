package com.giltgroupe.model;

import com.giltgroupe.model.product.Product;
import com.giltgroupe.model.product.Products;
import com.giltgroupe.model.sale.Sale;
import com.giltgroupe.model.sale.Sales;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * This is the primary class that manages all Sale and Product objects. Data is retrieved
 * via the Gilt Public API. The general usage is as follows:
 * 1. Create an instance of the Gilt class.
 * 2. Provide your API key via the setApiKey() method
 * 3. Call start()
 * Calling start() will kick off a scheduled task which will occur every 30 minutes to relaod 
 * all sale/product data. 
 */
public class Gilt {
   
    private static final ObjectMapper _mapper = new ObjectMapper();
    
    private Sales _activeSales = null;
    private Sales _upcomingSales = null;

    private Products _products = new Products();

    private static final String ACTIVE_SALES_URL = "https://api.gilt.com/v1/sales/active.json";
    private static final String UPCOMING_SALES_URL = "https://api.gilt.com/v1/sales/upcoming.json";

	private final ExecutorService _pool; 

    private static final int RELOAD_INTERVAL_IN_MIN = 30;
	private static final int THREAD_POOL_SIZE = 5;

    private String _apiKey = "";
   
    public Gilt() {
        /*
		 *Disable failure if there are any attributes we're not aware of
		 * TBD - Ideally - we should set a handler routine instead to determine what is throwing
		 * the error
		 */
        _mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		/**
		 * TBD
		 */
		_pool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    }

    public void setApiKey(String apiKey) {
        _apiKey = apiKey;
    }

    public String getApiKey() {
        return _apiKey;
    }

    /**
     * Connect to the Gilt Public API and fetch all current/upcoming sales information as well
     * as the products referenced in those sales
     */
    protected void fetchData() {       
        fetchSalesAndProducts();
    }

    public void start(boolean blocking) {
        int initDelay = 0;
        if (blocking) {
            initDelay = RELOAD_INTERVAL_IN_MIN;
            fetchData();

        }
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Reloader(), initDelay, RELOAD_INTERVAL_IN_MIN, TimeUnit.MINUTES);
    }

    /**
     * Fetch sales and product information from Gilt
     */
    private void fetchSalesAndProducts() {
        System.out.println("Beginning of fetch sales");
        try {
            /*
             * Fetch active and upcoming sales
             */
			System.out.println("-- fetching active sales"); 
            URL giltApiUrl = new URL(ACTIVE_SALES_URL + "?apikey=" + getApiKey());

            JsonNode rootNode = _mapper.readTree(giltApiUrl);
            Sales activeSales = _mapper.readValue(rootNode, Sales.class);

			System.out.println("-- fetching upcoming sales");
            giltApiUrl = new URL(UPCOMING_SALES_URL + "?apikey=" + getApiKey());
            
            rootNode = _mapper.readTree(giltApiUrl);
            Sales upcomingSales = _mapper.readValue(rootNode, Sales.class);

            /*
             * Fetch product data from active sales
			 * NOTE: there is no point in fetching products from upcoming sales
			 * because Gilt does not expose product information until the
			 * sale is live.
             */
			System.out.println("-- fetching products from active sales");
            List<Sale> saleList = activeSales.getSaleList();
            Products products = new Products();
			
			List<FetchProductCallable> fetchProductTasks = new ArrayList<FetchProductCallable>();
			
            for (Sale sale : saleList) {
                List<String> productJsonUrls = sale.getProductJsonUrls();

                for (String productJsonUrl : productJsonUrls) {
					FetchProductCallable fetchProduct = new FetchProductCallable(products, sale, productJsonUrl);
					fetchProductTasks.add(fetchProduct);
                }
            }
			try {

				List<Future<Void>> taskResuls = _pool.invokeAll(fetchProductTasks);
			} catch (InterruptedException e) {

			}
            /*
             * Swap caches
             */
			System.out.println("-- swapping caches");
            if (activeSales != null) {
                _activeSales = activeSales; 
            }

            if (upcomingSales != null) {
                _upcomingSales = upcomingSales; 
            }
			
			_products = products;
            
        } catch (MalformedURLException e) {
            //System.out.println("MalformedURLException: " + e);
            // tbd
        } catch (IOException e) {
            //System.out.println("IOException: " + e);
            // tbd
        }
        System.out.println("End of fetch sales");
    }

	protected void fetchProduct(Products products, Sale sale, String productJsonUrl) {
		try {
			ObjectMapper mapper = new ObjectMapper();

			URL productUrl = new URL(productJsonUrl + "?apikey=" + getApiKey());
			
			System.out.println("Fetching product: " + productUrl);
			JsonNode rootNode = mapper.readTree(productUrl);
			Product product = mapper.readValue(rootNode, Product.class);
                    
			sale.addProduct(product);
			products.addProduct(product);
		} catch (MalformedURLException e) {

		} catch (IOException e) {

		}
	}

    /**
     * @return Return a Sales object which is a container for the list of active sales 
     */
    public Sales getActiveSales() {
        return _activeSales;
    }

    /**
     * @return Return a Sales object which is a container for the list of upcoming sales
     */
    public Sales getUpcomingSales() {
        return _upcomingSales;
    }

    /**
     * @return Return the Products object which is a container for the list of all Products referenced by active/upcoming sales
     */
    public Products getProducts() {
        return _products;
    }

    /**
     * Reloads the sale and product data at a fixed interval
     */
    private class Reloader implements Runnable {
        public void run() {
            fetchData();
        }
    }


	private class FetchProductCallable implements Callable<Void> {
		private Sale _sale;
		private String _productJsonUrl;
		private Products _products;

		FetchProductCallable(Products products, Sale sale, String productJsonUrl) {
			_sale = sale;
			_productJsonUrl = productJsonUrl;
			_products = products;
		}

		public Void call() throws Exception {
			fetchProduct(_products, _sale, _productJsonUrl);
			return null;
		}

	}

}