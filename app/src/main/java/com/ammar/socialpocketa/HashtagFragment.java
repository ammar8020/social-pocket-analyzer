package com.ammar.socialpocketa;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ammar.socialpocketa.models.Hashtag;
import com.ammar.socialpocketa.models.TrendDetail;
import com.ammar.socialpocketa.models.trendDetail.Trend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HashtagFragment extends Fragment {

    private static final String TAG = "HashtagFragment";

    EditText etKeyword;
    Button btnSearch;
    ProgressBar pbHashtag;
    RecyclerView recyclerView;
    RecyclerView rvTrends;

//    //private HomeAdapter adapter;
//    private static List<Hashtag> postList;
//
//    //vars
//    String keyword = "";
////    private static int noOfTweets = 0;
//
//    private List<String> m_Ids = new ArrayList<>();
//
//    //private ArrayList<String> mNames = new ArrayList<>();
//    private List<String> mNames = new ArrayList<>();
//
//    //private ArrayList<String> mTweets = new ArrayList<>();
//
//    //private ArrayList<String> mRTweets = new ArrayList<>();
//    private List<String> mTweets = new ArrayList<>();
//    private List<String> mSentiments = new ArrayList<>();
//
//    private List<Boolean> mRetweeteds = new ArrayList<>();
//    private List<Long> mCreatedAt = new ArrayList<>();
//    private List<String> mProfileImageUrls = new ArrayList<>();
//    private List<Integer> mRetweetCounts = new ArrayList<>();
//    private List<Integer> mFavoriteCounts = new ArrayList<>();
//    private List<Boolean> mFavoriteds = new ArrayList<>();


    public static String sentimentFilter = "";


    private static final String SHARED_PREF_NAME = "settings";

    private static final String KEY_SELECTED_ALGO = "keySelectedAlgo";


    List<Trend> trendList = new ArrayList<>();

    List<String> mTrendNames = new ArrayList<>();

    //private ArrayList<String> s = new ArrayList<>();

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        Log.d(TAG, "onCreate: started.");

        initImageBitmaps();
    }*/




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_hashtag, container, false);

        Log.d(TAG, "onCreate: started.");

        etKeyword = rootView.findViewById(R.id.et_keyword);
        pbHashtag = rootView.findViewById(R.id.pb_hashtag);
//        pbHashtag.setVisibility(View.GONE);

        recyclerView = rootView.findViewById(R.id.rv_hashtag);
        rvTrends = rootView.findViewById(R.id.rv_trend);

        initTrends();

        Log.d(TAG, "onCreateView: done init");

        btnSearch = rootView.findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Search Button clicked");

                apiResponse();
            }
        });
        

//        initImageBitmaps();

        return rootView;
    }

    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        /*for (int i = 0; i < noOfTweets; i++ ){
            mImages.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
            mNames.add("Havasu Falls");
            mTimes.add("2 days ago");
        }*/

//        mTweets.add("This is the content of tweet");
        //mTweets.add(s.get(0));

        /*mImages.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNames.add("Trondheim");
        mTimes.add("2 days ago");
//        mTweets.add("This is the content of tweet");

        mImages.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames.add("Portugal");
        mTimes.add("2 days ago");
//        mTweets.add("This is the content of tweet");

        mImages.add("https://i.redd.it/j6myfqglup501.jpg");
        mNames.add("Rocky Mountain National Park");
        mTimes.add("2 days ago");
//        mTweets.add("This is the content of tweet");


        mImages.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mNames.add("Mahahual");
        mTimes.add("2 days ago");
//        mTweets.add("This is the content of tweet");

        mImages.add("https://i.redd.it/k98uzl68eh501.jpg");
        mNames.add("Frozen Lake");
        mTimes.add("2 days ago");
//        mTweets.add("This is the content of tweet");

        mImages.add("https://i.redd.it/glin0nwndo501.jpg");
        mNames.add("White Sands Desert");
        mTimes.add("2 weeks ago");*/
//        mTweets.add("This is another content of tweet");
        //initRecyclerView();


    }


    /*private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        HomeAdapter adapter = new HomeAdapter(getContext(), mNames, mImages, mTimes, mTweets);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }*/


    public void initTrends() {

//        rvTrends.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
        pbHashtag.setVisibility(View.VISIBLE);

        //now making the call object
        //Here using the api method that we created inside the api interface
        Call<List<TrendDetail>> call = RetrofitClient
                .getInstance()
                .getApi()
                .getTrends();


        call.enqueue(new Callback<List<TrendDetail>>() {

            @Override
            public void onResponse(Call<List<TrendDetail>> call, Response<List<TrendDetail>> response) {

                Log.d(TAG, "onResponse: Inside onResponse");

//                if (response.code() == 400) {
//                    try {
//                        Log.e("Error code 400", response.errorBody().string());
//                    } catch (Exception e) {
//                        Log.e(TAG, "Exception thrown by Error code 400");
//                    }
//                }



//                List<String> mTrendNames = new ArrayList<>();

                //In this point we got our Home list


//                Log.d(TAG, "onResponse: One " + response.body());
//
//                for(int i=0; i< response.body().size(); i++ ) {
//                    Log.d(TAG, "onResponse: two " + response.body().get(i));
//                    Log.d(TAG, "onResponse: three " + response.body().get(i).getTrends());
//                    Log.d(TAG, "onResponse: four " + response.body().get(i).getTrends().get(i).getName());
//                }
//
//                for (int i=0; i < response.body().size(); i++) {
//
//                    for ( int j=0; j < response.body().get(i).getTrends().size(); j++ ) {
//
//                        Log.d(TAG, "onResponse: four " + response.body().get(i).getTrends().get(j).getName());
//                    }
//                }


                try {



//                    for (int i=0; i < response.body().size(); i++) {
//                        trendList.add(response.body().get(i).getTrends());
//                    }
//                    Log.d(TAG, "onResponse: Trend list: " + trendList);


//                    for(TrendDetail tDetail: response.body()) {
//                        Log.d(TAG, "onResponse: Trend list: " + tDetail.getTrends());
////                        trendList.add(tDetail);
//                    }


//                    noOfTweets = postList.size();


                    //Creating a String array for the ListView
//                    String[] _ids = new String[postList.size()];
//                    String[] texts = new String[postList.size()];
//                    String[] sentiments = new String[postList.size()];
                    //String[] names = new String[postList.size()];

//                    String[] names = new String[postList.size()];
//                    Boolean[] retweeteds = new Boolean[postList.size()];
//                    Long[] createdAt = new Long[postList.size()];
//                    String[] profileImageUrls = new String[postList.size()];
//                    Integer[] retweetCounts = new Integer[postList.size()];
//                    Integer[] favoriteCounts = new Integer[postList.size()];
//                    Boolean[] favoriteds = new Boolean[postList.size()];

//                    List<String> names = new ArrayList<>();
//                    List<Boolean> retweeteds = new ArrayList<>();
//                    List<Long> createdAt = new ArrayList<>();
//                    List<String> profileImageUrls = new ArrayList<>();
//                    List<Integer> retweetCounts = new ArrayList<>();
//                    List<Integer> favoriteCounts = new ArrayList<>();
//                    List<Boolean> favoriteds = new ArrayList<>();


                    //looping through all the texts and inserting the text inside the string array
                    for (int i = 0; i < response.body().size(); i++) {

                        for (int j = 0; j < response.body().get(i).getTrends().size(); j++) {


//                        _ids[i] = postList.get(i).getId();
//                        texts[i] = postList.get(i).getText();
//                        sentiments[i] = postList.get(i).getSentimentAnalysis();

//                        names.add(postList.get(i).getName());
//                        retweeteds.add(postList.get(i).getRetweeted());
//                        createdAt.add(postList.get(i).getCreatedAt());
//                        profileImageUrls.add(postList.get(i).getProfileImageUrl());
//                        retweetCounts.add(postList.get(i).getRetweetCount());
//                        favoriteCounts.add(postList.get(i).getFavoriteCount());
//                        favoriteds.add(postList.get(i).getFavorited());

                            mTrendNames.add(response.body().get(i).getTrends().get(j).getName());

//                        mNames.add(postList.get(i).getName());
//                        mRetweeteds.add(postList.get(i).getRetweeted());
//                        mCreatedAt.add(postList.get(i).getCreatedAt());
//                        mProfileImageUrls.add(postList.get(i).getProfileImageUrl());
//                        mRetweetCounts.add(postList.get(i).getRetweetCount());
//                        mFavoriteCounts.add(postList.get(i).getFavoriteCount());
//                        mFavoriteds.add(postList.get(i).getFavorited());

//                        names[i] = postList.get(i).getName();
//                        retweeteds[i] = postList.get(i).getRetweeted();
//                        createdAt[i] = postList.get(i).getCreatedAt();
//                        profileImageUrls[i] = postList.get(i).getProfileImageUrl();
//                        retweetCounts[i] = postList.get(i).getRetweetCount();
//                        favoriteCounts[i] = postList.get(i).getFavoriteCount();
//                        favoriteds[i] = postList.get(i).getFavorited();


//                        Log.d(TAG, "onResponse: Sentiment = " + sentiments[i]);
                            //names[i] = postList.get(i).getUser();

                            //mRTweets.get(i).concat(texts[i]);


                            //List<Integer> newList = new ArrayList<Home>(texts);
                        }
                    }

//                    m_Ids = Arrays.asList(_ids);
//                    mTweets = Arrays.asList(texts);
//                    mSentiments = Arrays.asList(sentiments);
//                    mNames = Arrays.asList(names);
//                    mCreatedAt = Arrays.asList(createdAt);
//                    mFavoriteCounts = Arrays.asList(favoriteCounts);
//                    mFavoriteds = Arrays.asList(favoriteds);
//                    mProfileImageUrls = Arrays.asList(profileImageUrls);
//                    mRetweetCounts = Arrays.asList(retweetCounts);
//                    mRetweeteds = Arrays.asList(retweeteds);


//                    for (int i = 0; i < noOfTweets; i++ ){
//                        mImages.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
//                        mNames.add("Havasu Falls");
//                        mTimes.add("2 days ago");
//                    }


                    //mNames = Arrays.asList(names);

                    //displaying the string array into listview
                    //listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, heroes));

                    //displaying the string array into recycler view
                    //HomeAdapter adapter = new HomeAdapter(getContext(), mRTweets);

                    pbHashtag.setVisibility(View.GONE);

                    //recyclerView.setLayoutManager(manager);
                    TrendsAdapter trendsAdapter = new TrendsAdapter(getContext(), mTrendNames);
                    rvTrends.setAdapter(trendsAdapter);
                    rvTrends.setLayoutManager(new LinearLayoutManager(getContext()));



                /*recyclerView = findViewById(R.id.recycler_view);
                LinearLayoutManager manager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(manager);
                recyclerView.setHasFixedSize(true);
                adapter = new MyAdapter();
                recyclerView.setAdapter(adapter);*/


//                try {

                    /*s.set(0,response.body().getText());
                    Toast.makeText(getContext(), s.get(0), Toast.LENGTH_LONG).show();*/

                    //Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
                /*} catch (IOException e) {
                    e.printStackTrace();
                }*/

                /*if(response.code() == 201){
                    Home post = new Home();
                    post = response.body();
                    Toast.makeText(getContext(), post.getText(), Toast.LENGTH_LONG).show();

                }else if(response.code() == 422){
                    Toast.makeText(getContext(), "Invalid email or password", Toast.LENGTH_LONG).show();
                }*/

                    //Toast.makeText(getContext(), response.body().getText(), Toast.LENGTH_LONG).show();


//                postList = response.body().getText();
                /*adapter = new HomeAdapter(get, postList);
                recyclerView.setAdapter(adapter);*/

                    //HomeAdapter adapter = new HomeAdapter(getContext(), mNames, mImages, mTimes, mTweets);

                /*HomeAdapter adapter = new HomeAdapter(getContext(), postList);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));*/


                } catch (NullPointerException e) {
                    Log.e(TAG, "onResponse: " +
                            " " + e.getMessage() );
//                    noOfTweets = 0;
                }


            }


            @Override
            public void onFailure(Call<List<TrendDetail>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


    public void apiResponse() {


        //vars
        String keyword = "";





//        rvTrends.setVisibility(View.INVISIBLE);
        rvTrends.setVisibility(View.GONE);
//        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
        pbHashtag.setVisibility(View.VISIBLE);

        keyword = etKeyword.getText().toString();

        Log.d(TAG, "apiResponse: Keyword: " + keyword);

        //now making the call object
        //Here using the api method that we created inside the api interface
        Call<List<Hashtag>> call = RetrofitClient
                .getInstance()
                .getApi()
                .getHashtagTweets(keyword);


        call.enqueue(new Callback<List<Hashtag>>() {

            @Override
            public void onResponse(Call<List<Hashtag>> call, Response<List<Hashtag>> response) {

                Log.d(TAG, "onResponse: Inside onResponse");

                if (response.code() == 400) {
                    try {
                        Log.e("Error code 400", response.errorBody().string());
                    } catch (Exception e) {
                        Log.e(TAG, "Exception thrown by Error code 400");
                    }
                }


                try {


                    //private HomeAdapter adapter;
                    List<Hashtag> postList;


//    private static int noOfTweets = 0;

                    List<String> m_Ids = new ArrayList<>();

                    //private ArrayList<String> mNames = new ArrayList<>();
                    List<String> mNames = new ArrayList<>();

                    //private ArrayList<String> mTweets = new ArrayList<>();

                    //private ArrayList<String> mRTweets = new ArrayList<>();
                    List<String> mTweets = new ArrayList<>();
                    List<String> mSentiments = new ArrayList<>();

                    List<Boolean> mRetweeteds = new ArrayList<>();
                    List<Long> mCreatedAt = new ArrayList<>();
                    List<String> mProfileImageUrls = new ArrayList<>();
                    List<Integer> mRetweetCounts = new ArrayList<>();
                    List<Integer> mFavoriteCounts = new ArrayList<>();
                    List<Boolean> mFavoriteds = new ArrayList<>();



                    String appreciative = "Appreciated";
                    String abusive = "Abusive";
                    String suggestive = "Suggestion";
                    String seriousConcern = "Serious Concern";
                    String disappointed = "Disappointed";


                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

                    String logReg = "LogReg";
                    String rnn = "RNN";
                    String naiveBayes = "NaiveBayes";

                    String currentSelectedAlgo = sharedPreferences.getString(KEY_SELECTED_ALGO, "LogReg");

                    String tempSelectedAlgo = "";



                    //In this point we got our Home list
                    postList = response.body();

                    Log.d(TAG, "onResponse: Home list: " + postList);


//                    noOfTweets = postList.size();


                    //Creating a String array for the ListView
//                    String[] _ids = new String[postList.size()];
//                    String[] texts = new String[postList.size()];
//                    String[] sentiments = new String[postList.size()];
                    //String[] names = new String[postList.size()];

//                    String[] names = new String[postList.size()];
//                    Boolean[] retweeteds = new Boolean[postList.size()];
//                    Long[] createdAt = new Long[postList.size()];
//                    String[] profileImageUrls = new String[postList.size()];
//                    Integer[] retweetCounts = new Integer[postList.size()];
//                    Integer[] favoriteCounts = new Integer[postList.size()];
//                    Boolean[] favoriteds = new Boolean[postList.size()];

//                    List<String> names = new ArrayList<>();
//                    List<Boolean> retweeteds = new ArrayList<>();
//                    List<Long> createdAt = new ArrayList<>();
//                    List<String> profileImageUrls = new ArrayList<>();
//                    List<Integer> retweetCounts = new ArrayList<>();
//                    List<Integer> favoriteCounts = new ArrayList<>();
//                    List<Boolean> favoriteds = new ArrayList<>();


                    //looping through all the texts and inserting the text inside the string array
                    for (int i = 0; i < postList.size(); i++) {


                        if (currentSelectedAlgo.equals(logReg)) {

                            tempSelectedAlgo = postList.get(i).getSentimentAnalysisLogreg();

                        } else if (currentSelectedAlgo.equals(rnn)) {

                            tempSelectedAlgo = postList.get(i).getSentimentAnalysisRnn();

                        } else {

                            tempSelectedAlgo = postList.get(i).getSentimentAnalysisNaiveBayes();

                        }



//                        _ids[i] = postList.get(i).getId();
//                        texts[i] = postList.get(i).getText();
//                        sentiments[i] = postList.get(i).getSentimentAnalysisLogreg();

//                        names.add(postList.get(i).getName());
//                        retweeteds.add(postList.get(i).getRetweeted());
//                        createdAt.add(postList.get(i).getCreatedAt());
//                        profileImageUrls.add(postList.get(i).getProfileImageUrl());
//                        retweetCounts.add(postList.get(i).getRetweetCount());
//                        favoriteCounts.add(postList.get(i).getFavoriteCount());
//                        favoriteds.add(postList.get(i).getFavorited());


//                        m_Ids.add(postList.get(i).getId());
//                        mTweets.add(postList.get(i).getText());
//                        mSentiments.add(postList.get(i).getSentimentAnalysisLogreg());
//
//                        mNames.add(postList.get(i).getName());
//                        mRetweeteds.add(postList.get(i).getRetweeted());
//                        mCreatedAt.add(postList.get(i).getCreatedAt());
//                        mProfileImageUrls.add(postList.get(i).getProfileImageUrl());
//                        mRetweetCounts.add(postList.get(i).getRetweetCount());
//                        mFavoriteCounts.add(postList.get(i).getFavoriteCount());
//                        mFavoriteds.add(postList.get(i).getFavorited());





                        if (sentimentFilter.equals(appreciative)) {

                            if (appreciative.equals(tempSelectedAlgo)) {

//                            if (appreciative.contentEquals(postList.get(i).getSentimentAnalysisLogreg() ) ) {


//                        if (postList.get(i).getSentimentAnalysisLogreg().equals("Appreciated")) {


//                                _ids[i] = postList.get(i).getId();
//                                texts[i] = postList.get(i).getText();
//                                sentiments[i] = postList.get(i).getSentimentAnalysisLogreg();


                                m_Ids.add(postList.get(i).getId());
                                mTweets.add(postList.get(i).getText());
                                mSentiments.add(tempSelectedAlgo);
//
                                mNames.add(postList.get(i).getName());
                                mRetweeteds.add(postList.get(i).getRetweeted());
                                mCreatedAt.add(postList.get(i).getCreatedAt());
                                mProfileImageUrls.add(postList.get(i).getProfileImageUrl());
                                mRetweetCounts.add(postList.get(i).getRetweetCount());
                                mFavoriteCounts.add(postList.get(i).getFavoriteCount());
                                mFavoriteds.add(postList.get(i).getFavorited());


//
//
                            }


                        } else if (sentimentFilter.equals(abusive)) {

                            if (abusive.equals(tempSelectedAlgo)) {

//                            if (appreciative.contentEquals(postList.get(i).getSentimentAnalysisLogreg() ) ) {


//                        if (postList.get(i).getSentimentAnalysisLogreg().equals("Appreciated")) {


//                                _ids[i] = postList.get(i).getId();
//                                texts[i] = postList.get(i).getText();
//                                sentiments[i] = postList.get(i).getSentimentAnalysisLogreg();


                                m_Ids.add(postList.get(i).getId());
                                mTweets.add(postList.get(i).getText());
                                mSentiments.add(tempSelectedAlgo);
//
                                mNames.add(postList.get(i).getName());
                                mRetweeteds.add(postList.get(i).getRetweeted());
                                mCreatedAt.add(postList.get(i).getCreatedAt());
                                mProfileImageUrls.add(postList.get(i).getProfileImageUrl());
                                mRetweetCounts.add(postList.get(i).getRetweetCount());
                                mFavoriteCounts.add(postList.get(i).getFavoriteCount());
                                mFavoriteds.add(postList.get(i).getFavorited());
//
//
                            }
                        } else if (sentimentFilter.equals(suggestive)) {

                            if (suggestive.equals(tempSelectedAlgo)) {

//                            if (appreciative.contentEquals(postList.get(i).getSentimentAnalysisLogreg() ) ) {


//                        if (postList.get(i).getSentimentAnalysisLogreg().equals("Appreciated")) {


//                                _ids[i] = postList.get(i).getId();
//                                texts[i] = postList.get(i).getText();
//                                sentiments[i] = postList.get(i).getSentimentAnalysisLogreg();


                                m_Ids.add(postList.get(i).getId());
                                mTweets.add(postList.get(i).getText());
                                mSentiments.add(tempSelectedAlgo);
//
                                mNames.add(postList.get(i).getName());
                                mRetweeteds.add(postList.get(i).getRetweeted());
                                mCreatedAt.add(postList.get(i).getCreatedAt());
                                mProfileImageUrls.add(postList.get(i).getProfileImageUrl());
                                mRetweetCounts.add(postList.get(i).getRetweetCount());
                                mFavoriteCounts.add(postList.get(i).getFavoriteCount());
                                mFavoriteds.add(postList.get(i).getFavorited());
//
//
                            }
                        } else if (sentimentFilter.equals(seriousConcern)) {

                            if (seriousConcern.equals(tempSelectedAlgo)) {

//                            if (appreciative.contentEquals(postList.get(i).getSentimentAnalysisLogreg() ) ) {


//                        if (postList.get(i).getSentimentAnalysisLogreg().equals("Appreciated")) {


//                                _ids[i] = postList.get(i).getId();
//                                texts[i] = postList.get(i).getText();
//                                sentiments[i] = postList.get(i).getSentimentAnalysisLogreg();


                                m_Ids.add(postList.get(i).getId());
                                mTweets.add(postList.get(i).getText());
                                mSentiments.add(tempSelectedAlgo);
//
                                mNames.add(postList.get(i).getName());
                                mRetweeteds.add(postList.get(i).getRetweeted());
                                mCreatedAt.add(postList.get(i).getCreatedAt());
                                mProfileImageUrls.add(postList.get(i).getProfileImageUrl());
                                mRetweetCounts.add(postList.get(i).getRetweetCount());
                                mFavoriteCounts.add(postList.get(i).getFavoriteCount());
                                mFavoriteds.add(postList.get(i).getFavorited());
//
//
                            }
                        } else if (sentimentFilter.equals(disappointed)) {

                            if (disappointed.equals(tempSelectedAlgo)) {

//                            if (appreciative.contentEquals(postList.get(i).getSentimentAnalysisLogreg() ) ) {


//                        if (postList.get(i).getSentimentAnalysisLogreg().equals("Appreciated")) {


//                                _ids[i] = postList.get(i).getId();
//                                texts[i] = postList.get(i).getText();
//                                sentiments[i] = postList.get(i).getSentimentAnalysisLogreg();


                                m_Ids.add(postList.get(i).getId());
                                mTweets.add(postList.get(i).getText());
                                mSentiments.add(tempSelectedAlgo);
//
                                mNames.add(postList.get(i).getName());
                                mRetweeteds.add(postList.get(i).getRetweeted());
                                mCreatedAt.add(postList.get(i).getCreatedAt());
                                mProfileImageUrls.add(postList.get(i).getProfileImageUrl());
                                mRetweetCounts.add(postList.get(i).getRetweetCount());
                                mFavoriteCounts.add(postList.get(i).getFavoriteCount());
                                mFavoriteds.add(postList.get(i).getFavorited());
//
//
                            }
                        } else {

//                            _ids[i] = postList.get(i).getId();
//                            texts[i] = postList.get(i).getText();
//                            sentiments[i] = postList.get(i).getSentimentAnalysisLogreg();

                            m_Ids.add(postList.get(i).getId());
                            mTweets.add(postList.get(i).getText());
                            mSentiments.add(tempSelectedAlgo);

                            mNames.add(postList.get(i).getName());
                            mRetweeteds.add(postList.get(i).getRetweeted());
                            mCreatedAt.add(postList.get(i).getCreatedAt());
                            mProfileImageUrls.add(postList.get(i).getProfileImageUrl());
                            mRetweetCounts.add(postList.get(i).getRetweetCount());
                            mFavoriteCounts.add(postList.get(i).getFavoriteCount());
                            mFavoriteds.add(postList.get(i).getFavorited());


//                            Log.d(TAG, "onResponse: Sentiment = " + postList.get(i).getSentimentAnalysisLogreg());
                            //names[i] = postList.get(i).getUser();

                            //mRTweets.get(i).concat(texts[i]);


                            //List<Integer> newList = new ArrayList<Home>(texts);

//                        }

                        }






//                        names[i] = postList.get(i).getName();
//                        retweeteds[i] = postList.get(i).getRetweeted();
//                        createdAt[i] = postList.get(i).getCreatedAt();
//                        profileImageUrls[i] = postList.get(i).getProfileImageUrl();
//                        retweetCounts[i] = postList.get(i).getRetweetCount();
//                        favoriteCounts[i] = postList.get(i).getFavoriteCount();
//                        favoriteds[i] = postList.get(i).getFavorited();


                        Log.d(TAG, "onResponse: Sentiment = " + postList.get(i).getText());
                        //names[i] = postList.get(i).getUser();

                        //mRTweets.get(i).concat(texts[i]);



                        //List<Integer> newList = new ArrayList<Home>(texts);
                    }
//
//                    m_Ids = Arrays.asList(_ids);
//                    mTweets = Arrays.asList(texts);
//                    mSentiments = Arrays.asList(sentiments);
//                    mNames = Arrays.asList(names);
//                    mCreatedAt = Arrays.asList(createdAt);
//                    mFavoriteCounts = Arrays.asList(favoriteCounts);
//                    mFavoriteds = Arrays.asList(favoriteds);
//                    mProfileImageUrls = Arrays.asList(profileImageUrls);
//                    mRetweetCounts = Arrays.asList(retweetCounts);
//                    mRetweeteds = Arrays.asList(retweeteds);


//                    for (int i = 0; i < noOfTweets; i++ ){
//                        mImages.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
//                        mNames.add("Havasu Falls");
//                        mTimes.add("2 days ago");
//                    }


                    //mNames = Arrays.asList(names);

                    //displaying the string array into listview
                    //listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, heroes));

                    //displaying the string array into recycler view
                    //HomeAdapter adapter = new HomeAdapter(getContext(), mRTweets);

                    pbHashtag.setVisibility(View.GONE);

                    recyclerView.setVisibility(View.VISIBLE);


                    //recyclerView.setLayoutManager(manager);
                    HashtagAdapter adapter = new HashtagAdapter(getContext(), m_Ids, mNames,
                            mTweets, mSentiments, mRetweeteds, mCreatedAt, mProfileImageUrls, mRetweetCounts,
                            mFavoriteCounts, mFavoriteds);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



                /*recyclerView = findViewById(R.id.recycler_view);
                LinearLayoutManager manager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(manager);
                recyclerView.setHasFixedSize(true);
                adapter = new MyAdapter();
                recyclerView.setAdapter(adapter);*/


//                try {

                    /*s.set(0,response.body().getText());
                    Toast.makeText(getContext(), s.get(0), Toast.LENGTH_LONG).show();*/

                    //Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
                /*} catch (IOException e) {
                    e.printStackTrace();
                }*/

                /*if(response.code() == 201){
                    Home post = new Home();
                    post = response.body();
                    Toast.makeText(getContext(), post.getText(), Toast.LENGTH_LONG).show();

                }else if(response.code() == 422){
                    Toast.makeText(getContext(), "Invalid email or password", Toast.LENGTH_LONG).show();
                }*/

                    //Toast.makeText(getContext(), response.body().getText(), Toast.LENGTH_LONG).show();


//                postList = response.body().getText();
                /*adapter = new HomeAdapter(get, postList);
                recyclerView.setAdapter(adapter);*/

                    //HomeAdapter adapter = new HomeAdapter(getContext(), mNames, mImages, mTimes, mTweets);

                /*HomeAdapter adapter = new HomeAdapter(getContext(), postList);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));*/


                } catch (NullPointerException e) {
                    Log.e(TAG, "onResponse: " +
                            " " + e.getMessage() );
//                    noOfTweets = 0;
                }


            }


            @Override
            public void onFailure(Call<List<Hashtag>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


//    public static List<Hashtag> getPostList() {
//        return postList;
//    }
//
//    public static void setPostList(List<Hashtag> postList) {
//        HashtagFragment.postList = postList;
//    }





    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        getActivity().getMenuInflater().inflate(R.menu.menu, menu);


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


        super.onCreateOptionsMenu(menu, inflater);
    }

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