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

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    TextView linkSignin;
    EditText txtName, txtEmail, txtPassword, txtConPassword;

    Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        linkSignin = findViewById(R.id.link_signin);
        txtName = findViewById(R.id.txt_name);
        txtEmail = findViewById(R.id.txt_email);
        txtPassword = findViewById(R.id.txt_password);
        txtConPassword = findViewById(R.id.txt_confirm_pwd);
        btnSignup = findViewById(R.id.btn_signup);

        linkSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtName.getText().toString().trim().isEmpty()){
                    txtName.setError("This field is required");
                }else if(txtEmail.getText().toString().trim().isEmpty()){
                    txtEmail.setError("This filed is required");
                }else if(!isValidEmail(txtEmail.getText().toString().trim())){
                    txtEmail.setError("Not a valid email");
                }else if(txtPassword.getText().toString().trim().isEmpty()){
                    txtPassword.setError("This field is required");
                }else if(txtConPassword.getText().toString().trim().isEmpty()){
                    txtConPassword.setError("This field is required");
                }else if(txtPassword.getText().toString().trim().compareTo(txtConPassword.getText().toString().trim()) != 0){
                    txtConPassword.setError("Not matched with the Password");
                }else{
                    Snackbar.make(v, "No validation errors, continue signup", Snackbar.LENGTH_LONG).setAction("Action",null).show();
                }
            }
        });

    }

    public final static boolean isValidEmail(CharSequence target){
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

}