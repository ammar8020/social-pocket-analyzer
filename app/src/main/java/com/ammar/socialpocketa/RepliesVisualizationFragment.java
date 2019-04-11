package com.ammar.socialpocketa;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.graphics.Color;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;


import com.ammar.socialpocketa.models.RMAnalysisDetail;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RepliesVisualizationFragment extends Fragment {


    private static final String TAG = "RepliesVisualFragment";

    ProgressBar pbVisualization;

    PieChart pieChart;

    private static final String SHARED_PREF_NAME = "settings";

    private static final String KEY_SELECTED_ALGO = "keySelectedAlgo";

//    Integer[] yData = new Integer[5];
//    String[] xData = new String[5];

//    private float[] yData = {25.3f, 10.6f, 66.76f, 44.32f, 46.01f, 16.89f, 23.9f};
//    private String[] xData = {"Mitch", "Jessica", "Brad" , "Kelsey", "Sam", "Robert", "Ashley"};
//    PieChart pieChart;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_replies_visualization, container, false);

        Log.d(TAG, "onCreateView: started " + TAG);

        pbVisualization = rootView.findViewById(R.id.pb_visualization);


        pieChart = rootView.findViewById(R.id.idPieChart);


        apiResponse();


        return rootView;

//        return inflater.inflate(R.layout.fragment_mentions_visualization, null);
    }






    public void apiResponse() {


        pbVisualization.setVisibility(View.VISIBLE);

        //now making the call object
        //Here using the api method that we created inside the api interface
//        Call<ReplyMentionAnalysisDetail> call = RetrofitClient
        Call<RMAnalysisDetail> call = RetrofitClient
                .getInstance()
                .getApi()
                .getReplyMentionAnalysis();


//        call.enqueue(new Callback<ReplyMentionAnalysisDetail>() {
        call.enqueue(new Callback<RMAnalysisDetail>() {
            @Override
//            public void onResponse(Call<ReplyMentionAnalysisDetail> call, Response<ReplyMentionAnalysisDetail> response) {
            public void onResponse(Call<RMAnalysisDetail> call, Response<RMAnalysisDetail> response) {



                try {


                    //In this point we got our response

                    Log.d(TAG, "onResponse: Response.body " + response.body() );

//                    Log.d(TAG, "onResponse: response.body().getMentionCount() \n\n " + response.body().getMentionCount() + "\n\n");
//
//                    Log.d(TAG, "onResponse: response.body().getReplyCount() " + response.body().getReplyCount());



//                    Log.d(TAG, "onResponse: response.body().getReplyCount().getReplyLogregCount().getAbusive() " + response.body().getReplyCount().getReplyLogregCount().getAbusive() );



//                    Log.d(TAG, "onResponse: response.body().getMentionCount().getLogregCounts() " + response.body().getMentionCount().getLogregCounts());

//                    Log.d(TAG, "onResponse: response.body().getMentionCount().getReplyLogregCount() " + response.body().getReplyCount().getReplyLogregCount() );


                    Log.d(TAG, "onResponse: response.body().getRepliesCount " + response.body().getRepliesCount() );

                    Log.d(TAG, "onResponse: " + response.body().getRepliesCount().getLogregCounts().getAppreciated() );




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

//                    String tempSelectedAlgo = "";

//                    LogregCounts_ logregCounts_ = new LogregCounts_();

                    Integer tempAppreciativeCount = 0;
                    Integer tempAbusiveCount = 0;
                    Integer tempSuggestiveCount = 0;
                    Integer tempSeriousConcernCount = 0;
                    Integer tempDisappointedCount = 0;


//                    if (currentSelectedAlgo.equals(logReg)) {
//
//                        tempSelectedAlgo = logReg;
//
//                    } else if (currentSelectedAlgo.equals(rnn)) {
//
//                        tempSelectedAlgo = rnn;
//
//                    } else {
//
//                        tempSelectedAlgo = naiveBayes;
//
//                    }



                    if (currentSelectedAlgo.equals(logReg)) {

//                        tempSelectedAlgo = postList.get(i).getSentimentAnalysisLogreg();

                        tempAppreciativeCount = response.body().getRepliesCount().getLogregCounts().getAppreciated();
                        tempAbusiveCount = response.body().getRepliesCount().getLogregCounts().getAbusive();
                        tempSuggestiveCount = response.body().getRepliesCount().getLogregCounts().getSuggestion();
                        tempSeriousConcernCount = response.body().getRepliesCount().getLogregCounts().getSeriousConcern();
                        tempDisappointedCount = response.body().getRepliesCount().getLogregCounts().getDisappointed();


                    } else if (currentSelectedAlgo.equals(rnn)) {

                        tempAppreciativeCount = response.body().getRepliesCount().getRnnCounts().getAppreciated();
                        tempAbusiveCount = response.body().getRepliesCount().getRnnCounts().getAbusive();
                        tempSuggestiveCount = response.body().getRepliesCount().getRnnCounts().getSuggestion();
                        tempSeriousConcernCount = response.body().getRepliesCount().getRnnCounts().getSeriousConcern();
                        tempDisappointedCount = response.body().getRepliesCount().getRnnCounts().getDisappointed();

                    } else {

                        tempAppreciativeCount = response.body().getRepliesCount().getNaivebayesCounts().getAppreciated();
                        tempAbusiveCount = response.body().getRepliesCount().getNaivebayesCounts().getAbusive();
                        tempSuggestiveCount = response.body().getRepliesCount().getNaivebayesCounts().getSuggestion();
                        tempSeriousConcernCount = response.body().getRepliesCount().getNaivebayesCounts().getSeriousConcern();
                        tempDisappointedCount = response.body().getRepliesCount().getNaivebayesCounts().getDisappointed();

                    }




                    final Integer[] yData = {tempAppreciativeCount, tempAbusiveCount, tempSuggestiveCount,
                            tempSeriousConcernCount, tempDisappointedCount};
                    final String[] xData = {appreciative, abusive, suggestive, seriousConcern, disappointed};


//        pieChart.setDescription("Sales by employee (In Thousands $)");
                    final Description desc = new Description();
                    desc.setText("Replies Count Visualization");
                    pieChart.setDescription(desc);

                    pieChart.setRotationEnabled(true);
                    //pieChart.setUsePercentValues(true);
                    //pieChart.setHoleColor(Color.BLUE);
                    //pieChart.setCenterTextColor(Color.BLACK);
                    pieChart.setHoleRadius(25f);
                    pieChart.setTransparentCircleAlpha(0);
                    pieChart.setCenterText("Replies Chart");
                    pieChart.setCenterTextSize(10);
                    //pieChart.setDrawEntryLabels(true);
                    //pieChart.setEntryLabelTextSize(20);
                    //More options just check out the documentation!

                    addDataSet(xData, yData);

                    pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                        @Override
                        public void onValueSelected(Entry e, Highlight h) {
                            Log.d(TAG, "onValueSelected: Value select from chart.");
                            Log.d(TAG, "onValueSelected: " + e.toString());
                            Log.d(TAG, "onValueSelected: " + h.toString());

                /*int pos1 = e.toString().indexOf("(sum): ");
                String count = e.toString().substring(pos1 + 7);*/

                            int pos1 = e.toString().indexOf("y: ");
                            String count = e.toString().substring(pos1 + 3);

                            for(int i = 0; i < yData.length; i++){
                                if(yData[i] == Float.parseFloat(count)){
                                    pos1 = i;
                                    break;
                                }
                            }
//                String sentiment = xData[pos1 + 1];
                            String sentiment = xData[pos1];
                            Toast.makeText(getActivity(), "Sentiment: " + sentiment + "\n" + "Count: " + count, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onNothingSelected() {

                        }
                    });





                    pbVisualization.setVisibility(View.GONE);


                } catch (NullPointerException e) {
                    Log.e(TAG, "onResponse: NullPointerException " + e.getMessage() );

                }


            }




            @Override
//            public void onFailure(Call<ReplyMentionAnalysisDetail> call, Throwable t) {
            public void onFailure(Call<RMAnalysisDetail> call, Throwable t) {

                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }



    private void addDataSet(String[] xData, Integer[] yData) {
        Log.d(TAG, "addDataSet started");
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i = 0; i < yData.length; i++){
            yEntrys.add(new PieEntry(yData[i] , i));
        }

        for(int i = 1; i < xData.length; i++){
            xEntrys.add(xData[i]);
        }

        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Replies Analysis");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();

        colors.add(Color.GREEN);
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.MAGENTA);
        colors.add(Color.YELLOW);



//        colors.add(Color.CYAN);
//        colors.add(Color.YELLOW);
//        colors.add(Color.MAGENTA);

        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }




}