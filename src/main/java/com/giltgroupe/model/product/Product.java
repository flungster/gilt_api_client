package com.giltgroupe.model.product;

import com.giltgroupe.model.media.Image;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * TBD
 */
@JsonAutoDetect(JsonMethod.NONE)
public class Product {
    
    @JsonProperty("name")
    private String _name;
 
    @JsonProperty("product")
    private String _productJsonUrl;

    @JsonProperty("id")
    private Long _id;

    @JsonProperty("brand")
    private String _brand;

    @JsonProperty("content")
    private ProductContent _content;

    @JsonProperty("url")
    private String _productUrl;

    @JsonProperty("image_urls")
    private Map<String, List<Image>> _imageUrls;

    @JsonProperty("skus")
    private List<Sku> _skus;

    public String getName() { return _name; }

    public void setName(String name) { _name = name; }

    public String getProductJsonUrl() { return _productJsonUrl; }

    public void setProductJsonUrl(String productJsonUrl) { _productJsonUrl = productJsonUrl; }

    public Long getId() { return _id; }

    public void setId(Long id) { _id = id; }

    public String getBrand() { return _brand; }

    public void setBrand(String brand) { _brand = brand; }
    
    public ProductContent getContent() { return _content; }

    public void setContent(ProductContent content) { _content = content; }

    public String getProductUrl() { return _productUrl; }

    public void setProductUrl(String productUrl) { _productUrl = productUrl; }

    public Map<String, List<Image>> getImageUrls() { return _imageUrls; }

    public void setImageUrls(Map<String, List<Image>> imageUrls) { _imageUrls = imageUrls; }

    public List<Sku> getSkus() { return _skus; }

    public void setSkues(List<Sku> skus) { _skus = skus; }
  
}