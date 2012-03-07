package com.giltgroupe.model;

import com.giltgroupe.model.product.Product;
import com.giltgroupe.model.product.Products;
import com.giltgroupe.model.sale.Sale;
import com.giltgroupe.model.sale.Sales;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

public class Gilt {
   
    private static final ObjectMapper _mapper = new ObjectMapper();
    
    private Sales _activeSales = null;
    private Sales _upcomingSales = null;

    private Products _products = new Products();

    private static final String ACTIVE_SALES_URL = "https://api.gilt.com/v1/sales/women/active.json";
    private static final String UPCOMING_SALES_URL = "https://api.gilt.com/v1/sales/upcoming.json";
    private String _apiKey = "";
   
    public Gilt() {
        // Disable failure if there are any attributes we're not aware of
        // TBD - Ideally - we should set a handler routine instead to determine what is throwing
        // the error
        _mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public void setApiKey(String apiKey) {
        _apiKey = apiKey;
    }

    public String getApiKey() {
        return _apiKey;
    }

    public void init() {       
        fetchSales();
        fetchSaleProducts();
    }

    private void fetchSales() {

        try {
            URL giltAPIUrl = new URL(ACTIVE_SALES_URL + "?apikey=" + getApiKey());

            JsonNode rootNode = _mapper.readTree(giltAPIUrl);
            _activeSales = _mapper.readValue(rootNode, Sales.class);
            if (_activeSales == null) {
                System.out.println("Active Sales are null");
            }

        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException: " + e);
            // tbd
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            // tbd
        }
    }

    public void fetchSaleProducts() {
        try {
            List<Sale> saleList = _activeSales.getSaleList();
            
            for (Sale sale : saleList) {
                List<String> productJsonUrls = sale.getProductJsonUrls();
                for (String productJsonUrl : productJsonUrls) {
                    URL productUrl = new URL(productJsonUrl + "?apikey=" + getApiKey());
                    System.out.println("Loading product from " + productJsonUrl);
                    JsonNode rootNode = _mapper.readTree(productUrl);

                    Product product = _mapper.readValue(rootNode, Product.class);
                    
                    sale.addProduct(product);
                    _products.addProduct(product);
                }
            }
        } catch (MalformedURLException e) {
            // tbd
        } catch (IOException e) {
            // tbd
        }
    }

    public Sales getActiveSales() {
        return _activeSales;
    }

    public Products getProducts() {
        return _products;
    }
}