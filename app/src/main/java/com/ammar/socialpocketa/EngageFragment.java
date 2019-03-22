package com.ammar.socialpocketa;


import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.ammar.socialpocketa.utils.DbImageUtil;
import com.ammar.socialpocketa.data.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class EngageFragment extends Fragment {


    private static final String TAG = "EngageFragment";

    DatabaseHelper mDatabaseHelper;

    RecyclerView rvEngage;

    ProgressBar pbEngage;


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



    public EngageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_engage, container, false);


        rvEngage = rootView.findViewById(R.id.rv_engage);
        pbEngage = rootView.findViewById(R.id.pb_engage);

        mDatabaseHelper = new DatabaseHelper(getContext());

        populateListView();


        return rootView;
    }




    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView");

        // get the data and append to a list
        Cursor data = mDatabaseHelper.getData();
        List<String> listData = new ArrayList<>();



        List<Bitmap> imageData = new ArrayList<>();

        Bitmap bitmapImage;

        int i = 0;




        while(data.moveToNext()){
            // get the value from the database in column 1
            // then add it to the ArrayList
            listData.add(data.getString(1));


            byte[] image = data.getBlob(2);

            bitmapImage = DbImageUtil.getImage(image);

            imageData.add(bitmapImage);

            Log.d(TAG, "populateListView: ImageData in the form of bitmapImage: " + imageData.get(i));

            i++;




        }

        EngageAdapter engageAdapter = new EngageAdapter(listData);


        for (int j = 0; j < listData.size(); j++) {
            m_Ids.add("id1");
            mNames.add("id1");
            mSentiments.add("id1");
            mRetweeteds.add(true);
            mCreatedAt.add("id1");
//            mProfileImageUrls.add("id1");
            mRetweetCounts.add("id1");
            mFavoriteCounts.add("id1");
            mFavoriteds.add(true);
        }

        pbEngage.setVisibility(View.GONE);

//        HomeAdapter adapter = new HomeAdapter(getContext(), m_Ids, mNames,
//                listData, mSentiments, mRetweeteds, mCreatedAt, mProfileImageUrls,
//                mRetweetCounts, mFavoriteCounts, mFavoriteds);
//        rvEngage.setAdapter(adapter);
//        rvEngage.setLayoutManager(new LinearLayoutManager(getContext()));



//        MentionsAdapter adapter = new MentionsAdapter(getContext(), m_Ids, mNames,
//                listData, mSentiments, mRetweeteds, mCreatedAt, mProfileImageUrls,
//                mRetweetCounts, mFavoriteCounts, mFavoriteds);


                EngageAdapter adapter = new EngageAdapter(getContext(), m_Ids, mNames,
                listData, mSentiments, mRetweeteds, mCreatedAt, imageData,
                mRetweetCounts, mFavoriteCounts, mFavoriteds);


        rvEngage.setAdapter(adapter);
        rvEngage.setLayoutManager(new LinearLayoutManager(getContext()));






        //create the list adapter and set the adapter
//        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
//        mListView.setAdapter(adapter);

//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String name = adapterView.getItemAtPosition(i).toString();
//                Log.d(TAG, "onItemClick: You Clicked on " + name);
//
//                // get the id associated with that name
//                Cursor data = mDatabaseHelper.getItemID(name);
//                int itemID = -1;
//                while(data.moveToNext()){
//                    itemID = data.getInt(0);
//                }
//                if(itemID > -1){
//                    Log.d(TAG, "onItemClick: The ID is: " + itemID);
//                    Intent editScreenIntent = new Intent(ListDataActivity.this, EditDataActivity.class);
//                    editScreenIntent.putExtra("id", itemID);
//                    editScreenIntent.putExtra("name", name);
//                    startActivity(editScreenIntent);
//                }
//                else {
//                    Toast.makeText(ListDataActivity.this, "No ID associated with that name", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

    }



}
