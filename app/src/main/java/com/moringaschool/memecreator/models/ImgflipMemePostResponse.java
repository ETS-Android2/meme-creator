package com.moringaschool.memecreator.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImgflipMemePostResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private PostData data;

    /**
     * No args constructor for use in serialization
     *
     */
    public ImgflipMemePostResponse() {
    }

    /**
     *
     * @param data
     * @param success
     */
    public ImgflipMemePostResponse(Boolean success, PostData data) {
        super();
        this.success = success;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public PostData getData() {
        return data;
    }

    public void setData(PostData data) {
        this.data = data;
    }

}
