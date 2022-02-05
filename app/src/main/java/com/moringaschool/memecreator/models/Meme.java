package com.moringaschool.memecreator.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meme {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("box_count")
    @Expose
    private Integer boxCount;

    /**
     * No args constructor for use in serialization
     *
     */
    public Meme() {
    }

    /**
     *
     * @param boxCount
     * @param name
     * @param width
     * @param id
     * @param url
     * @param height
     */
    public Meme(String id, String name, String url, Integer width, Integer height, Integer boxCount) {
        super();
        this.id = id;
        this.name = name;
        this.url = url;
        this.width = width;
        this.height = height;
        this.boxCount = boxCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getBoxCount() {
        return boxCount;
    }

    public void setBoxCount(Integer boxCount) {
        this.boxCount = boxCount;
    }

}
