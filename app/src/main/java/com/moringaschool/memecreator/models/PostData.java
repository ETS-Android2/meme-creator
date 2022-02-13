package com.moringaschool.memecreator.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostData {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("page_url")
    @Expose
    private String pageUrl;

    private String pushId;

    /**
     * No args constructor for use in serialization
     *
     */
    public PostData() {
    }

    /**
     *
     * @param pageUrl
     * @param url
     */
    public PostData(String url, String pageUrl) {
        super();
        this.url = url;
        this.pageUrl = pageUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
