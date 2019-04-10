package com.ammar.socialpocketa;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import static android.content.Context.MODE_PRIVATE;

public class SettingFragment extends Fragment {

    private static final String TAG = "SettingFragment";

    private static String selectedAlgo = "LogReg";

    private static final String SHARED_PREF_NAME = "settings";

    private static final String KEY_SELECTED_ALGO = "keySelectedAlgo";

    SharedPreferences sharedPreferences;

//    private RadioGroup radioGroup;
//    private RadioButton radioButton;

    RadioButton radioButtonLogReg, radioButtonNaiveBayes, rbRnn;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_setting, container, false);

        Log.d(TAG, "onCreate: started.");

        radioButtonLogReg = rootView.findViewById(R.id.radio_log_reg);
        rbRnn = rootView.findViewById(R.id.radio_rnn);
        radioButtonNaiveBayes = rootView.findViewById(R.id.radio_naive_bayes);



        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(KEY_SELECTED_ALGO, selectedAlgo);
//        editor.apply();


        String currentSelectedAlgo = sharedPreferences.getString(KEY_SELECTED_ALGO, "LogReg");


        if(currentSelectedAlgo.equals("LogReg")) {
            radioButtonLogReg.setChecked(true);
        } else if (currentSelectedAlgo.equals("NaiveBayes")){
            radioButtonNaiveBayes.setChecked(true);
        } else {
            rbRnn.setChecked(true);
        }



//        SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);
//        int savedRadioIndex = sharedPreferences.getInt(KEY_SAVED_RADIO_BUTTON_INDEX, 0);
//        RadioButton savedCheckedRadioButton = (RadioButton)radioGroup.getChildAt(savedRadioIndex);
//        savedCheckedRadioButton.setChecked(true);


//        if (radioButtonLogReg.isChecked()) {

//            int selectedId = radioGroup.getCheckedRadioButtonId();

//            radioButtonLogReg.setChecked(true);
            // find the radiobutton by returned id
//            radioButton = (RadioButton) rootView.findViewById(selectedId);

//        if (radioButton.getText().equals("LogReg")) {
//            radioButton.setChecked(true);
//        }

//        } else if (radioButtonNaiveBayes.isChecked()){
//
//            radioButtonNaiveBayes.setChecked(true);
//
//        }


        radioButtonLogReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAlgo = "LogReg";

                sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_SELECTED_ALGO, selectedAlgo);
                editor.apply();

            }
        });

        radioButtonNaiveBayes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAlgo = "NaiveBayes";


                sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_SELECTED_ALGO, selectedAlgo);
                editor.apply();


            }
        });


        rbRnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAlgo = "RNN";

                sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_SELECTED_ALGO, selectedAlgo);
                editor.apply();

            }
        });


        return rootView;
    }




//    public void onRadioButtonClicked(View view) {
//        // Is the button now checked?
//        boolean checked = ((RadioButton) view).isChecked();
//
//        // Check which radio button was clicked
//        switch(view.getId()) {
//            case R.id.radio_log_reg:
//                if (checked)
//                    selectedAlgo = "LogReg";
//                    break;
//            case R.id.radio_rnn:
//                if (checked)
//                    selectedAlgo = "RNN";
//                    break;
//
//            case R.id.radio_naive_bayes:
//                if (checked)
//                    selectedAlgo = "NaiveBayes";
//                    break;
//        }
//    }



    public static String getSelectedAlgo() {
        return selectedAlgo;
    }

    public static void setSelectedAlgo(String selected) {
        selectedAlgo = selected;
    }



}
