package com.ammar.socialpocketa;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PostDetailsActivity extends AppCompatActivity {

    private static final String TAG = "PostDetailsActivity";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();

    private ArrayList<String> mTimes = new ArrayList<>();
    private ArrayList<String> mComments = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
        Log.d(TAG, "onCreate: started.");

        getIncomingIntent();
        initComments();
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        if(getIntent().hasExtra("image") && getIntent().hasExtra("name")){
            Log.d(TAG, "getIncomingIntent: found intent extras.");

            String image = getIntent().getStringExtra("image");
            String name = getIntent().getStringExtra("name");

            setImage(image, name);
        }
    }

    private void setImage(String image, String name){
        Log.d(TAG, "setImage: setting the image and name to widgets.");

        TextView tvName = findViewById(R.id.tv_name);
        tvName.setText(name);

        ImageView ivImage = findViewById(R.id.imgView_proPic);
        Glide.with(this)
                .asBitmap()
                .load(image)
                .into(ivImage);
    }

    private void initComments(){
        Log.d(TAG, "initComments: preparing comments.");

        mImages.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mNames.add("Talk");
        mTimes.add("3 days ago");
        mComments.add("This is the content of comment");

        mImages.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNames.add("Trondheim");
        mTimes.add("2 days ago");
        mComments.add("This is the content of tweet");

        mImages.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames.add("Portugal");
        mTimes.add("2 days ago");
        mComments.add("This is the content of tweet");

        mImages.add("https://i.redd.it/j6myfqglup501.jpg");
        mNames.add("Rocky Mountain National Park");
        mTimes.add("2 days ago");
        mComments.add("This is the content of tweet");

        mImages.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mNames.add("Mahahual");
        mTimes.add("2 days ago");
        mComments.add("This is the content of tweet");

        mImages.add("https://i.redd.it/k98uzl68eh501.jpg");
        mNames.add("Frozen Lake");
        mTimes.add("2 days ago");
        mComments.add("This is the content of tweet");

        mImages.add("https://i.redd.it/glin0nwndo501.jpg");
        mNames.add("Rock");
        mTimes.add("3 weeks ago");
        mComments.add("This is another content of comment");

        initRecyclerView();
    }

    private void initRecyclerView() {

        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.rvComments);
        CommentAdapter adapter = new CommentAdapter(this, mNames, mImages, mTimes, mComments);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int menuItemThatWasSelected = item.getItemId();

        switch(menuItemThatWasSelected) {
            case R.id.action_appreciative:
                String message = "Appreciative clicked !";
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);

    }

}