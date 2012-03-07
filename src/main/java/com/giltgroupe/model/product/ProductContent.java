package com.giltgroupe.model.product;

import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * TBD
 */
@JsonAutoDetect(JsonMethod.NONE)
public class ProductContent {

    @JsonProperty("description")
    private String _description;

    @JsonProperty("material")
    private String _material;

    @JsonProperty("origin")
    private String _origin;

    public String getDescription() { return _description; }

    public void setDescription(String description) { _description = description; }

    public String getMaterial() { return _material; }

    public void setMaterial(String material) { _material = material; }

    public String getOrigin() { return _origin; }

    public void setOrigin(String origin) { _origin = origin; }
}