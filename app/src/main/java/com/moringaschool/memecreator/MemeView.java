package com.moringaschool.memecreator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemeView extends AppCompatActivity {
    @BindView(R.id.viewMemeText)
    EditText mViewMemeText;
    @BindView(R.id.viewSubmittedByText) EditText mViewSubmittedByText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meme_view);
        ButterKnife.bind(this);

    }
}
