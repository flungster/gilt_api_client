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
 * TBD
 */
@JsonAutoDetect(JsonMethod.NONE)
public class Sales {

    @JsonProperty("sales")    
    private List<Sale> _sales;

    private Map<String, List<Sale>> _salesByStore = null;

    private Map<String, Sale> _salesBySaleKey = null;

    public List<Sale> getSaleList() { return _sales; }
    
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
        System.out.println("sorting sales by store");
        _salesByStore = new HashMap<String, List<Sale>>();
        for (Sale sale : _sales) {
            if (_salesByStore.get(sale.getStore()) == null) {
                _salesByStore.put(sale.getStore(), new ArrayList<Sale>());
            }
            
            _salesByStore.get(sale.getStore()).add(sale);
        }
    }

    /**
     * TBD
     */
    private void sortSalesBySaleKey() {
        System.out.println("sorting sales by sale key");
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
