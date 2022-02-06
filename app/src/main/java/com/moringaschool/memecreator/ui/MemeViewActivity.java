package com.moringaschool.memecreator.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.moringaschool.memecreator.Constants;
import com.moringaschool.memecreator.R;
import com.moringaschool.memecreator.clients.ImgflipClient;
import com.moringaschool.memecreator.interfaces.ImgflipAPI;
import com.moringaschool.memecreator.models.Data;
import com.moringaschool.memecreator.models.ImgflipMemePostResponse;
import com.moringaschool.memecreator.models.ImgflipMemeSearchResponse;
import com.moringaschool.memecreator.models.PostData;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemeViewActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.memeName) TextView mTextView;
    @BindView(R.id.memeImage)
    ImageView mImageView;
    @BindView(R.id.postMemeButton)
    Button mButton;
    @BindView(R.id.text0) EditText mEditText0;
    @BindView(R.id.text1) EditText mEditText1;

    ImgflipAPI imgflipAPI;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meme_view);
        ButterKnife.bind(this);

        imgflipAPI = ImgflipClient.getClient();

        mButton.setOnClickListener(this);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String imageUrl = intent.getStringExtra("imageUrl");

        mTextView.setText(name);
        Picasso.get().load(imageUrl).into(mImageView);

    }

    @Override
    public void onClick(View view) {
        if (view == mButton) {
            myPostRequest();
        }
    }

    private void myPostRequest() {

        if(mEditText0.equals("") || mEditText1.equals("")) {
            Toast.makeText(this, "Put some text first before you submit!", Toast.LENGTH_LONG).show();
        }

        else {
            Call<ImgflipMemePostResponse> call = imgflipAPI
                    .postMeme(getIntent().getStringExtra("imageId"), Constants.USERNAME, Constants.PASSWORD, mEditText0.getText().toString(), mEditText1.getText().toString());

            call.enqueue(new Callback<ImgflipMemePostResponse>() {
                @Override
                public void onResponse(Call<ImgflipMemePostResponse> call, Response<ImgflipMemePostResponse> response) {
                    Log.e("MY POST RESPONSE", response.raw().toString());
                    PostData postData = response.body().getData();


                }

                @Override
                public void onFailure(Call<ImgflipMemePostResponse> call, Throwable t) {
                    Log.e("MY POST RESPONSE", t.getMessage());

                }
            });
        }


    }
}
