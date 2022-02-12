package com.moringaschool.memecreator.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.moringaschool.memecreator.R;

import butterknife.BindView;

public class CreateAccountActivity extends AppCompatActivity {
    @BindView(R.id.newEmailEditText)
    EditText mNewEmailEditText;
    @BindView(R.id.newPasswordEditText) EditText mNewPasswordEditText;
    @BindView(R.id.confirmPasswordEditText) EditText mConfirmPasswordEditText;
    @BindView(R.id.createAccountTextView)
    TextView mCreateAccountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }
}