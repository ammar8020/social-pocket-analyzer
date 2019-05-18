package com.ammar.socialpocketa;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ammar.socialpocketa.api.APIService;
import com.ammar.socialpocketa.api.APIUrl;
import com.ammar.socialpocketa.models.ReplyToTweet;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ReplyDialog extends DialogFragment {

    private static final String TAG = "ReplyDialog";

    public interface OnInputListener{
        void sendInput(String input);
    }
    public OnInputListener mOnInputListener;

    //widgets
    private EditText mInput;
    private TextView mActionOk, mActionCancel;

    ProgressBar pbCreateTweet;

    TextView dialogHeading;

    //vars
    String input = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_my_custom, container, false);
        mActionCancel = view.findViewById(R.id.action_cancel);
        mActionOk = view.findViewById(R.id.action_ok);
        mInput = view.findViewById(R.id.input);

        pbCreateTweet = view.findViewById(R.id.pb_create_tweet);

        dialogHeading = view.findViewById(R.id.heading);

        dialogHeading.setText("Reply here");
        mActionOk.setText("Reply");

        pbCreateTweet.setVisibility(View.INVISIBLE);

        mActionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: closing dialog");
                getDialog().dismiss();
            }
        });


        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: capturing input");

                input = mInput.getText().toString();
//                if(!input.equals("")){
//
//                    //Easiest way: just set the value
//                    ((MainActivity)getActivity()).mInputDisplay.setText(input);
//
//                }

                //"Best Practice" but it takes longer
//                mOnInputListener.sendInput(input);

//                apiRequest();
//
//                apiRequest2();


                if (mInput.length() == 0) {

                    Toast.makeText(getActivity(), "Please Enter any Reply first ", Toast.LENGTH_SHORT).show();

                } else if (mInput.length() > 280) {

                    Toast.makeText(getActivity(), "Reply Character length cannot be greater than 280 ", Toast.LENGTH_SHORT).show();


                } else {


                    apiRequest2();


                }



//                getDialog().dismiss();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mOnInputListener = (OnInputListener) getActivity();
        }catch (ClassCastException e){
            Log.e(TAG, "onAttach: ClassCastException: " + e.getMessage() );
        }
    }



//    public void apiRequest() {
//
//
//        pbCreateTweet.setVisibility(View.VISIBLE);
//
////        Retrofit retrofit = new Retrofit.Builder()
////                .baseUrl(APIUrl.BASE_URL)
////                .addConverterFactory(GsonConverterFactory.create())
////                .build();
////
////        APIService service = retrofit.create(APIService.class);
//
//        Log.d(TAG, "apiRequest: input String: " + input);
//
//
////        TweetDetailsActivity tweetDetailsActivity = new TweetDetailsActivity();
//
//        List<Reply> filteredReplyList = TweetDetailsActivity.getFilteredReplyList();
//
//        Log.d(TAG, "apiRequest: filteredReplyList " + filteredReplyList);
//
//        Reply[] filteredReplyArr = new Reply[filteredReplyList.size()];
//        filteredReplyArr = filteredReplyList.toArray(filteredReplyArr);
//
//        Log.d(TAG, "apiRequest: filteredReplyArr " + filteredReplyArr);
//
////        Call<ReplyToTweet> call = service.replyToTweet(filteredReplyList, input);
//
//
//        ArrayList<Reply> filteredReplyArrList = new ArrayList<Reply>(filteredReplyList);
//
////        ArrayList<ReplyToTweet> replyToTweet = new ArrayList<>();
//
////        for(int k = 0; k < filteredReplyList.size(); k++) {
////            replyToTweet.setIdStr(filteredReplyList.get(k).getIdStr());
////
////            replyToTweet.setScreenName(filteredReplyArr[k].getScreenName());
////            Log.d(TAG, "apiRequest: replyToTweet: " + replyToTweet.getScreenName());
////        }
//
//
//        Log.d(TAG, "apiRequest: filteredReplyArrayList: " + filteredReplyArrList);
//
//
//
////        ArrayList<String> screenNameList = new ArrayList<>();
////
////        for (int i = 0; i < filteredReplyList.size(); i++) {
////
////            screenNameList.set(i, filteredReplyArrList.get(i).getScreenName());
////
////            Log.d(TAG, "apiRequest: " + screenNameList.get(i));
////        }
//
//
//
//
//
//        //now making the call object
//        //Here using the api method that we created inside the api interface
//        Call<Reply> call = RetrofitClient
//                .getInstance()
//                .getApi()
//                .replyToTweet(filteredReplyArrList, input);
//
////                .replyToTweet(screenNameList, input);
////                .replyToTweet(filteredReplyArr, input);
//
//
//
//
//
//        call.enqueue(new Callback<Reply>() {
//            @Override
//            public void onResponse(Call<Reply> call, Response<Reply> response) {
//
//
////                if(response.body() != null) {
////
////                    Toast.makeText(getActivity(), "Tweet Created Successfully", Toast.LENGTH_SHORT).show();
////
////                } else {
////                    Toast.makeText(getActivity(), "Failed to create a Tweet", Toast.LENGTH_SHORT).show();
////
////                }
//
//                pbCreateTweet.setVisibility(View.INVISIBLE);
//
//
//                Log.d(TAG, "onResponse: input string: " + input);
//
//
////                Log.d(TAG, "onResponse: response.body().getText(): " + response.body().getText());
//
//
////                Log.d(TAG, "onResponse: response.body().getText().getText(): " + response.body().getText().getText());
//
//
//                Log.d(TAG, "onResponse: Response.body(): " + response.body());
//
//
//
//
//
//                Log.d(TAG, "onResponse: response.getStatus() " + response.message());
//
//
//                Log.d(TAG, "onResponse: response.code() " + response.code());
//
//                Log.d(TAG, "onResponse: response.raw() " + response.raw());
//
//
//
//                Toast.makeText(getActivity(), "Reply to Tweet Successfully", Toast.LENGTH_SHORT).show();
//
//
//
//
//
//
//
////                if(input.equals(response.body().getText().getText())) {
////
////                    Toast.makeText(getActivity(), "Tweet Created Successfully", Toast.LENGTH_SHORT).show();
////
////                } else
////
////                    Toast.makeText(getActivity(), "Tweet not Successfully", Toast.LENGTH_SHORT).show();
////            }
//
//
//
//////                getDialog().dismiss();
//
//
//            }
//
//
//            @Override
//            public void onFailure(Call<Reply> call, Throwable t) {
////                progressDialog.dismiss();
//                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//
//
//
//    }






    public void apiRequest2() {


        pbCreateTweet.setVisibility(View.VISIBLE);

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(APIUrl.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        APIService service = retrofit.create(APIService.class);

        Log.d(TAG, "apiRequest: input String: " + input);


//        TweetDetailsActivity tweetDetailsActivity = new TweetDetailsActivity();

        List<String> idStr = TweetDetailsActivity.getIdStr();

        Log.d(TAG, "apiRequest: idStr " + idStr);



        List<String> screenNames = TweetDetailsActivity.getScreenNames();

        Log.d(TAG, "apiRequest: idStr " + screenNames);



//        Reply[] filteredReplyArr = new Reply[filteredReplyList.size()];
//        filteredReplyArr = filteredReplyList.toArray(filteredReplyArr);

//        Log.d(TAG, "apiRequest: filteredReplyArr " + filteredReplyArr);

//        Call<ReplyToTweet> call = service.replyToTweet(filteredReplyList, input);


        ArrayList<String> idStrArrList = new ArrayList<String>(idStr);

        ArrayList<String> screenNamesArrList = new ArrayList<String>(screenNames);





//        ArrayList<ReplyToTweet> replyToTweet = new ArrayList<>();

//        for(int k = 0; k < filteredReplyList.size(); k++) {
//            replyToTweet.setIdStr(filteredReplyList.get(k).getIdStr());
//
//            replyToTweet.setScreenName(filteredReplyArr[k].getScreenName());
//            Log.d(TAG, "apiRequest: replyToTweet: " + replyToTweet.getScreenName());
//        }


        Log.d(TAG, "apiRequest: idStrArrList: " + idStrArrList);


        Log.d(TAG, "apiRequest: screenNamesArrList: " + screenNamesArrList);


//        ArrayList<String> screenNameList = new ArrayList<>();
//
//        for (int i = 0; i < filteredReplyList.size(); i++) {
//
//            screenNameList.set(i, filteredReplyArrList.get(i).getScreenName());
//
//            Log.d(TAG, "apiRequest: " + screenNameList.get(i));
//        }





        //now making the call object
        //Here using the api method that we created inside the api interface
        Call<ReplyToTweet> call = RetrofitClient
                .getInstance()
                .getApi()
                .replyToTweet(input, idStrArrList, screenNamesArrList);

//                .replyToTweet(screenNameList, input);
//                .replyToTweet(filteredReplyArr, input);


//        Toast.makeText(getActivity(), "Your reply will be sent to the desired people", Toast.LENGTH_SHORT).show();


//        pbCreateTweet.setVisibility(View.INVISIBLE);


//        getDialog().dismiss();



        call.enqueue(new Callback<ReplyToTweet>() {
            @Override
            public void onResponse(Call<ReplyToTweet> call, Response<ReplyToTweet> response) {


//                if(response.body() != null) {
//
//                    Toast.makeText(getActivity(), "Tweet Created Successfully", Toast.LENGTH_SHORT).show();
//
//                } else {
//                    Toast.makeText(getActivity(), "Failed to create a Tweet", Toast.LENGTH_SHORT).show();
//
//                }


                pbCreateTweet.setVisibility(View.INVISIBLE);


                Log.d(TAG, "onResponse: input string: " + input);


//                Log.d(TAG, "onResponse: response.body().getText(): " + response.body().getText());


//                Log.d(TAG, "onResponse: response.body().getText().getText(): " + response.body().getText().getText());


                Log.d(TAG, "onResponse: Response.body(): " + response.body());





                Log.d(TAG, "onResponse: response.getStatus() " + response.message());


                Log.d(TAG, "onResponse: response.code() " + response.code());

                Log.d(TAG, "onResponse: response.raw() " + response.raw());



//                Toast.makeText(getActivity(), "Reply to Tweet Successfully", Toast.LENGTH_SHORT).show();


                Toast.makeText(getActivity(), "Your reply will be sent to the selected users", Toast.LENGTH_SHORT).show();


                getDialog().dismiss();



//                if(input.equals(response.body().getText().getText())) {
//
//                    Toast.makeText(getActivity(), "Tweet Created Successfully", Toast.LENGTH_SHORT).show();
//
//                } else
//
//                    Toast.makeText(getActivity(), "Tweet not Successfully", Toast.LENGTH_SHORT).show();
//            }



////                getDialog().dismiss();


            }


            @Override
            public void onFailure(Call<ReplyToTweet> call, Throwable t) {
//                progressDialog.dismiss();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });







//        getDialog().dismiss();




    }






}
