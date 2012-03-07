package com.giltgroupe.model.media;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Data model for an image record
 */
@JsonAutoDetect(JsonMethod.NONE)
public class Image {

    @JsonProperty("url")
    private String _url;

    @JsonProperty("width")
    private int _width;

    @JsonProperty("height")
    private int _height;

    /** 
     * @return Returns the image URL (the location where the image is served up - typically a CDN)
     */
    public String getUrl() { return _url; }

    /**
     * @param url The new url where the image is located at
     */
    public void setUrl(String url) { _url = url; }

    /**
     * @return Returns the width of the image
     */
    public int getWidth() { return _width; }

    public void setWidth(int width) { _width = width; }
    
    /**
     * @return Returns the height of the image
     */
    public int getHeight() { return _height; }

    public void setHeight(int height) { _height = height; }
}