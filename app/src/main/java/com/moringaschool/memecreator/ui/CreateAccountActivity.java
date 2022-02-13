package com.moringaschool.memecreator.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.moringaschool.memecreator.R;

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

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        ButterKnife.bind(this);
        mCreateAccountTextView.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view) {
        if (view == mCreateAccountTextView) {
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
        String name = mNameEditText.getText().toString().trim();
        String email = mNewEmailEditText.getText().toString().trim();
        String password = mNewPasswordEditText.getText().toString().trim();
        String confirmPassword = mConfirmPasswordEditText.getText().toString().trim();

        if (name.equals("")) {
            mNameEditText.setError("You cannot enter an empty string");
            return;
        }
        if (email.equals("") || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mNewEmailEditText.setError("Invalid Email.");
            return;
        }
        if (password.equals("") || confirmPassword.equals("")) {
            mNewPasswordEditText.setError("Invalid Password.");
            mConfirmPasswordEditText.setError("Invalid Password");
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
                if (task.isSuccessful()) {
                    Log.d(TAG, "Authentication Successful");
                } else {
                    Log.d(TAG, "Authentication Failed");
                }
            }
        });
    }
}