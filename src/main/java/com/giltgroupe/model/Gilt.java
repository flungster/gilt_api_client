package com.giltgroupe.model;

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

    public Gilt() {
        // Disable failure if there are any attributes we're not aware of
        // TBD - Ideally - we should set a handler routine instead to determine what is throwing
        // the error
        _mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public void load(String url) {

        System.out.println("Loading Sale Data from " + url);
        try {
            URL giltAPIUrl = new URL(url);

            JsonNode rootNode = _mapper.readTree(giltAPIUrl);
            _activeSales = _mapper.readValue(rootNode, Sales.class);
            _activeSales.load(_mapper);
                    

        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException: " + e);
            // tbd
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            // tbd
        }
    }

    public Sales getActiveSales() {
        return _activeSales;
    }
}