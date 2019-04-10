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

import com.ammar.socialpocketa.models.ReplyMentionAnalysisDetail;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NotificationsFragment extends Fragment {


    private static final String TAG = "VisualizationFragment";

    ProgressBar pbVisualization;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_notifications, container, false);

        Log.d(TAG, "onCreateView: started " + TAG);

        pbVisualization = rootView.findViewById(R.id.pb_visualization);


        apiResponse();


        return rootView;

//        return inflater.inflate(R.layout.fragment_notifications, null);
    }






    public void apiResponse() {


        pbVisualization.setVisibility(View.VISIBLE);

        //now making the call object
        //Here using the api method that we created inside the api interface
        Call<ReplyMentionAnalysisDetail> call = RetrofitClient
                .getInstance()
                .getApi()
                .getReplyMentionAnalysis();


        call.enqueue(new Callback<ReplyMentionAnalysisDetail>() {

            @Override
            public void onResponse(Call<ReplyMentionAnalysisDetail> call, Response<ReplyMentionAnalysisDetail> response) {


                try {


                    //In this point we got our response

                    Log.d(TAG, "onResponse: Response.body " + response.body() );

//                    Log.d(TAG, "onResponse: response.body().getMentionCount() \n\n " + response.body().getMentionCount() + "\n\n");
//
//                    Log.d(TAG, "onResponse: response.body().getReplyCount() " + response.body().getReplyCount());



                    Log.d(TAG, "onResponse: response.body().getReplyCount().getReplyLogregCount().getAbusive() " + response.body().getReplyCount().getReplyLogregCount().getAbusive() );



                    Log.d(TAG, "onResponse: response.body().getMentionCount().getLogregCounts() " + response.body().getMentionCount().getLogregCounts());

                    Log.d(TAG, "onResponse: response.body().getMentionCount().getReplyLogregCount() " + response.body().getReplyCount().getReplyLogregCount() );



                    pbVisualization.setVisibility(View.GONE);


                } catch (NullPointerException e) {
                    Log.e(TAG, "onResponse: NullPointerException " + e.getMessage() );

                }


            }




            @Override
            public void onFailure(Call<ReplyMentionAnalysisDetail> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }






}