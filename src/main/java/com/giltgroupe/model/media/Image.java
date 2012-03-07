package com.giltgroupe.model.media;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * TBD
 */
@JsonAutoDetect(JsonMethod.NONE)
public class Image {

    @JsonProperty("url")
    private String _url;

    @JsonProperty("width")
    private int _width;

    @JsonProperty("height")
    private int _height;

    public String getUrl() { return _url; }

    public void setUrl(String url) { _url = url; }

    public int getWidth() { return _width; }

    public void setWidth(int width) { _width = width; }

    public int getHeight() { return _height; }

    public void setHeight(int height) { _height = height; }
}