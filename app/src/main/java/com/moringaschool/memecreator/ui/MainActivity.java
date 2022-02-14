package com.moringaschool.memecreator.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.memecreator.Constants;
import com.moringaschool.memecreator.R;
import com.moringaschool.memecreator.clients.ImgflipClient;
import com.moringaschool.memecreator.interfaces.ImgflipAPI;
import com.moringaschool.memecreator.models.Data;
import com.moringaschool.memecreator.models.ImgflipMemeSearchResponse;
import com.moringaschool.memecreator.models.Meme;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.button) Button mButton;

    private Data data;
    private List<Meme> memes;
    private String name;
    private String imageUrl;

    private ImgflipAPI imgflipAPI;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        Fragment myFragment = new SimpleFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("imageUrl", "");
//        myFragment.setArguments(bundle);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        mAuth = FirebaseAuth.getInstance();
        createAuthStateListener();

        replaceTitleFragment(new SimpleFragment());

        mButton.setOnClickListener(this);

        imgflipAPI = ImgflipClient.getClient();
    }

    private void createAuthStateListener() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    getSupportActionBar().setTitle("Welcome " + firebaseUser.getDisplayName() + "!");
                }
            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }

    @Override
    public void onClick(View view ) {
        if (view == mButton) {
            myRequest();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logoutText) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void myRequest() {

        Call<ImgflipMemeSearchResponse> call = imgflipAPI.getMeme();

        call.enqueue(new Callback<ImgflipMemeSearchResponse>() {

            @Override
            public void onResponse(Call<ImgflipMemeSearchResponse> call, Response<ImgflipMemeSearchResponse> response) {
                Log.e("MY RESPONSE", response.raw().toString());
                data = response.body().getData();
                memes = data.getMemes();
                Random random = new Random();

                Meme selectedMeme = null;

                while(selectedMeme == null) {
                    Meme randomMeme = memes.get(random.nextInt(memes.size()));
                    if(randomMeme.getBoxCount() > 2) {

                    } else {
                        selectedMeme = randomMeme;
                    }
                }

                name = selectedMeme.getName();
                imageUrl = selectedMeme.getUrl();
                String imageId = selectedMeme.getId();
                Log.e("MY MEME NAME", name);

                Intent intent = new Intent(MainActivity.this, MemeViewActivity.class);
                intent.putExtra("meme", selectedMeme);
                intent.putExtra("name", name);
                intent.putExtra("imageUrl", imageUrl);
                intent.putExtra("imageId", imageId);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ImgflipMemeSearchResponse> call, Throwable t) {
                Log.e("MY RESPONSE", t.getMessage());
            }
        });
    }

    private void replaceTitleFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.welcomeTextLayout, fragment);
        fragmentTransaction.commit();

    }

    private void addToSharedPreferences(String name) {
        mEditor.putString(Constants.SHARED_PREFERENCES_USER_NAME, name);
    }
}