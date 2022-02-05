package com.moringaschool.memecreator.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.moringaschool.memecreator.R;
import com.moringaschool.memecreator.clients.ImgflipClient;
import com.moringaschool.memecreator.interfaces.ImgflipAPI;
import com.moringaschool.memecreator.models.Data;
import com.moringaschool.memecreator.models.ImgflipMemeSearchResponse;
import com.moringaschool.memecreator.models.Meme;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.button) Button mButton;
    private Data data;
    private List<Meme> memes;
    private Meme meme;
    private String name;
    private String imageUrl;

    ImgflipAPI imgflipAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mButton.setOnClickListener(this);

        imgflipAPI = ImgflipClient.getClient();
    }

    @Override
    public void onClick(View view ) {
        if (view == mButton) {
            myRequest();

//            Intent intent = new Intent(MainActivity.this, MemeView.class);
//            startActivity(intent);
        }
    }

    private void myRequest() {

        Call<ImgflipMemeSearchResponse> call = imgflipAPI.getMeme();

        call.enqueue(new Callback<ImgflipMemeSearchResponse>() {

            @Override
            public void onResponse(Call<ImgflipMemeSearchResponse> call, Response<ImgflipMemeSearchResponse> response) {
                Log.e("MY RESPONSE", response.raw().toString());
                data = response.body().getData();
                memes = data.getMemes();
                Random random = new Random();
                meme = memes.get(random.nextInt(memes.size()));
                name = meme.getName();
                imageUrl = meme.getUrl();
                Log.e("MY MEME NAME", name);
            }

            @Override
            public void onFailure(Call<ImgflipMemeSearchResponse> call, Throwable t) {
                Log.e("MY RESPONSE", t.getMessage());
            }
        });
    }
}