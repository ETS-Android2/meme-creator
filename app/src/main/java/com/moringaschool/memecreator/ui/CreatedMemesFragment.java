package com.moringaschool.memecreator.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.memecreator.R;

import java.util.ArrayList;

import butterknife.BindView;

public class CreatedMemesFragment extends Fragment {
    @BindView(R.id.listView)
    ListView mListView;
    @BindView(R.id.textView8) TextView mTextView8;

    Intent intent;

    private ArrayList<String> mMemeNames;
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

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
