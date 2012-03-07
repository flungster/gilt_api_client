package com.giltgroupe.model.product;

import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * TBD
 */
@JsonAutoDetect(JsonMethod.NONE)
public class Sku {

    @JsonProperty("id")
    private Long _id;

    @JsonProperty("inventory_status")
    private String _inventoryStatus;

    @JsonProperty("msrp_price")
    private String _msrpPrice;

    @JsonProperty("sale_price")
    private String _salePrice;

    @JsonProperty("attributes")
    private List<SkuAttribute> _attributes;


    public Long getId() { return _id; }

    public void setId(Long id) { _id = id; }

    public String getInventoryStatus() { return _inventoryStatus; }

    public void setInventoryStatus(String inventoryStatus) { _inventoryStatus = inventoryStatus; }

    public String getMsrpPrice() { return _msrpPrice; }

    public void setMsrpPrice(String msrpPrice) { _msrpPrice = msrpPrice; }

    public String getSalePrice() { return _salePrice; }

    public void setSalePrice(String salePrice) { _salePrice = salePrice; }

    public List<SkuAttribute> getAttributes() { return _attributes; }

    public void setAttributes(List<SkuAttribute> attributes) { _attributes = attributes; }
}