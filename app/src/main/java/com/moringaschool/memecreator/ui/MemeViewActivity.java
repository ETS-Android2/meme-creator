package com.moringaschool.memecreator.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.moringaschool.memecreator.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemeViewActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.memeName) TextView mTextView;
    @BindView(R.id.memeImage)
    ImageView mImageView;
    @BindView(R.id.postMemeButton)
    Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meme_view);
        ButterKnife.bind(this);

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

        }
    }
}
