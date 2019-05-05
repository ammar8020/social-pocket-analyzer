package com.ammar.socialpocketa;


import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

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


//    private List<String> m_Ids = new ArrayList<>();
//
//    //private ArrayList<String> mNames = new ArrayList<>();
//    private List<String> mNames = new ArrayList<>();
//
////    private ArrayList<String> mImages = new ArrayList<>();
////
////    private ArrayList<String> mTimes = new ArrayList<>();
////    private ArrayList<String> mTweets = new ArrayList<>();
//
//    //private ArrayList<String> mRTweets = new ArrayList<>();
//    private List<String> mTweets = new ArrayList<>();
//
////    private List<String> mSentiments = new ArrayList<>();
//
//    private List<Boolean> mRetweeteds = new ArrayList<>();
//    private List<String> mCreatedAt = new ArrayList<>();
//    private List<String> mProfileImageUrls = new ArrayList<>();
//    private List<String> mRetweetCounts = new ArrayList<>();
//    private List<String> mFavoriteCounts = new ArrayList<>();
//    private List<Boolean> mFavoriteds = new ArrayList<>();
//
//    private List<String> mIdStr = new ArrayList<>();
//    private List<String> mSentimentAnalysisLogReg = new ArrayList<>();
//    private List<String> mSentimentAnalysisNaiveBayes = new ArrayList<>();
//    private List<String> mSentimentAnalysisRnn = new ArrayList<>();
//    private List<String> mScreenName = new ArrayList<>();



    public static String sentimentFilter = "";



    public EngageFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

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



        pbEngage.setVisibility(View.VISIBLE);

        rvEngage.setVisibility(View.INVISIBLE);



        List<String> m_Ids = new ArrayList<>();

        //private ArrayList<String> mNames = new ArrayList<>();
        List<String> mNames = new ArrayList<>();

//    private ArrayList<String> mImages = new ArrayList<>();
//
//    private ArrayList<String> mTimes = new ArrayList<>();
//    private ArrayList<String> mTweets = new ArrayList<>();

        //private ArrayList<String> mRTweets = new ArrayList<>();
        List<String> mTweets = new ArrayList<>();

//    private List<String> mSentiments = new ArrayList<>();

        List<Boolean> mRetweeteds = new ArrayList<>();
        List<String> mCreatedAt = new ArrayList<>();
        List<String> mProfileImageUrls = new ArrayList<>();
        List<String> mRetweetCounts = new ArrayList<>();
        List<String> mFavoriteCounts = new ArrayList<>();
        List<Boolean> mFavoriteds = new ArrayList<>();

        List<String> mIdStr = new ArrayList<>();
        List<String> mSentimentAnalysisLogReg = new ArrayList<>();
        List<String> mSentimentAnalysisNaiveBayes = new ArrayList<>();
        List<String> mSentimentAnalysisRnn = new ArrayList<>();
        List<String> mScreenName = new ArrayList<>();







        // get the data and append to a list
        Cursor data = mDatabaseHelper.getData();
        List<String> listData = new ArrayList<>();



        List<Bitmap> imageData = new ArrayList<>();

        Bitmap bitmapImage;

//        int i = 0;



        String appreciative = "Appreciated";
        String abusive = "Abusive";
        String suggestive = "Suggestion";
        String seriousConcern = "Serious Concern";
        String disappointed = "Disappointed";


        Boolean appreciativeFound = false;
        Boolean abusiveFound = false;
        Boolean suggestiveFound = false;
        Boolean seriousConcernFound = false;
        Boolean disappointedFound = false;


        if (data == null) {

            pbEngage.setVisibility(View.INVISIBLE);
            Toast.makeText(getActivity(), "No Mentions found", Toast.LENGTH_LONG).show();

        } else {


            while (data.moveToNext()) {
                // get the value from the database in column 1
                // then add it to the ArrayList
//            listData.add(data.getString(1));
//
//
//            byte[] image = data.getBlob(2);
//
//            bitmapImage = DbImageUtil.getImage(image);
//
//            imageData.add(bitmapImage);
//
//            Log.d(TAG, "populateListView: ImageData in the form of bitmapImage: " + imageData.get(i));
//
//
//
//            m_Ids.add(data.getString(3));
//            mIdStr.add(data.getString(4));
//            mNames.add(data.getString(5));
//            mScreenName.add(data.getString(6));
//            mCreatedAt.add(data.getString(7));
//            mRetweetCounts.add(data.getString(8));
//            mFavoriteCounts.add(data.getString(9));
//            mSentimentAnalysisLogReg.add(data.getString(10));
//            mSentimentAnalysisNaiveBayes.add(data.getString(11));
//            mSentimentAnalysisRnn.add(data.getString(12));
//
//            mRetweeteds.add(true);
//            mFavoriteds.add(true);


                if (sentimentFilter.equals(appreciative)) {

                    if (appreciative.equals(data.getString(10))) {


                        appreciativeFound = true;

//                     get the value from the database in column 1
//                     then add it to the ArrayList
                        listData.add(data.getString(1));


                        byte[] image = data.getBlob(2);

                        bitmapImage = DbImageUtil.getImage(image);

                        imageData.add(bitmapImage);

//                    Log.d(TAG, "populateListView: ImageData in the form of bitmapImage: " + imageData.get(i));


                        m_Ids.add(data.getString(3));
                        mIdStr.add(data.getString(4));
                        mNames.add(data.getString(5));
                        mScreenName.add(data.getString(6));
                        mCreatedAt.add(data.getString(7));
                        mRetweetCounts.add(data.getString(8));
                        mFavoriteCounts.add(data.getString(9));
                        mSentimentAnalysisLogReg.add(data.getString(10));
                        mSentimentAnalysisNaiveBayes.add(data.getString(11));
                        mSentimentAnalysisRnn.add(data.getString(12));

                        mRetweeteds.add(true);
                        mFavoriteds.add(true);

                    }

                } else if (sentimentFilter.equals(abusive)) {

                    if (abusive.equals(data.getString(10))) {

                        abusiveFound = true;

//                     get the value from the database in column 1
//                     then add it to the ArrayList
                        listData.add(data.getString(1));


                        byte[] image = data.getBlob(2);

                        bitmapImage = DbImageUtil.getImage(image);

                        imageData.add(bitmapImage);

//                        Log.d(TAG, "populateListView: ImageData in the form of bitmapImage: " + imageData.get(i));


                        m_Ids.add(data.getString(3));
                        mIdStr.add(data.getString(4));
                        mNames.add(data.getString(5));
                        mScreenName.add(data.getString(6));
                        mCreatedAt.add(data.getString(7));
                        mRetweetCounts.add(data.getString(8));
                        mFavoriteCounts.add(data.getString(9));
                        mSentimentAnalysisLogReg.add(data.getString(10));
                        mSentimentAnalysisNaiveBayes.add(data.getString(11));
                        mSentimentAnalysisRnn.add(data.getString(12));

                        mRetweeteds.add(true);
                        mFavoriteds.add(true);

                    }

                } else if (sentimentFilter.equals(suggestive)) {

                    if (suggestive.equals(data.getString(10))) {

                        suggestiveFound = true;

//                     get the value from the database in column 1
//                     then add it to the ArrayList
                        listData.add(data.getString(1));


                        byte[] image = data.getBlob(2);

                        bitmapImage = DbImageUtil.getImage(image);

                        imageData.add(bitmapImage);

//                            Log.d(TAG, "populateListView: ImageData in the form of bitmapImage: " + imageData.get(i));


                        m_Ids.add(data.getString(3));
                        mIdStr.add(data.getString(4));
                        mNames.add(data.getString(5));
                        mScreenName.add(data.getString(6));
                        mCreatedAt.add(data.getString(7));
                        mRetweetCounts.add(data.getString(8));
                        mFavoriteCounts.add(data.getString(9));
                        mSentimentAnalysisLogReg.add(data.getString(10));
                        mSentimentAnalysisNaiveBayes.add(data.getString(11));
                        mSentimentAnalysisRnn.add(data.getString(12));

                        mRetweeteds.add(true);
                        mFavoriteds.add(true);
                    }


                } else if (sentimentFilter.equals(seriousConcern)) {

                    if (seriousConcern.equals(data.getString(10))) {

                        seriousConcernFound= true;

//                     get the value from the database in column 1
//                     then add it to the ArrayList
                        listData.add(data.getString(1));


                        byte[] image = data.getBlob(2);

                        bitmapImage = DbImageUtil.getImage(image);

                        imageData.add(bitmapImage);

//                                Log.d(TAG, "populateListView: ImageData in the form of bitmapImage: " + imageData.get(i));


                        m_Ids.add(data.getString(3));
                        mIdStr.add(data.getString(4));
                        mNames.add(data.getString(5));
                        mScreenName.add(data.getString(6));
                        mCreatedAt.add(data.getString(7));
                        mRetweetCounts.add(data.getString(8));
                        mFavoriteCounts.add(data.getString(9));
                        mSentimentAnalysisLogReg.add(data.getString(10));
                        mSentimentAnalysisNaiveBayes.add(data.getString(11));
                        mSentimentAnalysisRnn.add(data.getString(12));

                        mRetweeteds.add(true);
                        mFavoriteds.add(true);

                    }


                } else if (sentimentFilter.equals(disappointed)) {

                    if (disappointed.equals(data.getString(10))) {

                        disappointedFound = true;

//                     get the value from the database in column 1
//                     then add it to the ArrayList
                        listData.add(data.getString(1));


                        byte[] image = data.getBlob(2);

                        bitmapImage = DbImageUtil.getImage(image);

                        imageData.add(bitmapImage);

//                                    Log.d(TAG, "populateListView: ImageData in the form of bitmapImage: " + imageData.get(i));


                        m_Ids.add(data.getString(3));
                        mIdStr.add(data.getString(4));
                        mNames.add(data.getString(5));
                        mScreenName.add(data.getString(6));
                        mCreatedAt.add(data.getString(7));
                        mRetweetCounts.add(data.getString(8));
                        mFavoriteCounts.add(data.getString(9));
                        mSentimentAnalysisLogReg.add(data.getString(10));
                        mSentimentAnalysisNaiveBayes.add(data.getString(11));
                        mSentimentAnalysisRnn.add(data.getString(12));

                        mRetweeteds.add(true);
                        mFavoriteds.add(true);

                    }

                } else {


//                                    get the value from the database in column 1
//                     then add it to the ArrayList
                    listData.add(data.getString(1));


                    byte[] image = data.getBlob(2);

                    bitmapImage = DbImageUtil.getImage(image);

                    imageData.add(bitmapImage);

//                                    Log.d(TAG, "populateListView: ImageData in the form of bitmapImage: " + imageData.get(i));


                    m_Ids.add(data.getString(3));
                    mIdStr.add(data.getString(4));
                    mNames.add(data.getString(5));
                    mScreenName.add(data.getString(6));
                    mCreatedAt.add(data.getString(7));
                    mRetweetCounts.add(data.getString(8));
                    mFavoriteCounts.add(data.getString(9));
                    mSentimentAnalysisLogReg.add(data.getString(10));
                    mSentimentAnalysisNaiveBayes.add(data.getString(11));
                    mSentimentAnalysisRnn.add(data.getString(12));

                    mRetweeteds.add(true);
                    mFavoriteds.add(true);


                }


//            i++;


            }





            if(sentimentFilter.equals(appreciative)) {


                if (appreciativeFound.equals(false)) {


                    Toast.makeText(getActivity(), "No Appreciative Mention found", Toast.LENGTH_SHORT).show();

                }


            } else if (sentimentFilter.equals(abusive)) {

                if (abusiveFound.equals(false)) {


                    Toast.makeText(getActivity(), "No Abusive Mention found", Toast.LENGTH_SHORT).show();

                }


            } else if (sentimentFilter.equals(suggestive)) {

                if (suggestiveFound.equals(false)) {


                    Toast.makeText(getActivity(), "No Suggestive Mention found", Toast.LENGTH_SHORT).show();

                }


            } else if (sentimentFilter.equals(seriousConcern)) {

                if (seriousConcernFound.equals(false)) {


                    Toast.makeText(getActivity(), "No Serious Concern Mention found", Toast.LENGTH_SHORT).show();

                }


            } else if (sentimentFilter.equals(disappointed)) {

                if (disappointedFound.equals(false)) {


                    Toast.makeText(getActivity(), "No Disappointed Mention found", Toast.LENGTH_SHORT).show();

                }


            }




//        EngageAdapter engageAdapter = new EngageAdapter(listData);


//        for (int j = 0; j < listData.size(); j++) {
//            m_Ids.add("id1");
//            mNames.add("id1");
//            mSentiments.add("id1");
//            mRetweeteds.add(true);
//            mCreatedAt.add("id1");
////            mProfileImageUrls.add("id1");
//            mRetweetCounts.add("id1");
//            mFavoriteCounts.add("id1");
//            mFavoriteds.add(true);
//        }

            pbEngage.setVisibility(View.GONE);
            rvEngage.setVisibility(View.VISIBLE);

//        HomeAdapter adapter = new HomeAdapter(getContext(), m_Ids, mNames,
//                listData, mSentiments, mRetweeteds, mCreatedAt, mProfileImageUrls,
//                mRetweetCounts, mFavoriteCounts, mFavoriteds);
//        rvEngage.setAdapter(adapter);
//        rvEngage.setLayoutManager(new LinearLayoutManager(getContext()));


//        MentionsAdapter adapter = new MentionsAdapter(getContext(), m_Ids, mNames,
//                listData, mSentiments, mRetweeteds, mCreatedAt, mProfileImageUrls,
//                mRetweetCounts, mFavoriteCounts, mFavoriteds);


            EngageAdapter adapter = new EngageAdapter(getContext(), m_Ids, mNames,
                    listData, mSentimentAnalysisLogReg, mRetweeteds, mCreatedAt, imageData,
                    mRetweetCounts, mFavoriteCounts, mFavoriteds);


            rvEngage.setAdapter(adapter);
            rvEngage.setLayoutManager(new LinearLayoutManager(getContext()));


        }



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


            populateListView();


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

            populateListView();



        } else if (id == R.id.action_suggestive) {

            Log.d(TAG, "onOptionsItemSelected: Suggestive Filter clicked");

//            CheckBox box = v.findViewById(R.id.addressCheckBox); //get your checkbox
//            box.setChecked(!box.isChecked()); //toggle checkbox-state

            item.setChecked(!item.isChecked()); //toggle checkbox-state

            sentimentFilter = "Suggestion";

//            recyclerView.setVisibility(View.INVISIBLE);

            populateListView();



        } else if (id == R.id.action_serious_concern) {

            Log.d(TAG, "onOptionsItemSelected: Serious Concern Filter clicked");

//            CheckBox box = v.findViewById(R.id.addressCheckBox); //get your checkbox
//            box.setChecked(!box.isChecked()); //toggle checkbox-state

            item.setChecked(!item.isChecked()); //toggle checkbox-state

            sentimentFilter = "Serious Concern";

//            recyclerView.setVisibility(View.INVISIBLE);

            populateListView();



        } else if (id == R.id.action_disappointed) {

            Log.d(TAG, "onOptionsItemSelected: disappointed Filter clicked");

//            CheckBox box = v.findViewById(R.id.addressCheckBox); //get your checkbox
//            box.setChecked(!box.isChecked()); //toggle checkbox-state

            item.setChecked(!item.isChecked()); //toggle checkbox-state

            sentimentFilter = "Disappointed";

//            recyclerView.setVisibility(View.INVISIBLE);

            populateListView();



        } else {

            Log.d(TAG, "onOptionsItemSelected: DisplayAll Filter clicked");

//            CheckBox box = v.findViewById(R.id.addressCheckBox); //get your checkbox
//            box.setChecked(!box.isChecked()); //toggle checkbox-state

            item.setChecked(!item.isChecked()); //toggle checkbox-state

            sentimentFilter = "DisplayAll";

//            recyclerView.setVisibility(View.INVISIBLE);

            populateListView();



        }







        return super.onOptionsItemSelected(item);

    }





}
