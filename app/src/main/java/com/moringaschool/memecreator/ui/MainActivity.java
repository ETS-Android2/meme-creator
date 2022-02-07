package com.moringaschool.memecreator.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
        replaceTitleFragment(new SimpleFragment());

        mButton.setOnClickListener(this);

        imgflipAPI = ImgflipClient.getClient();
    }

    @Override
    public void onClick(View view ) {
        if (view == mButton) {
            myRequest();
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

                if(meme.getBoxCount() > 2) {
                    meme = memes.get(random.nextInt(memes.size()));
                }

                name = meme.getName();
                imageUrl = meme.getUrl();
                String imageId = meme.getId();
                Log.e("MY MEME NAME", name);

                Intent intent = new Intent(MainActivity.this, MemeViewActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("imageUrl", imageUrl);
                intent.putExtra("imageId", imageId);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ImgflipMemeSearchResponse> call, Throwable t) {
                Log.e("MY RESPONSE", t.getMessage());
            }
        });
    }

    private void replaceTitleFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.welcomeTextLayout, fragment);
        fragmentTransaction.commit();

    }
}