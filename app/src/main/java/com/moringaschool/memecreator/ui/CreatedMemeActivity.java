package com.moringaschool.memecreator.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.moringaschool.memecreator.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreatedMemeActivity extends AppCompatActivity {
    @BindView(R.id.newMeme)
    ImageView mImageView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    private String newImageUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.created_meme);
        ButterKnife.bind(this);
        loading(true);

        Intent intent = getIntent();
        newImageUrl = intent.getStringExtra("newImageUrl");

        loadPicasso();

    }

    private void loading(Boolean loading) {
        if(loading) {
            mProgressBar.setVisibility(View.VISIBLE);
            mImageView.setVisibility(View.GONE);
        }
        else {
            mProgressBar.setVisibility(View.GONE);
            mImageView.setVisibility(View.VISIBLE);
        }
    }

    private void loadPicasso() {
        loading(false);
        Picasso.get().load(newImageUrl).into(mImageView);
    }


}
