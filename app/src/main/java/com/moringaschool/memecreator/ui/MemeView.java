package com.moringaschool.memecreator.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.moringaschool.memecreator.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemeView extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meme_view);
//        ButterKnife.bind(this);

        Intent intent = getIntent();
        String meme = intent.getStringExtra("meme");
        String submittedBy = intent.getStringExtra("submittedBy");
    }
}
