package com.ammar.socialpocketa;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * @author Priyanka
 */

public class Homepage extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        /* fetching the username from LoginActivity */
        String username = getIntent().getStringExtra("username");
        TextView uname = findViewById(R.id.TV_username);
        uname.setText(username);
    }
}
