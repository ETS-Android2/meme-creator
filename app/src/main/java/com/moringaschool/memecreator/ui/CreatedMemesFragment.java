package com.moringaschool.memecreator.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

public class CreatedMemesFragment extends Fragment {
    @BindView(R.id.listView)
    ListView mListView;
    @BindView(R.id.progressBar2)
    ProgressBar mProgressBar;
    @BindView(R.id.textView6)
    TextView mTextView6;
    @BindView(R.id.textView8) TextView mTextView8;

    private ArrayList<String> mCreatedMemesUrl = new ArrayList<>();

    private FirebaseDatabase myDatabase;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;

    private String userId;

    private SharedPreferences mSharedPreferences;

    public CreatedMemesFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        mAuth = FirebaseAuth.getInstance();
        myDatabase = FirebaseDatabase.getInstance("https://meme-creator-462dc-default-rtdb.firebaseio.com/");
        myRef = myDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userId = user.getUid();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_memes_created, container, false);
        ButterKnife.bind(this, view);
        showProgressBar();

        String userName = mSharedPreferences.getString(Constants.SHARED_PREFERENCES_USER_NAME, null);
        if(userName != null) {
            String title = userName + " the memes you create appear here";
            mTextView8.setText(title);
        }
        else {
            mTextView8.setText("The memes you create shall appear here");
        }

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                showData(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(getActivity(), R.anim.listview_images_anim);
        mListView.setLayoutAnimation(controller);
        mListView.scheduleLayoutAnimation();

        return view;
    }

    private void showData(DataSnapshot snapshot) {
        for(DataSnapshot ds : snapshot.getChildren()) {
            DataSnapshot ds2 = ds.child(userId);
            for(DataSnapshot ds3 : ds2.getChildren()) {
                mCreatedMemesUrl.add(ds3.getValue(PostData.class).getUrl());
            }

            CreatedMemesListViewAdapter myAdapter = new CreatedMemesListViewAdapter(getActivity(), R.layout.created_meme, mCreatedMemesUrl);
            mListView.setAdapter(myAdapter);
            hideProgressBar();
        }
    }

    private void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
        mTextView6.setVisibility(View.VISIBLE);
        mTextView8.setVisibility(View.GONE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
        mTextView6.setVisibility(View.GONE);
        mTextView8.setVisibility(View.VISIBLE);
    }
}
