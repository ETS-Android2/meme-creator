package com.moringaschool.memecreator.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.memecreator.Constants;
import com.moringaschool.memecreator.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreatedMemesActivity extends AppCompatActivity {
    @BindView(R.id.createdMemesRecyclerView)
    RecyclerView mCreatedMemesRecyclerView;


    private String newImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.created_memes);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        newImageUrl = intent.getStringExtra("newImageUrl");


        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_USERS);

    }




}
