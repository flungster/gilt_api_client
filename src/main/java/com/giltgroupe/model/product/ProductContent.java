package com.giltgroupe.model.product;

import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Model class for a ProductContent record
 */
@JsonAutoDetect(JsonMethod.NONE)
public class ProductContent {

    @JsonProperty("description")
    private String _description;

    @JsonProperty("material")
    private String _material;

    @JsonProperty("origin")
    private String _origin;

    /**
     * @return Returns the product description
     */
    public String getDescription() { return _description; }

    /**
     * @param description The new description for this product
     */
    public void setDescription(String description) { _description = description; }

    /** 
     * @return Returns the product material
     */
    public String getMaterial() { return _material; }

    /**
     * @param material The new material for this product
     */
    public void setMaterial(String material) { _material = material; }

    /**
     * @return Returns the product origin
     */
    public String getOrigin() { return _origin; }

    /**
     * @param origin The new origin for this product
     */
    public void setOrigin(String origin) { _origin = origin; }
}