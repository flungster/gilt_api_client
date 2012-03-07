package com.giltgroupe.model.sale;

import com.giltgroupe.model.media.Image;
import com.giltgroupe.model.product.Product;

import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;


/**
 * TBD
 */
@JsonAutoDetect(JsonMethod.NONE)
public class Sale {

    @JsonProperty("name")
    private String _name;

    @JsonProperty("sale")
    private String _saleJsonUrl;

    @JsonProperty("sale_key")
    private String _saleKey;

    @JsonProperty("store")
    private String _store;

    @JsonProperty("sale_url")
    private String _saleUrl; 

    @JsonProperty("begins")
    private Date _beginsAt;

    @JsonProperty("ends")
    private Date _endsAt;

    @JsonProperty("description")
    private String _description;

    @JsonProperty("products")
    List<String> _productJsonUrls = new ArrayList<String>();

    @JsonProperty("image_urls")
    Map<String, List<Image>> _imageUrls = new HashMap<String, List<Image>>();
    
    List<Product> _products = new ArrayList<Product>();

    public String getName() { return _name; }

    public void setName(String name) { _name = name; }

    public String getSaleJsonUrl() { return _saleJsonUrl; }

    public void setSaleJsonUrl(String saleJsonUrl) { _saleJsonUrl = saleJsonUrl; }

    public String getSaleKey() { return _saleKey; }

    public void setSaleKey(String saleKey) { _saleKey = saleKey; }

    public String getStore() { return _store; }

    public void setStore(String store) { _store = store; }

    public String getSaleUrl() { return _saleUrl; }

    public void setSaleUrl(String saleUrl) { _saleUrl = saleUrl; }
    
    public Date beginsAt() { return _beginsAt; }

    public void setBeginsAt(Date beginsAt) { _beginsAt = beginsAt; }
    
    public Date endsAt() { return _endsAt; }
    
    public void setEndsAt(Date endsAt) { _endsAt = endsAt; }

    public String getDescription() { return _description; }

    public void setDescription(String description) { _description = description; }

    public List<String> getProductJsonUrls() { return _productJsonUrls; }

    public Map<String, List<Image>> getImageUrls() { return _imageUrls; }

    public void loadProducts(ObjectMapper mapper) {
        try {
            for (String productJsonUrl : _productJsonUrls) {
                URL productUrl = new URL(productJsonUrl + "?apikey=5aa7344bd866dd8128b82bb868811442");
                System.out.println("Loading product from " + productJsonUrl);
                JsonNode rootNode = mapper.readTree(productUrl);
                //System.out.println("** PRODUCT" + rootNode.toString());
                Product product = mapper.readValue(rootNode, Product.class);
               
                _products.add(product);
                // TBD - also add it to the products object
            }
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException: " + e);
                // tbd
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            // tbd
        }
       
        
    }
}