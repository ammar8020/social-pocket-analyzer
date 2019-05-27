package com.ammar.socialpocketa.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ammar.socialpocketa.R;
import com.ammar.socialpocketa.dialogs.ReplyDialog;
import com.ammar.socialpocketa.api.RetrofitClient;
import com.ammar.socialpocketa.adapters.ReplyAdapter;
import com.ammar.socialpocketa.models.TweetDetail;
import com.ammar.socialpocketa.models.reply.Reply;
import com.ammar.socialpocketa.utils.TimeUtil;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TweetDetailsActivity extends AppCompatActivity {

    private static final String TAG = "TweetDetailsActivity";


    RecyclerView recyclerView;
    ProgressBar pbReply;


//    private static List<Reply> replyList;


    //vars

//    private static int noOfReplies = 0;

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

//    private List<String> m_Ids = new ArrayList<>();
//
//    //private ArrayList<String> mNames = new ArrayList<>();
//    private List<String> mNames = new ArrayList<>();
//
////    private ArrayList<String> mImages = new ArrayList<>();
////
////    private ArrayList<String> mTimes = new ArrayList<>();
//    //private ArrayList<String> mTweets = new ArrayList<>();
//
//    //private ArrayList<String> mRTweets = new ArrayList<>();
//    private List<String> mTweets = new ArrayList<>();
//    private List<String> mSentiments = new ArrayList<>();
//
//    private List<Boolean> mRetweeteds = new ArrayList<>();
//    private List<String> mCreatedAt = new ArrayList<>();
//    private List<String> mProfileImageUrls = new ArrayList<>();
//    private List<String> mRetweetCounts = new ArrayList<>();
//    private List<String> mFavoriteCounts = new ArrayList<>();
//    private List<Boolean> mFavoriteds = new ArrayList<>();



    public static String sentimentFilter = "";

    private static final String SHARED_PREF_NAME = "settings";

    private static final String KEY_SELECTED_ALGO = "keySelectedAlgo";


    public static List<Reply> getFilteredReplyList() {
        return filteredReplyList;
    }

    public static void setFilteredReplyList(List<Reply> filteredReply) {
        filteredReplyList = filteredReply;
    }

    static List<Reply> filteredReplyList = new ArrayList<>();




    static List<String> idStr = new ArrayList<>();

//    List<String> idStr = new ArrayList<>();

    static List<String> screenNames = new ArrayList<>();

//    List<String> screenNames = new ArrayList<>();



    public static List<String> getIdStr() {
        return idStr;
    }

    public static List<String> getScreenNames() {
        return screenNames;
    }

//    public List<String> getIdStr() {
//        return idStr;
//    }
//
//    public List<String> getScreenNames() {
//        return screenNames;
//    }

    @Nullable
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_details);
        Log.d(TAG, "onCreate: started. " + TAG);

        recyclerView = findViewById(R.id.rvComments);
        pbReply = findViewById(R.id.pb_reply);
//        pbReply.setVisibility(View.VISIBLE);



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Log.d(TAG, "onClick: opening dialog.");

//                TweetDialog dialog = new TweetDialog();
//                dialog.show(getFragmentManager(), "TweetDialog");

                ReplyDialog dialog = new ReplyDialog();
                dialog.show(getFragmentManager(), "ReplyDialog");

            }
        });



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
            String time = getIntent().getStringExtra("time");
            String tweet = getIntent().getStringExtra("tweet");
            String sentiment = getIntent().getStringExtra("sentiment");
            String favoriteCount = getIntent().getStringExtra("favoriteCount");
            String retweetCount = getIntent().getStringExtra("retweetCount");


//            setImage(image, name);


            Log.d(TAG, "setImage: setting the image and name to widgets.");

            TextView tvName = findViewById(R.id.tv_name);
            tvName.setText(name);

            ImageView ivImage = findViewById(R.id.imgView_proPic);
            Glide.with(this)
                    .asBitmap()
                    .load(image)
                    .into(ivImage);

            TextView tvTime = findViewById(R.id.tv_time);
            tvTime.setText(time);

            TextView tvTweet = findViewById(R.id.tv_tweet);
            tvTweet.setText(tweet);

            ImageView ivSentiment = findViewById(R.id.sentiment);
            Glide.with(this)
                    .asBitmap()
                    .load(sentiment)
                    .into(ivSentiment);

            TextView tvFavoriteCount = findViewById(R.id.tv_like);
            tvFavoriteCount.setText(favoriteCount);

            TextView tvRetweetCount = findViewById(R.id.tv_comment);
            tvRetweetCount.setText(retweetCount);


        }
    }

//    private void setImage(String image, String name){
//        Log.d(TAG, "setImage: setting the image and name to widgets.");
//
//        TextView tvName = findViewById(R.id.tv_name);
//        tvName.setText(name);
//
//        ImageView ivImage = findViewById(R.id.imgView_proPic);
//        Glide.with(this)
//                .asBitmap()
//                .load(image)
//                .into(ivImage);
//    }

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


        idStr = new ArrayList<>();

//    List<String> idStr = new ArrayList<>();

        screenNames = new ArrayList<>();


        pbReply.setVisibility(View.VISIBLE);

        recyclerView.setVisibility(View.INVISIBLE);

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

//                List<Reply> replyList = response.body().getReplies();

                try {
//                    noOfReplies = replyList.size();


                //looping through all the texts and inserting the text inside the string array
//                for (int i = 0; i < replyList.size(); i++) {
//                    Toast.makeText(TweetDetailsActivity.this, "data is " + response.body().getReplies().get(i).getText(), Toast.LENGTH_LONG).show();
//                }


//                    List<Reply> replyList;

                    List<Reply> replyList = response.body().getReplies();


                    Log.d(TAG, "onResponse: replyList: " + replyList);


                    if (replyList == null) {

                        pbReply.setVisibility(View.INVISIBLE);
                        Toast.makeText(TweetDetailsActivity.this, "No Replies found", Toast.LENGTH_LONG).show();

                    } else {



                        List<String> m_Ids = new ArrayList<>();

                        //private ArrayList<String> mNames = new ArrayList<>();
                        List<String> mNames = new ArrayList<>();

//    private ArrayList<String> mImages = new ArrayList<>();
//
//    private ArrayList<String> mTimes = new ArrayList<>();
                        //private ArrayList<String> mTweets = new ArrayList<>();

                        //private ArrayList<String> mRTweets = new ArrayList<>();

                        List<String> mTweets = new ArrayList<>();
                        List<String> mSentiments = new ArrayList<>();

                        List<Boolean> mRetweeteds = new ArrayList<>();
                        List<String> mCreatedAt = new ArrayList<>();
                        List<String> mProfileImageUrls = new ArrayList<>();
                        List<String> mRetweetCounts = new ArrayList<>();
                        List<String> mFavoriteCounts = new ArrayList<>();
                        List<Boolean> mFavoriteds = new ArrayList<>();



                        String appreciative = "Appreciated";
                        String abusive = "Abusive";
                        String suggestive = "Suggestion";
                        String seriousConcern = "Serious Concern";
                        String disappointed = "Disappointed";



                        Log.d(TAG, "onResponse: from b " + replyList);

                        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

                        String logReg = "LogReg";
                        String rnn = "RNN";
                        String naiveBayes = "NaiveBayes";

                        String currentSelectedAlgo = sharedPreferences.getString(KEY_SELECTED_ALGO, "LogReg");

                        String tempSelectedAlgo = "";

                        String convertedTime = "";


                        Boolean appreciativeFound = false;
                        Boolean abusiveFound = false;
                        Boolean suggestiveFound = false;
                        Boolean seriousConcernFound = false;
                        Boolean disappointedFound = false;


//                    Reply[] reply = new Reply[replyList.size()];

//                    List<Reply> filteredReplyList = new ArrayList<>();


                        //Creating a String array for the ListView
//                String[] texts = new String[replyList.size()];
//                String[] names = new String[replyList.size()];
//                String[] sentiments = new String[replyList.size()];











                        //looping through all the texts and inserting the text inside the string array
                        for (int i = 0; i < replyList.size(); i++) {
                            //Toast.makeText(TweetDetailsActivity.this, "data is " + response.body().getReplies().get(i).getText(), Toast.LENGTH_SHORT).show();
//                    texts[i] = replyList.get(i).getText();
//                    names[i] = replyList.get(i).getScreenName();
//
//                    sentiments[i] = replyList.get(i).getSentimentAnalysis();


//                    mTweets.add(replyList.get(i).getText());
//                    mNames.add(replyList.get(i).getScreenName());
//
//                    mSentiments.add(replyList.get(i).getSentimentAnalysis());
//
//                    mNames.add(replyList.get(i).getName());
//                    mRetweeteds.add(replyList.get(i).getRetweeted());
//                    mCreatedAt.add(replyList.get(i).getCreatedAt());
//                    mProfileImageUrls.add(replyList.get(i).getProfileImageUrl());
//                    mRetweetCounts.add(replyList.get(i).getRetweetCount());
//                    mFavoriteCounts.add(replyList.get(i).getFavoriteCount());
//                    mFavoriteds.add(replyList.get(i).getFavorited());

//                    Log.d(TAG, "onResponse: Sentiment = " + replyList.get(i).getSentimentAnalysis());



                            if (currentSelectedAlgo.equals(logReg)) {

//                        tempSelectedAlgo = replyList.get(i).getSentimentAnalysis();
                                tempSelectedAlgo = replyList.get(i).getSentimentAnalysisLogreg();

                            } else if (currentSelectedAlgo.equals(rnn)) {

//                        tempSelectedAlgo = replyList.get(i).getSentimentAnalysis();
                                tempSelectedAlgo = replyList.get(i).getSentimentAnalysisRnn();

                            } else {

//                        tempSelectedAlgo = replyList.get(i).getSentimentAnalysis();
                                tempSelectedAlgo = replyList.get(i).getSentimentAnalysisNaiveBayes();

                            }




//                            for (int j = 0; j < replyList.size(); j++ ) {
//
//
//                                if (appreciative.equals(tempSelectedAlgo)) {
//
//                                    appreciativeFound = true;
//
//                                } else if (abusive.equals(tempSelectedAlgo)) {
//
//                                    abusiveFound = true;
//
//                                } else if (suggestive.equals(tempSelectedAlgo)) {
//
//                                    suggestiveFound = true;
//
//                                }
//
//
//                            }




                            if(sentimentFilter.equals(appreciative)) {

//                                if (appreciativeFound.equals(false)) {
//                                    Toast.makeText(TweetDetailsActivity.this, "No Appreciative Reply found", Toast.LENGTH_SHORT).show();
//                                } else {

                                    if (appreciative.equals(tempSelectedAlgo)) {

//                            if (appreciative.contentEquals(postList.get(i).getSentimentAnalysisLogreg() ) ) {


//                        if (postList.get(i).getSentimentAnalysisLogreg().equals("Appreciated")) {


//                                _ids[i] = postList.get(i).getId();
//                                texts[i] = postList.get(i).getText();
//                                sentiments[i] = postList.get(i).getSentimentAnalysisLogreg();


                                        appreciativeFound = true;


//                                        idStr = new ArrayList<String>();
//
//                                        screenNames = new ArrayList<String>();

                                        idStr.add(replyList.get(i).getIdStr());

                                        screenNames.add(replyList.get(i).getScreenName());


                                        m_Ids.add(replyList.get(i).getId());
                                        mTweets.add(replyList.get(i).getText());
                                        mSentiments.add(tempSelectedAlgo);
//
                                        mNames.add(replyList.get(i).getName());
                                        mRetweeteds.add(replyList.get(i).getRetweeted());


                                        convertedTime = TimeUtil.getTimeAgo(replyList.get(i).getCreatedAt()).toString();

//                                Log.d(TAG, "onResponse: convertedTime: " + convertedTime);


                                        mCreatedAt.add(convertedTime);

//                                mCreatedAt.add(postList.get(i).getCreatedAt());


                                        mProfileImageUrls.add(replyList.get(i).getProfileImageUrl());
                                        mRetweetCounts.add(replyList.get(i).getRetweetCount());
                                        mFavoriteCounts.add(replyList.get(i).getFavoriteCount());
                                        mFavoriteds.add(replyList.get(i).getFavorited());


//                            reply[i] = replyList.get(i);

                                        filteredReplyList.add(replyList.get(i));

//                            Log.d(TAG, "onResponse: reply: " + reply);
//                            Log.d(TAG, "onResponse: reply: " + reply.getText());
//                            Log.d(TAG, "onResponse: reply: " + reply.toString());
//                            Log.d(TAG, "onResponse: reply: " + reply.getText().toString());


                                        Log.d(TAG, "onResponse: filteredReplyList: " + filteredReplyList);
//                            Log.d(TAG, "onResponse: filteredReplyList: " + filteredReplyList.toString());
//                            Log.d(TAG, "onResponse: filteredReplyList: " + filteredReplyList.get(i));
//                            Log.d(TAG, "onResponse: filteredReplyList: " + filteredReplyList.get(i).getText());


//
//
                                    }

//                                    else {
//
//                                        Toast.makeText(TweetDetailsActivity.this, "No Appreciative Reply found", Toast.LENGTH_SHORT).show();
//
//                                    }




//                                }

                            } else if(sentimentFilter.equals(abusive)) {


//                                if (abusiveFound.equals(false)) {
//                                    Toast.makeText(TweetDetailsActivity.this, "No Abusive Reply found", Toast.LENGTH_SHORT).show();
//                                } else {


                                    if (abusive.equals(tempSelectedAlgo)) {

//                            if (appreciative.contentEquals(postList.get(i).getSentimentAnalysisLogreg() ) ) {


//                        if (postList.get(i).getSentimentAnalysisLogreg().equals("Appreciated")) {


//                                _ids[i] = postList.get(i).getId();
//                                texts[i] = postList.get(i).getText();
//                                sentiments[i] = postList.get(i).getSentimentAnalysisLogreg();


                                        abusiveFound = true;

//                                        idStr = new ArrayList<String>();
//
//                                        screenNames = new ArrayList<String>();

                                        idStr.add(replyList.get(i).getIdStr());

                                        screenNames.add(replyList.get(i).getScreenName());


                                        m_Ids.add(replyList.get(i).getId());
                                        mTweets.add(replyList.get(i).getText());
                                        mSentiments.add(tempSelectedAlgo);
//
                                        mNames.add(replyList.get(i).getName());
                                        mRetweeteds.add(replyList.get(i).getRetweeted());


                                        convertedTime = TimeUtil.getTimeAgo(replyList.get(i).getCreatedAt()).toString();

//                                Log.d(TAG, "onResponse: convertedTime: " + convertedTime);


                                        mCreatedAt.add(convertedTime);

//                                mCreatedAt.add(postList.get(i).getCreatedAt());


                                        mProfileImageUrls.add(replyList.get(i).getProfileImageUrl());
                                        mRetweetCounts.add(replyList.get(i).getRetweetCount());
                                        mFavoriteCounts.add(replyList.get(i).getFavoriteCount());
                                        mFavoriteds.add(replyList.get(i).getFavorited());
//
//
                                    }

//                                else {
//
//                                    Toast.makeText(TweetDetailsActivity.this, "No Abusive Reply found", Toast.LENGTH_SHORT).show();
//
//                                }


//                                }



                            } else if(sentimentFilter.equals(suggestive)) {

                                if (suggestive.equals(tempSelectedAlgo)) {

//                            if (appreciative.contentEquals(postList.get(i).getSentimentAnalysisLogreg() ) ) {


//                        if (postList.get(i).getSentimentAnalysisLogreg().equals("Appreciated")) {


//                                _ids[i] = postList.get(i).getId();
//                                texts[i] = postList.get(i).getText();
//                                sentiments[i] = postList.get(i).getSentimentAnalysisLogreg();


                                    suggestiveFound = true;

//                                    idStr = new ArrayList<String>();
//
//                                    screenNames = new ArrayList<String>();

                                    idStr.add(replyList.get(i).getIdStr());

                                    screenNames.add(replyList.get(i).getScreenName());



                                    m_Ids.add(replyList.get(i).getId());
                                    mTweets.add(replyList.get(i).getText());
                                    mSentiments.add(tempSelectedAlgo);
//
                                    mNames.add(replyList.get(i).getName());
                                    mRetweeteds.add(replyList.get(i).getRetweeted());


                                    convertedTime = TimeUtil.getTimeAgo(replyList.get(i).getCreatedAt()).toString();

//                                Log.d(TAG, "onResponse: convertedTime: " + convertedTime);


                                    mCreatedAt.add(convertedTime);

//                                mCreatedAt.add(postList.get(i).getCreatedAt());




                                    mProfileImageUrls.add(replyList.get(i).getProfileImageUrl());
                                    mRetweetCounts.add(replyList.get(i).getRetweetCount());
                                    mFavoriteCounts.add(replyList.get(i).getFavoriteCount());
                                    mFavoriteds.add(replyList.get(i).getFavorited());
//
//
                                }
                            } else if(sentimentFilter.equals(seriousConcern)) {

                                if (seriousConcern.equals(tempSelectedAlgo)) {

//                            if (appreciative.contentEquals(postList.get(i).getSentimentAnalysisLogreg() ) ) {


//                        if (postList.get(i).getSentimentAnalysisLogreg().equals("Appreciated")) {


//                                _ids[i] = postList.get(i).getId();
//                                texts[i] = postList.get(i).getText();
//                                sentiments[i] = postList.get(i).getSentimentAnalysisLogreg();


                                    seriousConcernFound = true;

//                                    idStr = new ArrayList<String>();
//
//                                    screenNames = new ArrayList<String>();

                                    idStr.add(replyList.get(i).getIdStr());

                                    screenNames.add(replyList.get(i).getScreenName());





                                    m_Ids.add(replyList.get(i).getId());
                                    mTweets.add(replyList.get(i).getText());
                                    mSentiments.add(tempSelectedAlgo);
//
                                    mNames.add(replyList.get(i).getName());
                                    mRetweeteds.add(replyList.get(i).getRetweeted());


                                    convertedTime = TimeUtil.getTimeAgo(replyList.get(i).getCreatedAt()).toString();

//                                Log.d(TAG, "onResponse: convertedTime: " + convertedTime);


                                    mCreatedAt.add(convertedTime);

//                                mCreatedAt.add(postList.get(i).getCreatedAt());




                                    mProfileImageUrls.add(replyList.get(i).getProfileImageUrl());
                                    mRetweetCounts.add(replyList.get(i).getRetweetCount());
                                    mFavoriteCounts.add(replyList.get(i).getFavoriteCount());
                                    mFavoriteds.add(replyList.get(i).getFavorited());
//
//
                                }
                            } else if(sentimentFilter.equals(disappointed)) {

                                if (disappointed.equals(tempSelectedAlgo)) {

//                            if (appreciative.contentEquals(postList.get(i).getSentimentAnalysisLogreg() ) ) {


//                        if (postList.get(i).getSentimentAnalysisLogreg().equals("Appreciated")) {


//                                _ids[i] = postList.get(i).getId();
//                                texts[i] = postList.get(i).getText();
//                                sentiments[i] = postList.get(i).getSentimentAnalysisLogreg();



                                    disappointedFound = true;

//                                    idStr = new ArrayList<String>();
//
//                                    screenNames = new ArrayList<String>();

                                    idStr.add(replyList.get(i).getIdStr());

                                    screenNames.add(replyList.get(i).getScreenName());




                                    m_Ids.add(replyList.get(i).getId());
                                    mTweets.add(replyList.get(i).getText());
                                    mSentiments.add(tempSelectedAlgo);
//
                                    mNames.add(replyList.get(i).getName());
                                    mRetweeteds.add(replyList.get(i).getRetweeted());


                                    convertedTime = TimeUtil.getTimeAgo(replyList.get(i).getCreatedAt()).toString();

//                                Log.d(TAG, "onResponse: convertedTime: " + convertedTime);


                                    mCreatedAt.add(convertedTime);

//                                mCreatedAt.add(postList.get(i).getCreatedAt());




                                    mProfileImageUrls.add(replyList.get(i).getProfileImageUrl());
                                    mRetweetCounts.add(replyList.get(i).getRetweetCount());
                                    mFavoriteCounts.add(replyList.get(i).getFavoriteCount());
                                    mFavoriteds.add(replyList.get(i).getFavorited());
//
//
                                }
                            }

                            else {

//                            _ids[i] = postList.get(i).getId();
//                            texts[i] = postList.get(i).getText();
//                            sentiments[i] = postList.get(i).getSentimentAnalysisLogreg();

//                                idStr = new ArrayList<String>();
//
//                                screenNames = new ArrayList<String>();

                                idStr.add(replyList.get(i).getIdStr());

                                screenNames.add(replyList.get(i).getScreenName());




                                m_Ids.add(replyList.get(i).getId());
                                mTweets.add(replyList.get(i).getText());
                                mSentiments.add(tempSelectedAlgo);

                                mNames.add(replyList.get(i).getName());
                                mRetweeteds.add(replyList.get(i).getRetweeted());


                                convertedTime = TimeUtil.getTimeAgo(replyList.get(i).getCreatedAt()).toString();

//                                Log.d(TAG, "onResponse: convertedTime: " + convertedTime);


                                mCreatedAt.add(convertedTime);

//                                mCreatedAt.add(postList.get(i).getCreatedAt());




                                mProfileImageUrls.add(replyList.get(i).getProfileImageUrl());
                                mRetweetCounts.add(replyList.get(i).getRetweetCount());
                                mFavoriteCounts.add(replyList.get(i).getFavoriteCount());
                                mFavoriteds.add(replyList.get(i).getFavorited());


                                Log.d(TAG, "onResponse: Sentiment = " + replyList.get(i).getSentimentAnalysisLogreg());
                                //names[i] = postList.get(i).getUser();

                                //mRTweets.get(i).concat(texts[i]);


                                //List<Integer> newList = new ArrayList<Home>(texts);

//                        }

                            }








                            //mRTweets.get(i).concat(texts[i]);



                            //List<Integer> newList = new ArrayList<Home>(texts);
                        }





//                        for (int j = 0; j < replyList.size(); j++ ) {
//
//
//                            if (appreciative.equals(tempSelectedAlgo)) {
//
//                                appreciativeFound = true;
//
//                            } else if (abusive.equals(tempSelectedAlgo)) {
//
//                                abusiveFound = true;
//
//                            }
//
//
//                        }




                        if(sentimentFilter.equals(appreciative)) {


                            if (appreciativeFound.equals(false)) {


                                Toast.makeText(TweetDetailsActivity.this, "No Appreciative Reply found", Toast.LENGTH_SHORT).show();

                            }


                        } else if (sentimentFilter.equals(abusive)) {

                            if (abusiveFound.equals(false)) {


                                Toast.makeText(TweetDetailsActivity.this, "No Abusive Reply found", Toast.LENGTH_SHORT).show();

                            }


                        } else if (sentimentFilter.equals(suggestive)) {

                            if (suggestiveFound.equals(false)) {


                                Toast.makeText(TweetDetailsActivity.this, "No Suggestive Reply found", Toast.LENGTH_SHORT).show();

                            }


                        } else if (sentimentFilter.equals(seriousConcern)) {

                            if (seriousConcernFound.equals(false)) {


                                Toast.makeText(TweetDetailsActivity.this, "No Serious Concern Reply found", Toast.LENGTH_SHORT).show();

                            }


                        } else if (sentimentFilter.equals(disappointed)) {

                            if (disappointedFound.equals(false)) {


                                Toast.makeText(TweetDetailsActivity.this, "No Disappointed Reply found", Toast.LENGTH_SHORT).show();

                            }


                        }






//                mComments = Arrays.asList(texts);
//                mTweets = Arrays.asList(texts);
//                mNames = Arrays.asList(names);
//                mSentiments = Arrays.asList(sentiments);

//                    for(int i = 0; i < noOfReplies; i++) {
//                    mImages.add("https://i.redd.it/tpsnoz5bzo501.jpg");
//                    mTimes.add("2 days ago");
//                }

                        //displaying the string array into listview
                        //listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, heroes));

                        //displaying the string array into recycler view
                        //HomeAdapter adapter = new HomeAdapter(getContext(), mRTweets);


                        pbReply.setVisibility(View.GONE);

                        recyclerView.setVisibility(View.VISIBLE);


                        //recyclerView.setLayoutManager(manager);
                        ReplyAdapter adapter = new ReplyAdapter(getApplicationContext(), mNames,
                                mTweets, mSentiments, mRetweeteds, mCreatedAt, mProfileImageUrls,
                                mRetweetCounts, mFavoriteCounts, mFavoriteds );
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));









                    }




                } catch (NullPointerException e) {
                    Log.e(TAG, "onResponse: NullPointerException " + e.getMessage() );
//                    noOfReplies = 0;
                }


            }




            @Override
            public void onFailure(Call<TweetDetail> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);


        switch (sentimentFilter) {

            case "Appreciated":
                menu.getItem(0).setChecked(true);
                break;

            case "Abusive":
                menu.getItem(1).setChecked(true);
                break;

            case "Suggestion":
                menu.getItem(2).setChecked(true);
                break;

            case "Serious Concern":
                menu.getItem(3).setChecked(true);
                break;

            case "Disappointed":
                menu.getItem(4).setChecked(true);
                break;


            default:
                menu.getItem(5).setChecked(true);
                break;

        }



        return true;

//        super.onCreateOptionsMenu(menu, inflater);
    }


/*
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




//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//
//        getMenuInflater().inflate(R.menu.menu, menu);
//
//
//        super.onCreateOptionsMenu(menu, inflater);
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getActivity().getMenuInflater().inflate(R.menu.navigation_drawer, menu);
//        return true;
//    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        if (id == R.id.action_appreciative) {

            Log.d(TAG, "onOptionsItemSelected: Appreciative Filter clicked");

//            CheckBox box = v.findViewById(R.id.addressCheckBox); //get your checkbox
//            box.setChecked(!box.isChecked()); //toggle checkbox-state

            item.setChecked(!item.isChecked()); //toggle checkbox-state

            sentimentFilter = "Appreciated";


//            recyclerView.setVisibility(View.INVISIBLE);


            apiResponse();


            // Reload current fragment
//            Fragment frg = null;
//            frg = getActivity().getSupportFragmentManager().findFragmentByTag("Your_Fragment_TAG");
//            final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//            ft.detach(frg);
//            ft.attach(frg);
//


//            Fragment currentFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container);


//            Fragment currentFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.post_container);
//
//            if (currentFragment instanceof MentionsFragment) {
//
//                FragmentTransaction fragTransaction =   (getActivity()).getSupportFragmentManager().beginTransaction();
//                fragTransaction.detach(currentFragment);
//                fragTransaction.attach(currentFragment);
//                fragTransaction.commit();
//
//            }



//            FragmentTransaction ft = getFragmentManager().beginTransaction();
//            ft.detach(this).attach(this).commit();


        } else if (id == R.id.action_abusive) {

            Log.d(TAG, "onOptionsItemSelected: Abusive Filter clicked");

//            CheckBox box = v.findViewById(R.id.addressCheckBox); //get your checkbox
//            box.setChecked(!box.isChecked()); //toggle checkbox-state

            item.setChecked(!item.isChecked()); //toggle checkbox-state

            sentimentFilter = "Abusive";

//            recyclerView.setVisibility(View.INVISIBLE);

            apiResponse();



        } else if (id == R.id.action_suggestive) {

            Log.d(TAG, "onOptionsItemSelected: Suggestive Filter clicked");

//            CheckBox box = v.findViewById(R.id.addressCheckBox); //get your checkbox
//            box.setChecked(!box.isChecked()); //toggle checkbox-state

            item.setChecked(!item.isChecked()); //toggle checkbox-state

            sentimentFilter = "Suggestion";

//            recyclerView.setVisibility(View.INVISIBLE);

            apiResponse();



        } else if (id == R.id.action_serious_concern) {

            Log.d(TAG, "onOptionsItemSelected: Serious Concern Filter clicked");

//            CheckBox box = v.findViewById(R.id.addressCheckBox); //get your checkbox
//            box.setChecked(!box.isChecked()); //toggle checkbox-state

            item.setChecked(!item.isChecked()); //toggle checkbox-state

            sentimentFilter = "Serious Concern";

//            recyclerView.setVisibility(View.INVISIBLE);

            apiResponse();



        } else if (id == R.id.action_disappointed) {

            Log.d(TAG, "onOptionsItemSelected: disappointed Filter clicked");

//            CheckBox box = v.findViewById(R.id.addressCheckBox); //get your checkbox
//            box.setChecked(!box.isChecked()); //toggle checkbox-state

            item.setChecked(!item.isChecked()); //toggle checkbox-state

            sentimentFilter = "Disappointed";

//            recyclerView.setVisibility(View.INVISIBLE);

            apiResponse();



        } else {

            Log.d(TAG, "onOptionsItemSelected: DisplayAll Filter clicked");

//            CheckBox box = v.findViewById(R.id.addressCheckBox); //get your checkbox
//            box.setChecked(!box.isChecked()); //toggle checkbox-state

            item.setChecked(!item.isChecked()); //toggle checkbox-state

            sentimentFilter = "DisplayAll";

//            recyclerView.setVisibility(View.INVISIBLE);

            apiResponse();



        }







        return super.onOptionsItemSelected(item);

    }







}