package com.ammar.socialpocketa;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ammar.socialpocketa.R;

import java.util.ArrayList;

public class MainActivity extends Fragment {

    private static final String TAG = "MainActivity";

    RecyclerView recyclerView;

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();

    private ArrayList<String> mTimes = new ArrayList<>();
    private ArrayList<String> mTweets = new ArrayList<>();

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started.");

        initImageBitmaps();
    }*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_main, container, false);

        Log.d(TAG, "onCreate: started.");

        recyclerView = rootView.findViewById(R.id.rvPosts);

        initImageBitmaps();

        return rootView;
    }

    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImages.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mNames.add("Havasu Falls");
        mTimes.add("2 days ago");
        mTweets.add("This is the content of tweet");

        mImages.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNames.add("Trondheim");
        mTimes.add("2 days ago");
        mTweets.add("This is the content of tweet");

        mImages.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames.add("Portugal");
        mTimes.add("2 days ago");
        mTweets.add("This is the content of tweet");

        mImages.add("https://i.redd.it/j6myfqglup501.jpg");
        mNames.add("Rocky Mountain National Park");
        mTimes.add("2 days ago");
        mTweets.add("This is the content of tweet");


        mImages.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mNames.add("Mahahual");
        mTimes.add("2 days ago");
        mTweets.add("This is the content of tweet");

        mImages.add("https://i.redd.it/k98uzl68eh501.jpg");
        mNames.add("Frozen Lake");
        mTimes.add("2 days ago");
        mTweets.add("This is the content of tweet");

        mImages.add("https://i.redd.it/glin0nwndo501.jpg");
        mNames.add("White Sands Desert");
        mTimes.add("2 weeks ago");
        mTweets.add("This is another content of tweet");

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        PostAdapter adapter = new PostAdapter(getContext(), mNames, mImages, mTimes, mTweets);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}