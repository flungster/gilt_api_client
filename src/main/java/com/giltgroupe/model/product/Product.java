package com.giltgroupe.model.product;

import com.giltgroupe.model.media.Image;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Model class for a Product record. 
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

    /**
     * @return Returns the sale name
     */
    public String getName() { return _name; }

    /**
     * @param name The new sale name
     */
    public void setName(String name) { _name = name; }

    /**
     * @return Returns the URL to retrieve the JSON information for the product
     */
    public String getProductJsonUrl() { return _productJsonUrl; }

    /**
     * @param productJsonUrl The new product url to fetch the JSON information from
     */
    public void setProductJsonUrl(String productJsonUrl) { _productJsonUrl = productJsonUrl; }

    /**
     * @return Return the product Id (actually the product look Id)
     */
    public Long getId() { return _id; }

    /**
     * @param id The new product Id (product look Id)
     */
    public void setId(Long id) { _id = id; }

    /**
     * @return Return the product brand
     */
    public String getBrand() { return _brand; }

    /**
     * @param brand The new product brand string to set to
     */
    public void setBrand(String brand) { _brand = brand; }
    
    /**
     * @return Return the ProductContent object for this product
     */
    public ProductContent getContent() { return _content; }

    /** 
     * @param content The new ProductContent object this product should have
     */
    public void setContent(ProductContent content) { _content = content; }

    /**
     * @return Return the product URL on Gilt.com
     */
    public String getProductUrl() { return _productUrl; }

    /**
     * @param productUrl The new product url this product should be using
     */
    public void setProductUrl(String productUrl) { _productUrl = productUrl; }

    /**
     * @return Return a Map of image size to a list of Image objects
     */
    public Map<String, List<Image>> getImageUrls() { return _imageUrls; }

    /**
     * @param imageUrls The new image size to list of images map to set to
     */
    public void setImageUrls(Map<String, List<Image>> imageUrls) { _imageUrls = imageUrls; }

    /**
     * @return Return a list of Sku objects
     */
    public List<Sku> getSkus() { return _skus; }

    /** 
     * @param skus Tnew list of Sku objects this product should have
     */
    public void setSkues(List<Sku> skus) { _skus = skus; }
  
}