package com.ammar.socialpocketa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ammar.socialpocketa.api.APIService;
import com.ammar.socialpocketa.api.APIUrl;
import com.ammar.socialpocketa.data.SharedPrefManager;
import com.ammar.socialpocketa.models.Login;
import com.ammar.socialpocketa.models.LoginTwitter;
import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginTwitterActivity extends AppCompatActivity {

    private static final String TAG = "LoginTwitterActivity";

    //Declaring Twitter loginButton
//    TwitterLoginButton loginButton;
//
//    String token = "";
//    String secret = "";
//
//    String username = "";
//
//
//
//
//    private TwitterLoginButton twitterLoginButton;
//
//
//    //twitter auth client required for custom login
//    private TwitterAuthClient client;


    ProgressBar pbLoginTwitter;

    String username = "";
    String token = "";
    String secret = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initializing twitter instance
//        Twitter.initialize(this);  //Make sure that this statement is added before setContentView() method


        setContentView(R.layout.activity_login_twitter);

        Log.d(TAG, "onCreate: Started");

        pbLoginTwitter = findViewById(R.id.pb_login_twitter);

        /* fetching the username from LoginTwitter2 */
        username = getIntent().getStringExtra("username");
        token = getIntent().getStringExtra("token");
        secret = getIntent().getStringExtra("secret");


        apiRequest();


        //Instantiating loginButton
//        loginButton = (TwitterLoginButton) findViewById(R.id.default_twitter_login_button);

        /*
          Adding a callback to loginButton
          These statements will execute when loginButton is clicked
         */
//        loginButton.setCallback(new Callback<TwitterSession>() {
//            @Override
//            public void success(Result<TwitterSession> result) {
//                /*
//                  This provides TwitterSession as a result
//                  This will execute when the authentication is successful
//                 */
//                TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
//                TwitterAuthToken authToken = session.getAuthToken();
//                token = authToken.token;
//                secret = authToken.secret;
//
//                username = session.getUserName();
//
//                Log.d(TAG, "success: token: " + token );
//
//                Log.d(TAG, "success: username: " + username);
//
//
//                //Calling login method and passing twitter session
////                login(session);
//
//
////                apiRequest();
//
//
//            }
//
//            @Override
//            public void failure(TwitterException exception) {
//                //Displaying Toast message
//                Toast.makeText(LoginTwitterActivity.this, "Authentication failed!", Toast.LENGTH_LONG).show();
//            }
//        });







        //initialize twitter auth client
//        client = new TwitterAuthClient();
//
//        //find the id of views
//        twitterLoginButton = findViewById(R.id.login_button);

//        userProfileImageView = findViewById(R.id.user_profile_image_view);
//        userDetailsLabel = findViewById(R.id.user_details_label);

        //NOTE : calling default twitter login in OnCreate/OnResume to initialize twitter callback
//        defaultLoginTwitter();







    }






//    /**
//     * method to do Default Twitter Login
//     */
//    public void defaultLoginTwitter() {
//
//        //check if user is already authenticated or not
//        if (getTwitterSession() == null ) {
//
//            //if user is not authenticated start authenticating
//            twitterLoginButton.setCallback(new Callback<TwitterSession>() {
//                @Override
//                public void success(Result<TwitterSession> result) {
//
//                    // Do something with result, which provides a TwitterSession for making API calls
//                    TwitterSession twitterSession = result.data;
//
//                    //call fetch email only when permission is granted
////                    fetchTwitterEmail(twitterSession);
//
//                }
//
//                @Override
//                public void failure(TwitterException exception) {
//                    // Do something on failure
//                    Toast.makeText(LoginTwitterActivity.this, "Failed to authenticate. Please try again.", Toast.LENGTH_SHORT).show();
//                }
//
//            });
//
//        } else {
//
//            //if user is already authenticated direct call fetch twitter email api
//            Toast.makeText(this, "User already authenticated", Toast.LENGTH_SHORT).show();
////            fetchTwitterEmail(getTwitterSession());
//
//        }
//
//    }






//    /**
//     * get authenticates user session
//     *
//     * @return twitter session
//     */
//
//    private TwitterSession getTwitterSession() {
//
//        TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
//
//        //NOTE : if you want to get token and secret too use uncomment the below code
//        TwitterAuthToken authToken = session.getAuthToken();
//        String token = authToken.token;
//        String secret = authToken.secret;
//
//        Log.d(TAG, "/n ---------------------------------------------------- /n");
//        Log.d(TAG, "/n getTwitterSession: Token = " + token);
//        Log.d(TAG, "/n getTwitterSession: Secret = " + secret);
//        Log.d(TAG, "/n ---------------------------------------------------- /n");
//
//        return session;
//
//
//    }
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Pass the activity result to the twitterAuthClient.
//        if (client != null)
//            client.onActivityResult(requestCode, resultCode, data);
//
//        // Pass the activity result to the login button.
//        twitterLoginButton.onActivityResult(requestCode, resultCode, data);
//
//
//    }
//
//
//
//
//
//
//
//    /**
//     * @param session
//     * This method will get username using session and start a new activity where username will be displayed
//     */
//    public void login(TwitterSession session)
//    {
//        username = session.getUserName();
//        Intent intent = new Intent(LoginTwitterActivity.this, MainActivity.class);
//        intent.putExtra("username", username);
//        startActivity(intent);
//    }

//    /**
//     * @param requestCode - we'll set it to REQUEST_CAMERA
//     * @param resultCode - this will store the result code
//     * @param data - data will store an intent
//     */
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Pass the activity result to the login button.
//        loginButton.onActivityResult(requestCode, resultCode, data);
//
//
//    }



    public void apiRequest() {


        pbLoginTwitter.setVisibility(View.VISIBLE);

        String userId = LoginActivity.getDecodedToken();

        Log.d(TAG, "apiRequest: username: " + username);

        Log.d(TAG, "apiRequest: Decoded Token userId: " + userId);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<LoginTwitter> call = service.loginTwitter(userId, username, token, secret);

        call.enqueue(new retrofit2.Callback<LoginTwitter>() {
            @Override
            public void onResponse(Call<LoginTwitter> call, Response<LoginTwitter> response) {




                Boolean isSuccessful = response.isSuccessful();

                if(isSuccessful) {

                    Log.d(TAG, "onResponse: Login with Twitter successful ");

                    pbLoginTwitter.setVisibility(View.INVISIBLE);

                    Intent intent = new Intent(LoginTwitterActivity.this, MainActivity.class);

                    startActivity(intent);


//        To remove this activity from the stack in order to avoid it
//        from opening on pressing the back button
                    finish();

                }
                else {

                    Log.d(TAG, "onResponse: Login with Twitter failed ");

                    Intent intent = new Intent(LoginTwitterActivity.this, LoginTwitter2.class);

                    startActivity(intent);




                }



//                progressDialog.dismiss();

//                String resMsg = response.message();

//                if (resMsg.equals("OK")) {
//                    finish();
//                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(email, response.body().getToken());
                    //Toast.makeText(getApplicationContext(), response.body().getToken(), Toast.LENGTH_LONG).show();

//                    String tempAuthToken = response.body().getToken();


//                    String[] parts = string.split("-");
//                    String part1 = parts[0];


//                    Log.d(TAG, "onResponse: tempAuthToken: " + tempAuthToken.split(" ")[1]);

//                    JWT jwt = new JWT(tempAuthToken.split(" ")[1]);

//                    Claim claimDecodedToken = jwt.getClaim("id");

//                    decodedToken = claimDecodedToken.toString();


//                    Log.d(TAG, "onResponse: JWT: " + jwt);

//                    Log.d(TAG, "onResponse: decodedToken: " + decodedToken);

//                    RetrofitClient.getToken(tempAuthToken);

//                    if (tempAuthToken == null) {
//                        finish();
//                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//
//                    }

//                    setAuthToken(tempAuthToken);


//                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                }

                //else if(resMsg.equals("Bad Request")) {
//                else {
//                    Toast.makeText(getApplicationContext(), "Invalid email or password", Toast.LENGTH_LONG).show();
//                }
            }

            @Override
            public void onFailure(Call<LoginTwitter> call, Throwable t) {
//                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });



    }


}
