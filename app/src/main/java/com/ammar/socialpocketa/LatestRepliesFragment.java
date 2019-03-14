package com.ammar.socialpocketa;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.ammar.socialpocketa.models.LatestReply;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LatestRepliesFragment extends Fragment {


    private static final String TAG = "LatestRepliesFragment";


    RecyclerView recyclerView;
    ProgressBar pbLatestReplies;


//    private static List<LatestReply> replyList;


    //vars

//    private static int noOfReplies = 0;

//    String _id = "";

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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View rootView = inflater.inflate(R.layout.fragment_latest_replies, container, false);


        Log.d(TAG, "onCreate: started. " + TAG);

        recyclerView = rootView.findViewById(R.id.rv_latest_replies);
        pbLatestReplies = rootView.findViewById(R.id.pb_latest_replies);
//        pbLatestReplies.setVisibility(View.VISIBLE);

        apiResponse();

        return rootView;

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
//        getActivity().setTitle("Latest Replies");
    }




    public void apiResponse() {


        //now making the call object
        //Here using the api method that we created inside the api interface
        Call<List<LatestReply>> call = RetrofitClient
                .getInstance()
                .getApi()
                .getLatestReply();
//                .getComments("5b951359d498032e88f99844");
//                .getComments();


        call.enqueue(new Callback<List<LatestReply>>() {

            @Override
            public void onResponse(Call<List<LatestReply>> call, Response<List<LatestReply>> response) {

//                TweetDetail replyList1 = response.body();
//                Log.d(TAG, "onResponse: from \n\n " + replyList1 + "\n\n");



                try {

                    //In this point we got our LatestReply list
                    Log.d(TAG, "onResponse: from 3 \n\n " + response.body() + "\n\n");

                    List<LatestReply> replyList = response.body();

//                    noOfReplies = replyList.size();


                    //looping through all the texts and inserting the text inside the string array
//                for (int i = 0; i < replyList.size(); i++) {
//                    Toast.makeText(TweetDetailsActivity.this, "data is " + response.body().getReplies().get(i).getText(), Toast.LENGTH_LONG).show();
//                }


                    Log.d(TAG, "onResponse: from 2 " + replyList);

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


                    pbLatestReplies.setVisibility(View.GONE);


                    //recyclerView.setLayoutManager(manager);
                    ReplyAdapter adapter = new ReplyAdapter(getContext(), mNames,
                            mTweets, mSentiments, mRetweeteds, mCreatedAt, mProfileImageUrls,
                            mRetweetCounts, mFavoriteCounts, mFavoriteds );
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


                } catch (NullPointerException e) {
                    Log.e(TAG, "onResponse: NullPointerException " + e.getMessage() );
//                    noOfReplies = 0;
                }


            }




            @Override
            public void onFailure(Call<List<LatestReply>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }




}
