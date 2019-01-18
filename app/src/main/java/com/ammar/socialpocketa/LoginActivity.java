package com.ammar.socialpocketa;

import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.ammar.socialpocketa.api.APIService;
import com.ammar.socialpocketa.api.APIUrl;
import com.ammar.socialpocketa.helper.SharedPrefManager;
import com.ammar.socialpocketa.models.Login;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    TextView linkSignup;
    EditText txtEmail, txtPassword;
    Button btnLogin;
    public static String authToken = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //if user is already logged in openeing the main activity
        if (authToken != null) {
            if (SharedPrefManager.getInstance(this).isLoggedIn()) {
                finish();
                startActivity(new Intent(this, NavigationDrawerActivity.class));
            }
        }

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
                    Snackbar.make(view, "No validation errors, continue login", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    /*Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);*/
                    userSignIn();
                }
            }
        });
    }

    public final static boolean isValidEmail(CharSequence target) {

        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }


    private void userSignIn() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing In...");
        progressDialog.show();

        final String email = txtEmail.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<Login> call = service.userLogin(email, password);

        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                progressDialog.dismiss();

                String resMsg = response.message();

                if (resMsg.equals("OK")) {
                    finish();
                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(email, response.body().getToken());
                    //Toast.makeText(getApplicationContext(), response.body().getToken(), Toast.LENGTH_LONG).show();

                    String tempAuthToken = response.body().getToken();

                    if (tempAuthToken == null) {
                        finish();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));

                    }

                    setAuthToken(tempAuthToken);


                    startActivity(new Intent(getApplicationContext(), NavigationDrawerActivity.class));
                }

                //else if(resMsg.equals("Bad Request")) {
                else {
                    Toast.makeText(getApplicationContext(), "Invalid email or password", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });





    }

    public static void setAuthToken(String userToken) {

        authToken = userToken ;

    }

    public static String getAuthToken() {

        return authToken;

    }


}