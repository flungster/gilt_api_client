package com.giltgroupe.model.product;

import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Data model for a Sku record
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


    /**
     * @return Returns the Sku Id
     */
    public Long getId() { return _id; }

    /**
     * @param The new Id for the sku
     */
    public void setId(Long id) { _id = id; }

    /**
     * @return Returns the inventory status for this sku record
     */
    public String getInventoryStatus() { return _inventoryStatus; }

    /**
     * @param inventoryStatus Set the inventory status for this sku
     */
    public void setInventoryStatus(String inventoryStatus) { _inventoryStatus = inventoryStatus; }

    /**
     * @return Returns the MSRP for the sku
     */
    public String getMsrpPrice() { return _msrpPrice; }

    /**
     * @param The new MSRP for the sku
     */
    public void setMsrpPrice(String msrpPrice) { _msrpPrice = msrpPrice; }

    /**
     * @return Returns the Gilt sale price for the sku
     */
    public String getSalePrice() { return _salePrice; }

    /**
     * @param salePrice The new Gilt sale price for the sku
     */
    public void setSalePrice(String salePrice) { _salePrice = salePrice; }

    /**
     * @return Return the list of SkuAttribute objects for this sku
     */
    public List<SkuAttribute> getAttributes() { return _attributes; }

    /**
     * @param attributes The new list of SkuAttributes to set to for this Sku
     */
    public void setAttributes(List<SkuAttribute> attributes) { _attributes = attributes; }
}