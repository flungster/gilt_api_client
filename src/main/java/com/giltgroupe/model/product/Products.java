package com.giltgroupe.model.product;

import java.util.HashMap;
import java.util.Map;

/**
 * This is essentially a utility class to easily find all the Products referenced by all
 * active and upcoming Sales. 
 */
public class Products {
    private Map<Long, Product> _mapProducts = new HashMap<Long, Product>();
    
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
}