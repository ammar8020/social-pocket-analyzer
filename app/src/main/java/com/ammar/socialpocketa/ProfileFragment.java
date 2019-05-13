package com.ammar.socialpocketa;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ammar.socialpocketa.models.Profile;
import com.bumptech.glide.Glide;

import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";

    ProgressBar pbProfile;
    TextView tvFollowersCount;
    TextView tvFollowingsCount;
    TextView tvDescription;
    TextView tvTotalTweets;
    TextView tvName, tvScreenName;
    CircleImageView civProfilePic;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        pbProfile = rootView.findViewById(R.id.pb_profile);
        tvFollowersCount = rootView.findViewById(R.id.followers_count);
        tvFollowingsCount = rootView.findViewById(R.id.followings_count);
        tvDescription = rootView.findViewById(R.id.tv_description);
        tvTotalTweets = rootView.findViewById(R.id.tv_total_tweets);
        tvName = rootView.findViewById(R.id.tv_name);
        tvScreenName = rootView.findViewById(R.id.tv_screen_name);
        civProfilePic = rootView.findViewById(R.id.civ_profile_pic);

        apiResponse();

        return rootView;

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        //getActivity().setTitle("Profile");
    }



    public void apiResponse() {

        pbProfile.setVisibility(View.VISIBLE);

        //now making the call object
        //Here using the api method that we created inside the api interface
        Call<Profile> call = RetrofitClient
                .getInstance()
                .getApi()
                .getProfile();


        call.enqueue(new Callback<Profile>() {

            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {

                pbProfile.setVisibility(View.GONE);

                try {

                    tvFollowersCount.setText(response.body().getFollowersCount().toString());
                    tvFollowingsCount.setText(response.body().getFriendsCount().toString());
                    tvDescription.setText(response.body().getDescription().toString());
                    tvTotalTweets.setText(response.body().getStatusesCount().toString());
                    tvName.setText(response.body().getName());
                    tvScreenName.setText(response.body().getScreenName());


                    Glide.with(getActivity())
                            .asBitmap()
                            .load(response.body().getProfileImageUrlHttps())
                            .into(civProfilePic);



                } catch (NullPointerException e) {
                    Log.e(TAG, "onResponse: NullPointerException " + e.getMessage() );
                }


            }


            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


}
