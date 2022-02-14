package com.moringaschool.memecreator.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.memecreator.Constants;
import com.moringaschool.memecreator.R;
import com.moringaschool.memecreator.adapters.CreatedMemesRecyclerViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreatedMemesActivity extends AppCompatActivity {
    @BindView(R.id.createdMemesRecyclerView)
    RecyclerView mCreatedMemesRecyclerView;

    private ArrayList<String> mMemeNames;
    private ArrayList<String> mCreatedMemesUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.created_memes);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        mMemeNames = intent.getStringArrayListExtra("memeNames");
        mCreatedMemesUrl = intent.getStringArrayListExtra("createdMemesUrl");

        CreatedMemesRecyclerViewAdapter myAdapter = new CreatedMemesRecyclerViewAdapter(CreatedMemesActivity.this, mMemeNames, mCreatedMemesUrl);
        mCreatedMemesRecyclerView.setAdapter(myAdapter);
        mCreatedMemesRecyclerView.setLayoutManager(new LinearLayoutManager(CreatedMemesActivity.this));

    }




}
