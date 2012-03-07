package com.giltgroupe.model.sale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * This class is a container class for sales. 
 */
@JsonAutoDetect(JsonMethod.NONE)
public class Sales {

    @JsonProperty("sales")    
    private List<Sale> _sales;

    private Map<String, List<Sale>> _salesByStore = null;

    private Map<String, Sale> _salesBySaleKey = null;

    public List<Sale> getSaleList() { return _sales; }
    
    /**
     * This constructor will not only make sure the list of Sales is assigned, it will also
     * Make sure to bucket the sales into their respective maps - one for stores and one for sale keys
     */
    @JsonCreator
    public Sales(@JsonProperty("sales") List<Sale> sales) {
        _sales = sales;
        sortSalesByStore();
        sortSalesBySaleKey();
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

    /**
     * Take the list of Sales and insert them into a Map where the key is the sale key
     */
    private void sortSalesBySaleKey() {
        _salesBySaleKey = new HashMap<String, Sale>();
        for (Sale sale : _sales) {
            _salesBySaleKey.put(sale.getSaleKey(), sale);
        }
    }

    /**
     * TBD - improvement - return Optional
     */
    public Sale findSaleBySaleKey(String saleKey) {
        if (_salesBySaleKey != null) {
            return _salesBySaleKey.get(saleKey);
        } else {
            return null;
        }
     
    }

    /**
     * TBD
     */
    public List<Sale> findSalesByStore(String store) {
        if (_salesByStore != null) {
            return _salesByStore.get(store);
        } else { 
            return (new ArrayList<Sale>());
        }
    }
}
