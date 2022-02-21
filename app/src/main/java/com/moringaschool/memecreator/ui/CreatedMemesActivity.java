package com.moringaschool.memecreator.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.memecreator.Constants;
import com.moringaschool.memecreator.R;
import com.moringaschool.memecreator.adapters.CreatedMemesListViewAdapter;
import com.moringaschool.memecreator.models.PostData;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreatedMemesActivity extends AppCompatActivity {
//    @BindView(R.id.listView)
//    ListView mListView;
//    @BindView(R.id.progressBar2)
//    ProgressBar mProgressBar;
//    @BindView(R.id.textView6)
//    TextView mTextView6;
//    @BindView(R.id.textView8)
//    TextView mTextView8;
//
//    Intent intent;
//
//    private ArrayList<String> mMemeNames;
//    private ArrayList<String> mCreatedMemesUrl = new ArrayList<>();
//
//    private FirebaseDatabase myDatabase;
//    private DatabaseReference myRef;
//    private FirebaseAuth mAuth;
//
//    private String userId;
//
//    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memes_created);
    }
}

//        ButterKnife.bind(this);
//        showProgressBar();
//
//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(CreatedMemesActivity.this);
//        String userName = mSharedPreferences.getString(Constants.SHARED_PREFERENCES_USER_NAME, null);
//        if(userName != null) {
//            String title = userName + " the memes you create appear here";
//            mTextView8.setText(title);
//        }
//        else {
//            mTextView8.setText("The memes you create shall appear here");
//        }
//
//        intent = getIntent();
//
//        mAuth = FirebaseAuth.getInstance();
//        myDatabase = FirebaseDatabase.getInstance("https://meme-creator-462dc-default-rtdb.firebaseio.com/");
//        myRef = myDatabase.getReference();
//        FirebaseUser user = mAuth.getCurrentUser();
//        userId = user.getUid();
//
//        mMemeNames = intent.getStringArrayListExtra("memeNames");
//
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                showData(snapshot);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(this, R.anim.listview_images_anim);
//        mListView.setLayoutAnimation(controller);
//        mListView.scheduleLayoutAnimation();
//    }
//
//    private void showProgressBar() {
//        mProgressBar.setVisibility(View.VISIBLE);
//        mTextView6.setVisibility(View.VISIBLE);
//    }
//
//    private void showData(DataSnapshot snapshot) {
//        for(DataSnapshot ds : snapshot.getChildren()) {
//            DataSnapshot ds2 = ds.child(userId);
//            for(DataSnapshot ds3 : ds2.getChildren()) {
//                mCreatedMemesUrl.add(ds3.getValue(PostData.class).getUrl());
//            }
//
//            CreatedMemesListViewAdapter myAdapter = new CreatedMemesListViewAdapter(CreatedMemesActivity.this, R.layout.created_meme, mCreatedMemesUrl);
//            mListView.setAdapter(myAdapter);
//            hideProgressBar();
//        }
//    }
//
//    private void hideProgressBar() {
//        mProgressBar.setVisibility(View.GONE);
//        mTextView6.setVisibility(View.GONE);
//    }

