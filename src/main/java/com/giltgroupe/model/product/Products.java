package com.giltgroupe.model.product;

import java.util.HashMap;
import java.util.Map;


public class Products {
    private Map<Long, Product> _mapProducts;
    
    /**
     * TBD - may want to return an optional here
     */
    public Product getProduct(Long id) {
        return _mapProducts.get(id);
    }

    public void addProduct(Product product) {
        _mapProducts.put(product.getId(), product);
    }
}