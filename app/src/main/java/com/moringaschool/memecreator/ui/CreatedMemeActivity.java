package com.moringaschool.memecreator.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.moringaschool.memecreator.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreatedMemeActivity extends AppCompatActivity {
    @BindView(R.id.newMeme)
    ImageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.created_meme);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String newImageUrl = intent.getStringExtra("newImageUrl");

        Picasso.get().load(newImageUrl).into(mImageView);



    }


}
