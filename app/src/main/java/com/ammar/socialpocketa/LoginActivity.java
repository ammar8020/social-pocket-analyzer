package com.ammar.socialpocketa;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    TextView linkSignup;
    EditText txtEmail, txtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        linkSignup = findViewById(R.id.link_signup);
        txtEmail = findViewById(R.id.input_email);
        txtPassword = findViewById(R.id.input_password);
        btnLogin = findViewById(R.id.btn_login);

        linkSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtEmail.getText().toString().trim().isEmpty()) {
                    txtEmail.setError("Email is required!");
                } else if(!isValidEmail(txtEmail.getText().toString().trim())) {
                    txtEmail.setError("Please enter a valid email.");
                } else if(txtPassword.getText().toString().trim().isEmpty()) {
                    txtPassword.setError("Password is required!");
                } else {
                    //Snackbar.make(view, "No validation errors, continue login", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public final static boolean isValidEmail(CharSequence target) {

        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

}
