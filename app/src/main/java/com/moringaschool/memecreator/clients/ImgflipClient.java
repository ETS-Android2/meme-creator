package com.moringaschool.memecreator.clients;

import com.moringaschool.memecreator.Constants;
import com.moringaschool.memecreator.interfaces.ImgflipAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImgflipClient {

    private static Retrofit retrofit = null;

    public static ImgflipAPI getClient() {
        if(retrofit == null) {


            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ImgflipAPI.class);
    }
}
