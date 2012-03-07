package com.giltgroupe.model.product;

import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Data model for the SkuAttribute record. A SkuAttribute is essentially just a key/value pair
 */
@JsonAutoDetect(JsonMethod.NONE)
public class SkuAttribute {

    @JsonProperty("name")
    private String _name;

    @JsonProperty("value")
    private String _value;

    /**
     * @return Returns the name of the Sku Attribute - the key
     */
    public String getName() { return _name; }
    
    /**
     * @param name The new name of the SkuAttribute
     */
    public void setName(String name) { _name = name; }

    /**
     * @return Return the value of the Sku Attribute
     */
    public String getValue() { return _value; }

    /**
     * @param value The new value of the SkuAttribute
     */
    public void setValue(String value) { _value = value; }
}