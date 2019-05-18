package com.ammar.socialpocketa;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ammar.socialpocketa.api.APIService;
import com.ammar.socialpocketa.api.APIUrl;
import com.ammar.socialpocketa.data.SharedPrefManager;
import com.ammar.socialpocketa.models.Login;
import com.ammar.socialpocketa.models.LoginTwitter;
import com.ammar.socialpocketa.models.Mention;
import com.ammar.socialpocketa.utils.TimeUtil;
import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private Context mContext;
    TextView linkSignup;
    EditText txtEmail, txtPassword;
    Button btnLogin;

    ProgressDialog progressDialog;

    ProgressBar pbLogin;
    public static String authToken = "";

    public static String getDecodedToken() {
        return decodedToken;
    }

    public static void setDecodedToken(String decodedToken) {
        LoginActivity.decodedToken = decodedToken;
    }

    private static String decodedToken = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mContext = LoginActivity.this;
        //if user is already logged in openeing the main activity

        /*if (authToken != null) {
            if (SharedPrefManager.getInstance(this).isLoggedIn()) {
                finish();
                startActivity(new Intent(this, MainActivity.class));
            }
        }*/

        if (SharedPrefManager.getInstance(mContext).isLoggedIn()){
//            finish();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        linkSignup = findViewById(R.id.link_signup);
        txtEmail = findViewById(R.id.input_email);
        txtPassword = findViewById(R.id.input_password);
        btnLogin = findViewById(R.id.btn_login);
        pbLogin = findViewById(R.id.pb_login);

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
                } else if (txtPassword.getText().toString().trim().length() < 8) {
                    txtPassword.setError("Password must include 8 characters!");
                } else {
                    Snackbar.make(view, "No validation errors, continue login", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    /*Intent intent = new Intent(getApplicationContext(), HomeFragment.class);
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
        progressDialog = new ProgressDialog(this);
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
//                progressDialog.dismiss();

                String resMsg = response.message();


                if (resMsg.equals("OK")) {
//                    finish();
                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(email, response.body().getToken());
                    //Toast.makeText(getApplicationContext(), response.body().getToken(), Toast.LENGTH_LONG).show();

                    String tempAuthToken = response.body().getToken();


//                    String[] parts = string.split("-");
//                    String part1 = parts[0];


                    Log.d(TAG, "onResponse: tempAuthToken: " + tempAuthToken.split(" ")[1]);

                    JWT jwt = new JWT(tempAuthToken.split(" ")[1]);

                    Claim claimDecodedToken = jwt.getClaim("id");

                    decodedToken = claimDecodedToken.asString();


                    Log.d(TAG, "onResponse: JWT: " + jwt);

                    Log.d(TAG, "onResponse: decodedToken: " + decodedToken);

                    RetrofitClient.getToken(tempAuthToken);

                    if (tempAuthToken == null) {
//                        finish();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                    }

                    setAuthToken(tempAuthToken);


                    apiResponse();

//                    startActivity(new Intent(getApplicationContext(), MainActivity.class));

//                    startActivity(new Intent(getApplicationContext(), LoginTwitter2.class));




                }


//                else if ( response.body().getEmail() != null) {
//            }

//                else {

                else if (resMsg.equals("Forbidden")) {
                        Toast.makeText(LoginActivity.this, "An email verification has been sent to you. Please verify your Email First", Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();
                }


                else if(resMsg.equals("Not Found")) {
                    Toast.makeText(getApplicationContext(), "Invalid email or password", Toast.LENGTH_LONG).show();

                    progressDialog.dismiss();

                }

//                }
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






    public void apiResponse() {

//        pbLogin.setVisibility(View.VISIBLE);

        //now making the call object
        //Here using the api method that we created inside the api interface
        Call<List<Mention>> call = RetrofitClient
                .getInstance()
                .getApi()
                .getMentions();


        call.enqueue(new Callback<List<Mention>>() {

            @Override
            public void onResponse(Call<List<Mention>> call, Response<List<Mention>> response) {


//                pbLogin.setVisibility(View.GONE);

                progressDialog.dismiss();

                try {

                    if (response.code() == 400) {

                        Toast.makeText(LoginActivity.this, "SignIn with twitter to analyze your tweets", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(getApplicationContext(), LoginTwitter2.class));

                        finish();

                    } else if (response.code() == 200) {

                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        finish();
                    }


                } catch (NullPointerException e) {
                    Log.e(TAG, "onResponse: NullPointerException " + e.getMessage());
//                    noOfTweets = 0;
                }


            }


            @Override
            public void onFailure(Call<List<Mention>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();

                progressDialog.dismiss();
            }
        });

    }







}