package com.moringaschool.memecreator.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.moringaschool.memecreator.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreatedMemeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meme_creator);
        ButterKnife.bind(this);



    }


}
