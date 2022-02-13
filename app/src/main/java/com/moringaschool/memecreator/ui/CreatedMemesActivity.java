package com.moringaschool.memecreator.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.memecreator.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreatedMemesActivity extends AppCompatActivity {
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

        Intent intent = getIntent();
        newImageUrl = intent.getStringExtra("newImageUrl");

        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();


        loadPicasso();

    }

    private void loadPicasso() {
        Picasso.get().load(newImageUrl).into(mImageView);
    }


}
