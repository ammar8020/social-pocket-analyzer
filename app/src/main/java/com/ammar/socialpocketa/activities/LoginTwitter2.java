package com.ammar.socialpocketa.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ammar.socialpocketa.R;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;


public class LoginTwitter2 extends AppCompatActivity {


    private static final String TAG = "LoginTwitter2";

    //Declaring Twitter loginButton
    TwitterLoginButton loginButton;

    ProgressBar pbLoginTwitter;

    /**
     * @param savedInstanceState - saves instance state
     * onCreate method takes savedInstanceState as parameter and calls it's super method
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initializing twitter instance
        Twitter.initialize(this);  //Make sure that this statement is added before setContentView() method
        setContentView(R.layout.activity_login_twitter);


        pbLoginTwitter =findViewById(R.id.pb_login_twitter);

        //Instantiating loginButton
        loginButton = findViewById(R.id.login_button);

        /*
          Adding a callback to loginButton
          These statements will execute when loginButton is clicked
         */
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                /*
                  This provides TwitterSession as a result
                  This will execute when the authentication is successful
                 */
                TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                TwitterAuthToken authToken = session.getAuthToken();
                String token = authToken.token;
                String secret = authToken.secret;

                Log.d(TAG, "success: token: " + token);
                Log.d(TAG, "success: secret: " + secret);

                //Calling login method and passing twitter session
                login(session, token, secret);
                pbLoginTwitter.setVisibility(View.GONE);
            }

            @Override
            public void failure(TwitterException exception) {
                //Displaying Toast message
                Toast.makeText(LoginTwitter2.this, "Authentication failed!", Toast.LENGTH_LONG).show();

                pbLoginTwitter.setVisibility(View.GONE);
            }
        });
    }

    /**
     * @param session
     * This method will get username using session and start a new activity where username will be displayed
     */
    public void login(TwitterSession session, String token, String secret)
    {
        String username = session.getUserName();
        Intent intent = new Intent(LoginTwitter2.this, LoginTwitterActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("token", token);
        intent.putExtra("secret", secret);
        startActivity(intent);

//        To remove this activity from the stack in order to avoid it
//        from opening on pressing the back button
        finish();
    }

    /**
     * @param requestCode - we'll set it to REQUEST_CAMERA
     * @param resultCode - this will store the result code
     * @param data - data will store an intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        loginButton.onActivityResult(requestCode, resultCode, data);

        pbLoginTwitter.setVisibility(View.VISIBLE);

    }
}
