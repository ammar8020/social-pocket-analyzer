package com.ammar.socialpocketa;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


import com.ammar.socialpocketa.models.RMAnalysisDetail;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MentionsVisualizationFragment extends Fragment {


    private static final String TAG = "MentionsVisualFragment";

    ProgressBar pbVisualization;

    PieChart pieChart;

    private static final String SHARED_PREF_NAME = "settings";

    private static final String KEY_SELECTED_ALGO = "keySelectedAlgo";



//    BarChart barChart;
//    ArrayList<String> dates;
//    Random random;
//    ArrayList<BarEntry> barEntries;
//
//
//
//    public static String sentimentFilter = "";




//    Integer[] yData = new Integer[5];
//    String[] xData = new String[5];

//    private float[] yData = {25.3f, 10.6f, 66.76f, 44.32f, 46.01f, 16.89f, 23.9f};
//    private String[] xData = {"Mitch", "Jessica", "Brad" , "Kelsey", "Sam", "Robert", "Ashley"};
//    PieChart pieChart;






//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setHasOptionsMenu(true);
//
//    }






    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_mentions_visualization, container, false);

        Log.d(TAG, "onCreateView: started " + TAG);

        pbVisualization = rootView.findViewById(R.id.pb_visualization);



        pieChart = rootView.findViewById(R.id.idPieChart);




//        barChart = (BarChart) rootView.findViewById(R.id.bargraph);

//        createRandomBarGraph("2016/05/05", "2016/06/01");





        apiResponse();


        return rootView;

//        return inflater.inflate(R.layout.fragment_mentions_visualization, null);
    }






    public void apiResponse() {


//        barChart.setVisibility(View.GONE);

        pbVisualization.setVisibility(View.VISIBLE);

        pieChart.setVisibility(View.VISIBLE);
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


                    Log.d(TAG, "onResponse: response.body().getMentionsCount " + response.body().getMentionsCount() );

                    Log.d(TAG, "onResponse: " + response.body().getMentionsCount().getLogregCounts().getAppreciated() );




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

                        tempAppreciativeCount = response.body().getMentionsCount().getLogregCounts().getAppreciated();
                        tempAbusiveCount = response.body().getMentionsCount().getLogregCounts().getAbusive();
                        tempSuggestiveCount = response.body().getMentionsCount().getLogregCounts().getSuggestion();
                        tempSeriousConcernCount = response.body().getMentionsCount().getLogregCounts().getSeriousConcern();
                        tempDisappointedCount = response.body().getMentionsCount().getLogregCounts().getDisappointed();


                    } else if (currentSelectedAlgo.equals(rnn)) {

                        tempAppreciativeCount = response.body().getMentionsCount().getRnnCounts().getAppreciated();
                        tempAbusiveCount = response.body().getMentionsCount().getRnnCounts().getAbusive();
                        tempSuggestiveCount = response.body().getMentionsCount().getRnnCounts().getSuggestion();
                        tempSeriousConcernCount = response.body().getMentionsCount().getRnnCounts().getSeriousConcern();
                        tempDisappointedCount = response.body().getMentionsCount().getRnnCounts().getDisappointed();

                    } else {

                        tempAppreciativeCount = response.body().getMentionsCount().getNaivebayesCounts().getAppreciated();
                        tempAbusiveCount = response.body().getMentionsCount().getNaivebayesCounts().getAbusive();
                        tempSuggestiveCount = response.body().getMentionsCount().getNaivebayesCounts().getSuggestion();
                        tempSeriousConcernCount = response.body().getMentionsCount().getNaivebayesCounts().getSeriousConcern();
                        tempDisappointedCount = response.body().getMentionsCount().getNaivebayesCounts().getDisappointed();

                    }


                    if (tempAppreciativeCount == 0 && tempAbusiveCount == 0 &&
                            tempDisappointedCount == 0 && tempSuggestiveCount == 0 &&
                            tempSeriousConcernCount == 0) {

                        Toast.makeText(getContext(), "There are no mentions available to visualize", Toast.LENGTH_SHORT).show();

                    }



                    final Integer[] yData = {tempAppreciativeCount, tempAbusiveCount, tempSuggestiveCount,
                             tempSeriousConcernCount, tempDisappointedCount};
                    final String[] xData = {appreciative, abusive, suggestive, seriousConcern, disappointed};


//        pieChart.setDescription("Sales by employee (In Thousands $)");
                    final Description desc = new Description();
                    desc.setText("Mentions Count Visualization");
                    pieChart.setDescription(desc);

                    pieChart.setRotationEnabled(true);
                    //pieChart.setUsePercentValues(true);
                    //pieChart.setHoleColor(Color.BLUE);
                    //pieChart.setCenterTextColor(Color.BLACK);
                    pieChart.setHoleRadius(25f);
                    pieChart.setTransparentCircleAlpha(0);
                    pieChart.setCenterText("Mentions Chart");
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
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "");
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







//    public void createRandomBarGraph(String Date1, String Date2){
//
//
//        pieChart.setVisibility(View.GONE);
//        barChart.setVisibility(View.VISIBLE);
//
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
//
//        try {
//            Date date1 = simpleDateFormat.parse(Date1);
//            Date date2 = simpleDateFormat.parse(Date2);
//
//            Calendar mDate1 = Calendar.getInstance();
//            Calendar mDate2 = Calendar.getInstance();
//            mDate1.clear();
//            mDate2.clear();
//
//            mDate1.setTime(date1);
//            mDate2.setTime(date2);
//
//            dates = new ArrayList<>();
//            dates = getList(mDate1,mDate2);
//
//            barEntries = new ArrayList<>();
//            float max = 0f;
//            float value = 0f;
//            random = new Random();
//            for(int j = 0; j< dates.size();j++){
//                max = 100f;
//                value = random.nextFloat()*max;
//                barEntries.add(new BarEntry(value,j));
//            }
//
//        }catch(ParseException e){
//            e.printStackTrace();
//        }
//
//        BarDataSet barDataSet = new BarDataSet(barEntries,"Dates");
//        BarData barData = new BarData((IBarDataSet) dates,barDataSet);
//        barChart.setData(barData);
////        barChart.setDescription("My First Bar Graph!");
//
//    }
//
//    public ArrayList<String> getList(Calendar startDate, Calendar endDate){
//        ArrayList<String> list = new ArrayList<String>();
//        while(startDate.compareTo(endDate)<=0){
//            list.add(getDate(startDate));
//            startDate.add(Calendar.DAY_OF_MONTH,1);
//        }
//        return list;
//    }
//
//    public String getDate(Calendar cld){
//        String curDate = cld.get(Calendar.YEAR) + "/" + (cld.get(Calendar.MONTH) + 1) + "/"
//                +cld.get(Calendar.DAY_OF_MONTH);
//        try{
//            Date date = new SimpleDateFormat("yyyy/MM/dd").parse(curDate);
//            curDate =  new SimpleDateFormat("yyy/MM/dd").format(date);
//        }catch(ParseException e){
//            e.printStackTrace();
//        }
//        return curDate;
//    }






//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//
//        getActivity().getMenuInflater().inflate(R.menu.visualization, menu);
//
//
//        switch (sentimentFilter) {
//
//            case "Pie Chart":
//                menu.getItem(0).setChecked(true);
//                break;
//
//            case "Bar Chart":
//                menu.getItem(1).setChecked(true);
//                break;
//
//        }
//
//            super.onCreateOptionsMenu(menu, inflater);
//
//    }
//
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//
//        if (id == R.id.action_pie_chart) {
//
//            Log.d(TAG, "onOptionsItemSelected: Pie Chart clicked");
//
//            item.setChecked(!item.isChecked()); //toggle checkbox-state
//
//            sentimentFilter = "Pie Chart";
//
//            apiResponse();
//
//        } else {
//
//
//            Log.d(TAG, "onOptionsItemSelected: Bar Chart clicked");
//
//            item.setChecked(!item.isChecked()); //toggle checkbox-state
//
//
//
//            sentimentFilter = "Bar Chart";
//
//
////            barChartApiResponse();
//
//            createRandomBarGraph("2016/05/05", "2016/06/01");
//
//
//        }
//
//        return super.onOptionsItemSelected(item);
//
//
//
//    }







}