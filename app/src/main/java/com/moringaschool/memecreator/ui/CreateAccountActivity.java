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
import com.google.firebase.auth.UserProfileChangeRequest;
import com.moringaschool.memecreator.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = CreateAccountActivity.class.getSimpleName();
    @BindView(R.id.nameEditText) EditText mNameEditText;
    @BindView(R.id.newEmailEditText)
    EditText mNewEmailEditText;
    @BindView(R.id.newPasswordEditText) EditText mNewPasswordEditText;
    @BindView(R.id.confirmPasswordEditText) EditText mConfirmPasswordEditText;
    @BindView(R.id.createNewAccountTextView)
    TextView mCreateAccountTextView;
    @BindView(R.id.loginAccountTextView) TextView mLoginAccountTextView;
    @BindView(R.id.firebaseProgressBar1)
    ProgressBar mFirebaseProgressBar1;
    @BindView(R.id.loadingTextView1) TextView mLoadingTextView1;
    @BindView(R.id.createAccountConstraint)
    ConstraintLayout mCreateAccountConstraint;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private String mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        ButterKnife.bind(this);
        mCreateAccountTextView.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        createAuthStateListener();

    }

    private void createAuthStateListener() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser != null) {
                    Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
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
        if (view == mCreateAccountTextView) {
            showProgressBar();
            createNewUser();
        }
        if(view == mLoginAccountTextView) {
            Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }

    private void createNewUser() {
        mName = mNameEditText.getText().toString().trim();
        String email = mNewEmailEditText.getText().toString().trim();
        String password = mNewPasswordEditText.getText().toString().trim();
        String confirmPassword = mConfirmPasswordEditText.getText().toString().trim();

        if (mName.equals("")) {
            mNameEditText.setError("You cannot enter an empty string");
            return;
        }
        if (email.equals("") || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mNewEmailEditText.setError("Invalid Email.");
            return;
        }
        if (password.length() < 6 || confirmPassword.length() < 6) {
            mNewPasswordEditText.setError("Password must be more than 6 characters");
            mConfirmPasswordEditText.setError("Password must be more than 6 characters");
            return;
        }
        if (!password.equals(confirmPassword)) {
            Toast.makeText(CreateAccountActivity.this, "Passwords Do Not Match!", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                hideProgressBar();
                if (task.isSuccessful()) {
                    Log.d(TAG, "Authentication Successful");
                    updateFirebaseUserProfile(Objects.requireNonNull(task.getResult().getUser()));
                } else {
                    Log.d(TAG, "Authentication Failed");
                }
            }
        });
    }

    private void updateFirebaseUserProfile(final FirebaseUser user) {

        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                .setDisplayName(mName)
                .build();

        user.updateProfile(request).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Log.d(TAG, user.getDisplayName());
                    Toast.makeText(CreateAccountActivity.this, "Name successfully added.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CreateAccountActivity.this, "There's been an error", Toast.LENGTH_LONG);
                }
            }
        });
    }

    private void showProgressBar() {
        mCreateAccountConstraint.setAlpha(0.3f);
        mFirebaseProgressBar1.setVisibility(View.VISIBLE);
        mLoadingTextView1.setVisibility(View.GONE);
    }

    private void hideProgressBar() {
        mCreateAccountConstraint.setAlpha(1f);
        mFirebaseProgressBar1.setVisibility(View.GONE);
        mLoadingTextView1.setVisibility(View.GONE);
    }
}