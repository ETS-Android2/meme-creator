package com.moringaschool.memecreator.interfaces;

import com.moringaschool.memecreator.models.Data;
import com.moringaschool.memecreator.models.ImgflipMemeSearchResponse;
import com.moringaschool.memecreator.models.Meme;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ImgflipAPI {
    @GET("get_memes")
    Call<ImgflipMemeSearchResponse> getMeme();

    @POST("caption_image")
    Call<ImgflipMemeSearchResponse> postMeme(@Query( "template_id") String templateId, @Body String username, @Body String password, @Query("text0") String text0, @Query("text1") String text1);
}
