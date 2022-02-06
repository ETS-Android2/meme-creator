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

public class CreatedMemeActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.button2)
    Button mButton;
    @BindView(R.id.memeContent)
    EditText mMemeContent;
    @BindView(R.id.submittedBy) EditText mSubmittedBy;
    @BindView(R.id.memePhoto)
    ImageView mMemePhoto;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meme_creator);
        ButterKnife.bind(this);

        mButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == mButton) {
            String meme = mMemeContent.getText().toString();
            String submittedBy = mSubmittedBy.getText().toString();
//            Intent intent = new Intent(MemeCreatorActivity.this, MemeView.class);
//            intent.putExtra("meme", meme);
//            intent.putExtra("submittedBy", submittedBy);
//            startActivity(intent);
        }

        if (view == mMemePhoto) {
            Toast.makeText(CreatedMemeActivity.this, "Meme!", Toast.LENGTH_LONG).show();
        }

    }
}
