package com.moringaschool.memecreator.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.moringaschool.memecreator.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = LoginActivity.class.getSimpleName();
    @BindView(R.id.createAccountTextView)
    TextView mCreateAccountTextView;
    @BindView(R.id.emailEditText)
    EditText mEmailEditText;
    @BindView(R.id.passwordEditText) EditText
    mPasswordEditText;
    @BindView(R.id.loginTextView) TextView mLoginTextView;
    @BindView(R.id.loginConstraint)
    ConstraintLayout mLoginConstraint;
    @BindView(R.id.firebaseProgressBar2)
    ProgressBar mFirebaseProgressBar2;
    @BindView(R.id.loadingTextView2) TextView mLoadingTextView2;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        createAuthStateListener();

        mCreateAccountTextView.setOnClickListener(this);
        mLoginTextView.setOnClickListener(this);
    }

    private void createAuthStateListener() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
              final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
              if (firebaseUser != null) {
                  Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                  startActivity(intent);
                  finish();
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
    public void onClick(View view) {
        if(view == mCreateAccountTextView) {
            Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }

        if(view == mLoginTextView) {
            showProgressBar();
            loginUser();
        }
    }

    private void loginUser() {
        String email = mEmailEditText.getText().toString().trim();
        String password = mPasswordEditText.getText().toString().trim();

        if (email.equals("") || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmailEditText.setError("Invalid Email");
            return;
        }

        if (password.equals("")) {
            mPasswordEditText.setError("Password cannot be blank.");
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                hideProgressBar();
                if (task.isSuccessful()) {
                    Log.d(TAG, "Login successful!");
                } else {
                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_LONG).show();
                    Log.d(TAG,"LoginWithEmail: ", task.getException());
                }
            }
        });
    }

    private void showProgressBar() {
        mLoginConstraint.setVisibility(View.GONE);
        mFirebaseProgressBar2.setVisibility(View.VISIBLE);
        mLoadingTextView2.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mLoginConstraint.setVisibility(View.VISIBLE);
        mFirebaseProgressBar2.setVisibility(View.GONE);
        mLoadingTextView2.setVisibility(View.GONE);
    }
}