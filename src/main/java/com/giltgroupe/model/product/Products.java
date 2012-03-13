package com.giltgroupe.model.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is essentially a utility class to easily find all the Products referenced by all
 * active and upcoming Sales. 
 */
public class Products {
    private Map<Long, Product> _mapProducts = new ConcurrentHashMap<Long, Product>();
    

	public Map<Long, Product> getProducts() { return _mapProducts; }

    /**
     * @return Returns a Product given the Product ID else null
     * TBD - may want to return an optional here
     */
    public Product getProduct(Long id) {
        return _mapProducts.get(id);
    }

    /**
     * @param product The new product to add to the map cache
     */
    public void addProduct(Product product) {
        _mapProducts.put(product.getId(), product);
    }

    /**
     * This method performs a brute force method of finding matching products. It simply iterates
     * through the products and performs a regular expression match. 
     * Good enough for now - may want to consider caching the results into a WeakHashMap
     */
    public List<Product> findProductsByBrand(String brand) {
        Pattern pattern = Pattern.compile(".*" + brand.toLowerCase() + ".*");
        List<Product> products = new ArrayList<Product>();
        
        for (Map.Entry<Long, Product> entry : _mapProducts.entrySet()) {
            System.out.println("Looking at : " + entry.getValue().getProductUrl());
            Matcher matcher = pattern.matcher(entry.getValue().getBrand().toLowerCase());
            if (matcher.matches()) {
                products.add(entry.getValue());
                System.out.println("Match found");
            }
        }

        return products;
    }
}