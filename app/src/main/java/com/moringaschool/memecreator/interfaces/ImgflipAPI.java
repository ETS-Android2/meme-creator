package com.moringaschool.memecreator.interfaces;

import com.moringaschool.memecreator.models.Data;
import com.moringaschool.memecreator.models.ImgflipMemeSearchResponse;
import com.moringaschool.memecreator.models.Meme;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ImgflipAPI {
    @GET("get_memes")
    Call<ImgflipMemeSearchResponse> getMeme();
}
