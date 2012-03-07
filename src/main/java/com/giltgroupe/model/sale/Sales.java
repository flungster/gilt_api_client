package com.giltgroupe.model.sale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;


/**
 * TBD
 */
@JsonAutoDetect(JsonMethod.NONE)
public class Sales {

    @JsonProperty("sales")
    private List<Sale> _sales;
    private Map<String, List<Sale>> _salesByStore = null;

    public List<Sale> getSaleList() { return _sales; }


    public void load(ObjectMapper mapper) {
        sortSalesByStore();
        loadSaleProducts(mapper);
    }

    /** 
     * Take the list of Sales and sort them into their respective collections by store
     */
    private void sortSalesByStore() {
        
        _salesByStore = new HashMap<String, List<Sale>>();
        for (Sale sale : _sales) {
            if (_salesByStore.get(sale.getStore()) == null) {
                _salesByStore.put(sale.getStore(), new ArrayList<Sale>());
            }
            
            _salesByStore.get(sale.getStore()).add(sale);
        }
    }

    private void loadSaleProducts(ObjectMapper mapper) {
        for (Sale sale : _sales) {
            System.out.println("SALE: " + sale.getSaleJsonUrl());
            sale.loadProducts(mapper);
        }
    }

}
