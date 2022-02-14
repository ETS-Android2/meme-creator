package com.moringaschool.memecreator.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.memecreator.Constants;
import com.moringaschool.memecreator.R;
import com.moringaschool.memecreator.clients.ImgflipClient;
import com.moringaschool.memecreator.interfaces.ImgflipAPI;
import com.moringaschool.memecreator.models.ImgflipMemePostResponse;
import com.moringaschool.memecreator.models.Meme;
import com.moringaschool.memecreator.models.PostData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemeViewActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MemeViewActivity.class.getSimpleName();
    @BindView(R.id.memeName) TextView mTextView;
    @BindView(R.id.memeImage)
    ImageView mImageView;
    @BindView(R.id.postMemeButton)
    Button mButton;
    @BindView(R.id.text0) EditText mEditText0;
    @BindView(R.id.text1) EditText mEditText1;

    private ImgflipAPI imgflipAPI;

    private String memeName;
    private PostData postData;
    Meme meme;

    private ArrayList<String> mMemeNames = new ArrayList<>();
    private ArrayList<String> mCreatedMemesUrl = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meme_view);
        ButterKnife.bind(this);

        imgflipAPI = ImgflipClient.getClient();

        mButton.setOnClickListener(this);

        Intent intent = getIntent();
        meme = (Meme) intent.getSerializableExtra("meme");
        memeName = intent.getStringExtra("name");
//        String imageUrl = intent.getStringExtra("imageUrl");

        mTextView.setText(meme.getName());
        Picasso.get().load(meme.getUrl()).into(mImageView);

    }

    @Override
    public void onClick(View view) {
        if (view == mButton) {
            myPostRequest();
        }
    }

    private void myPostRequest() {

            Call<ImgflipMemePostResponse> call = imgflipAPI
                    .postMeme(meme.getId(), Constants.USERNAME, Constants.PASSWORD, mEditText0.getText().toString(), mEditText1.getText().toString());

            call.enqueue(new Callback<ImgflipMemePostResponse>() {
                @Override
                public void onResponse(Call<ImgflipMemePostResponse> call, Response<ImgflipMemePostResponse> response) {
                    Log.e("MY POST RESPONSE", response.raw().toString());
                    assert response.body() != null;
                    postData = response.body().getData();
                    Log.e("MY POST DATA", postData.getUrl());

                    saveMemeToFirebase();

                }

                @Override
                public void onFailure(Call<ImgflipMemePostResponse> call, Throwable t) {
                    Log.e("MY POST RESPONSE", t.getMessage());

                }
            });
        }

    private void saveMemeToFirebase() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://meme-creator-462dc-default-rtdb.firebaseio.com/")
                .getReference(Constants.FIREBASE_CREATED_MEMES).child(userId);

        DatabaseReference pushReference = databaseReference.push();
        String pushId = pushReference.getKey();
        postData.setPushId(pushId);
        pushReference.setValue(postData);

        mCreatedMemesUrl.add(postData.getUrl());

        Toast.makeText(MemeViewActivity.this, "Meme Created!", Toast.LENGTH_SHORT).show();

        Intent intent2 = new Intent(MemeViewActivity.this, MainActivity.class);
        intent2.putStringArrayListExtra("createdMemeUrls", mCreatedMemesUrl);
        startActivity(intent2);
    }
}
