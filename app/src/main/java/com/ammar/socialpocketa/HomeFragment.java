package com.ammar.socialpocketa;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ammar.socialpocketa.models.Home;
import com.ammar.socialpocketa.sync.MentionService;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";


    Intent mServiceIntent;
    private MentionService mMentionService;

    Context ctx;


    public Context getCtx() {
        return ctx;
    }



    RecyclerView recyclerView;
    ProgressBar pbHome;
//    TextView tvEngage;
    RelativeLayout rlEngage;

    //private HomeAdapter adapter;
    private static List<Home> postList;

    //vars
    private static int noOfTweets = 0;

    private List<String> m_Ids = new ArrayList<>();

    //private ArrayList<String> mNames = new ArrayList<>();
    private List<String> mNames = new ArrayList<>();

//    private ArrayList<String> mImages = new ArrayList<>();
//
//    private ArrayList<String> mTimes = new ArrayList<>();
//    private ArrayList<String> mTweets = new ArrayList<>();

    //private ArrayList<String> mRTweets = new ArrayList<>();
    private List<String> mTweets = new ArrayList<>();

    private List<String> mSentiments = new ArrayList<>();

    private List<Boolean> mRetweeteds = new ArrayList<>();
    private List<String> mCreatedAt = new ArrayList<>();
    private List<String> mProfileImageUrls = new ArrayList<>();
    private List<String> mRetweetCounts = new ArrayList<>();
    private List<String> mFavoriteCounts = new ArrayList<>();
    private List<Boolean> mFavoriteds = new ArrayList<>();

    //private ArrayList<String> s = new ArrayList<>();

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        Log.d(TAG, "onCreate: started.");

        initImageBitmaps();
    }*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        Log.d(TAG, "onCreate: started.");



//        ctx = this;


        ctx = getActivity();

//        ctx = getContext();


        mMentionService = new MentionService(getCtx());
        mServiceIntent = new Intent(getCtx(), mMentionService.getClass());


        if (!isMyServiceRunning(mMentionService.getClass())) {
//            getActivity().startService(mServiceIntent);
        }


//        if (isInternetAvailable()) {
//
//            if (!isMyServiceRunning(mMentionService.getClass())) {
//                getActivity().startService(mServiceIntent);
//            }
//
//        }


        recyclerView = rootView.findViewById(R.id.rvPosts);
        pbHome = rootView.findViewById(R.id.pb_home);
//        tvEngage = rootView.findViewById(R.id.tv_engage);
        rlEngage = rootView.findViewById(R.id.rl_engage);

        pbHome.setVisibility(View.VISIBLE);

        apiResponse();

//        initImageBitmaps();


//        tvEngage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

//        rlEngage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getActivity(), "Engage Clicked", Toast.LENGTH_SHORT).show();
//            }
//        });


        return rootView;
    }




//    public boolean isInternetAvailable() {
//        try {
//            final InetAddress address = InetAddress.getByName("www.google.com");
//            return !address.equals("");
//
//        } catch (UnknownHostException e) {
//            // Log error
//
//            Log.e(TAG, "isInternetAvailable: Error Occured while checking for Internet Connection.");
//
//        }
//        return false;
//    }



    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.i ("isMyServiceRunning?", true+"");
                return true;
            }
        }
        Log.i ("isMyServiceRunning?", false+"");
        return false;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        getActivity().stopService(mServiceIntent);
        Log.i("MAINACT", "onDestroy!");
        super.onDestroy();

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





    public void apiResponse() {

        //now making the call object
        //Here using the api method that we created inside the api interface
        Call<List<Home>> call = RetrofitClient
                .getInstance()
                .getApi()
                .getPosts();


        call.enqueue(new Callback<List<Home>>() {

            @Override
            public void onResponse(Call<List<Home>> call, Response<List<Home>> response) {


                //In this point we got our Home list
                postList = response.body();

                try {
                    noOfTweets = postList.size();


                //Creating a String array for the ListView
                String[] _ids = new String[postList.size()];
                String[] texts = new String[postList.size()];
                //String[] names = new String[postList.size()];

                //looping through all the texts and inserting the text inside the string array
                for (int i = 0; i < postList.size(); i++) {

                    _ids[i] = postList.get(i).getId();
                    texts[i] = postList.get(i).getText();


                    mNames.add(postList.get(i).getName());
                    mRetweeteds.add(postList.get(i).getRetweeted());
                    mCreatedAt.add(postList.get(i).getCreatedAt());
                    mProfileImageUrls.add(postList.get(i).getProfileImageUrl());
                    mRetweetCounts.add(postList.get(i).getRetweetCount());
                    mFavoriteCounts.add(postList.get(i).getFavoriteCount());
                    mFavoriteds.add(postList.get(i).getFavorited());


//                    Log.d(TAG, "onResponse: Sentiment = " + sentiments[i]);

                    //names[i] = postList.get(i).getUser();

                    //mRTweets.get(i).concat(texts[i]);



                    //List<Integer> newList = new ArrayList<Home>(texts);
                }

                m_Ids = Arrays.asList(_ids);
                mTweets = Arrays.asList(texts);

                for (int i = 0; i < noOfTweets; i++ ){
//                    mImages.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
//                    mNames.add("Havasu Falls");
//                    mTimes.add("2 days ago");
                    mSentiments.add("");

                }

                //mNames = Arrays.asList(names);

                //displaying the string array into listview
                //listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, heroes));

                //displaying the string array into recycler view
                //HomeAdapter adapter = new HomeAdapter(getContext(), mRTweets);

                pbHome.setVisibility(View.GONE);

                //recyclerView.setLayoutManager(manager);
                HomeAdapter adapter = new HomeAdapter(getContext(), m_Ids, mNames,
                        mTweets, mSentiments, mRetweeteds, mCreatedAt, mProfileImageUrls,
                        mRetweetCounts, mFavoriteCounts, mFavoriteds);
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
                    Log.e(TAG, "onResponse: NullPointerException " + e.getMessage() );
                    noOfTweets = 0;
                }


            }


            @Override
            public void onFailure(Call<List<Home>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


//    public static List<Home> getPostList() {
//        return postList;
//    }
//
//    public static void setPostList(List<Home> postList) {
//        HomeFragment.postList = postList;
//    }


}