package com.ammar.socialpocketa;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ammar.socialpocketa.models.TweetDetail;
import com.ammar.socialpocketa.models.reply.Reply;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TweetDetailsActivity extends AppCompatActivity {

    private static final String TAG = "TweetDetailsActivity";


    RecyclerView recyclerView;
    ProgressBar pbReply;


    private static List<Reply> replyList;


    //vars

    private static int noOfReplies = 0;

    String _id = "";

//    //private ArrayList<String> mNames = new ArrayList<>();
//    private List<String> mNames = new ArrayList<>();
//
//    private ArrayList<String> mImages = new ArrayList<>();
//
//    private ArrayList<String> mTimes = new ArrayList<>();
//    //private ArrayList<String> mComments = new ArrayList<>();
//
//    private List<String> mComments = new ArrayList<>();
//
//    private ArrayList<Reply> data;

    private List<String> m_Ids = new ArrayList<>();

    //private ArrayList<String> mNames = new ArrayList<>();
    private List<String> mNames = new ArrayList<>();

//    private ArrayList<String> mImages = new ArrayList<>();
//
//    private ArrayList<String> mTimes = new ArrayList<>();
    //private ArrayList<String> mTweets = new ArrayList<>();

    //private ArrayList<String> mRTweets = new ArrayList<>();
    private List<String> mTweets = new ArrayList<>();
    private List<String> mSentiments = new ArrayList<>();

    private List<Boolean> mRetweeteds = new ArrayList<>();
    private List<String> mCreatedAt = new ArrayList<>();
    private List<String> mProfileImageUrls = new ArrayList<>();
    private List<String> mRetweetCounts = new ArrayList<>();
    private List<String> mFavoriteCounts = new ArrayList<>();
    private List<Boolean> mFavoriteds = new ArrayList<>();


    @Nullable
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_details);
        Log.d(TAG, "onCreate: started. " + TAG);

        recyclerView = findViewById(R.id.rvComments);
        pbReply = findViewById(R.id.pb_reply);
        pbReply.setVisibility(View.VISIBLE);

        getIncomingIntent();
//        initComments();

        apiResponse();
    }


    /*@Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_tweet_details , container, false);

        Log.d(TAG, "onCreate: started.");

        recyclerView = rootView.findViewById(R.id.rvComments);

        //getIncomingIntent();
        initComments();

        apiResponse();

        return rootView;
    }
*/


    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        if(getIntent().hasExtra("image") && getIntent().hasExtra("name")){
            Log.d(TAG, "getIncomingIntent: found intent extras.");

            _id = getIntent().getStringExtra("_id");
            Log.d(TAG, "getIncomingIntent: The value of _id is: " + _id);

            String name = getIntent().getStringExtra("name");
            String image = getIntent().getStringExtra("image");

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

        /*mImages.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        //mNames.add("Talk");
        mTimes.add("3 days ago");
        //mComments.add("This is the content of comment");

        mImages.add("https://i.redd.it/tpsnoz5bzo501.jpg");
//        mNames.add("Trondheim");
        mTimes.add("2 days ago");*/
//        mComments.add("This is the content of tweet");

        /*
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
*/
        //initRecyclerView();
    }

    /*private void initRecyclerView() {

        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.rvComments);
        ReplyAdapter adapter = new ReplyAdapter(this, mNames, mImages, mTimes, mComments);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }*/





    public void apiResponse() {


        //now making the call object
        //Here using the api method that we created inside the api interface
        Call<TweetDetail> call = RetrofitClient
                .getInstance()
                .getApi()
                .getComments(_id);
//                .getComments("5b951359d498032e88f99844");
//                .getComments();


            call.enqueue(new Callback<TweetDetail>() {

            @Override
            public void onResponse(Call<TweetDetail> call, Response<TweetDetail> response) {

//                TweetDetail replyList1 = response.body();
//                Log.d(TAG, "onResponse: from \n\n " + replyList1 + "\n\n");

                //In this point we got our Home list
                Log.d(TAG, "onResponse: from 3 \n\n " + response.body().getReplies() + "\n\n");

                List<Reply> replyList = response.body().getReplies();

                try {
                    noOfReplies = replyList.size();


                //looping through all the texts and inserting the text inside the string array
//                for (int i = 0; i < replyList.size(); i++) {
//                    Toast.makeText(TweetDetailsActivity.this, "data is " + response.body().getReplies().get(i).getText(), Toast.LENGTH_LONG).show();
//                }


                Log.d(TAG, "onResponse: from bhai2 " + replyList);

                //Creating a String array for the ListView
                String[] texts = new String[replyList.size()];
                String[] names = new String[replyList.size()];
                String[] sentiments = new String[replyList.size()];

                    //looping through all the texts and inserting the text inside the string array
                for (int i = 0; i < replyList.size(); i++) {
                    //Toast.makeText(TweetDetailsActivity.this, "data is " + response.body().getReplies().get(i).getText(), Toast.LENGTH_SHORT).show();
                    texts[i] = replyList.get(i).getText();
                    names[i] = replyList.get(i).getScreenName();

                    sentiments[i] = replyList.get(i).getSentimentAnalysis();

                    mNames.add(replyList.get(i).getName());
                    mRetweeteds.add(replyList.get(i).getRetweeted());
                    mCreatedAt.add(replyList.get(i).getCreatedAt());
                    mProfileImageUrls.add(replyList.get(i).getProfileImageUrl());
                    mRetweetCounts.add(replyList.get(i).getRetweetCount());
                    mFavoriteCounts.add(replyList.get(i).getFavoriteCount());
                    mFavoriteds.add(replyList.get(i).getFavorited());


                    Log.d(TAG, "onResponse: Sentiment = " + sentiments[i]);


                    //mRTweets.get(i).concat(texts[i]);



                    //List<Integer> newList = new ArrayList<Home>(texts);
                }

//                mComments = Arrays.asList(texts);
                mTweets = Arrays.asList(texts);
                mNames = Arrays.asList(names);
                mSentiments = Arrays.asList(sentiments);

//                    for(int i = 0; i < noOfReplies; i++) {
//                    mImages.add("https://i.redd.it/tpsnoz5bzo501.jpg");
//                    mTimes.add("2 days ago");
//                }

                //displaying the string array into listview
                //listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, heroes));

                //displaying the string array into recycler view
                //HomeAdapter adapter = new HomeAdapter(getContext(), mRTweets);


                pbReply.setVisibility(View.GONE);


                    //recyclerView.setLayoutManager(manager);
                ReplyAdapter adapter = new ReplyAdapter(getApplicationContext(), mNames,
                        mTweets, mSentiments, mRetweeteds, mCreatedAt, mProfileImageUrls,
                        mRetweetCounts, mFavoriteCounts, mFavoriteds );
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


                } catch (NullPointerException e) {
                    Log.e(TAG, "onResponse: NullPointerException " + e.getMessage() );
                    noOfReplies = 0;
                }


            }




            @Override
            public void onFailure(Call<TweetDetail> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }



    /*@Override
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
                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);

    }*/

}