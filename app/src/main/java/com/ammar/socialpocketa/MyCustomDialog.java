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
import com.ammar.socialpocketa.models.CreateTweet;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MyCustomDialog extends DialogFragment {

    private static final String TAG = "MyCustomDialog";

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

        dialogHeading.setText("Tweet here");

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

                apiRequest();


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



    public void apiRequest() {


        pbCreateTweet.setVisibility(View.VISIBLE);

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(APIUrl.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        APIService service = retrofit.create(APIService.class);

        Log.d(TAG, "apiRequest: input String: " + input);

//        Call<CreateTweet> call = service.createTweet(input);


        //now making the call object
        //Here using the api method that we created inside the api interface
        Call<CreateTweet> call = RetrofitClient
                .getInstance()
                .getApi()
                .createTweet(input);


        call.enqueue(new Callback<CreateTweet>() {
            @Override
            public void onResponse(Call<CreateTweet> call, Response<CreateTweet> response) {


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


//                CreateTweet createTweet;
//
//                if (response.code() == 400 ) {
//                    Log.d(TAG, "onResponse - Status : " + response.code());
//                    Gson gson = new Gson();
//                    TypeAdapter<CreateTweet> adapter = gson.getAdapter(CreateTweet.class);
//                    try {
//                        if (response.errorBody() != null)
//                            createTweet =
//                                    adapter.fromJson(
//                                            response.errorBody().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }






                Toast.makeText(getActivity(), "Tweet Created Successfully", Toast.LENGTH_SHORT).show();







//                if(input.equals(response.body().getText().getText())) {
//
//                    Toast.makeText(getActivity(), "Tweet Created Successfully", Toast.LENGTH_SHORT).show();
//
//                } else
//
//                    Toast.makeText(getActivity(), "Tweet not Successfully", Toast.LENGTH_SHORT).show();
//            }



                getDialog().dismiss();


            }


            @Override
            public void onFailure(Call<CreateTweet> call, Throwable t) {
//                progressDialog.dismiss();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });



    }


}
