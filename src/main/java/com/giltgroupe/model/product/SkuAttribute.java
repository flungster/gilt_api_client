package com.giltgroupe.model.product;

import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * TBD
 */
@JsonAutoDetect(JsonMethod.NONE)
public class SkuAttribute {

    @JsonProperty("name")
    private String _name;

    @JsonProperty("value")
    private String _value;

    public String getName() { return _name; }

    public void setName(String name) { _name = name; }

    public String getValue() { return _value; }

    public void setValue(String value) { _value = value; }
}