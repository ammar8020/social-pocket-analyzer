package com.ammar.socialpocketa;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{

    private static final String TAG = "HomeAdapter";

    private List<String> m_Ids;

    //private ArrayList<String> mNames = new ArrayList<>();
    private List<String> mNames;

//    private ArrayList<String> mImages = new ArrayList<>();
//    private ArrayList<String> mTimes = new ArrayList<>();

    //private ArrayList<String> mTweets = new ArrayList<>();
    private List<String> mTweets;
    private List<String> mSentiments;

    private List<Boolean> mRetweeted;
    private List<String> mCreatedAt;
    private List<String> mProfileImageUrl;
    private List<String> mRetweetCount;
    private List<String> mFavoriteCount;
    private List<Boolean> mFavorited;

    private Context mContext;

    /*private List<Home> postList;



    /*public HomeAdapter(Context mContext, ArrayList<String> mTweets) {
        this.mTweets = mTweets;
        this.mContext = mContext;
    }*/

    public HomeAdapter(Context context, List<String> ids, List<String> names, List<String> tweets,
                       List<String> sentiments, List<Boolean> retweeted, List<String> createdAt,
                       List<String> profileImageUrl, List<String> retweetCount,
                       List<String> favoriteCount, List<Boolean> favorited
                       ) {
        mNames = names;
        mContext = context;
        mTweets = tweets;
        m_Ids = ids;
        mSentiments = sentiments;
        mRetweeted = retweeted ;
        mCreatedAt = createdAt;
        mProfileImageUrl = profileImageUrl;
        mRetweetCount = retweetCount;
        mFavoriteCount = favoriteCount;
        mFavorited = favorited;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_tweet, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(mProfileImageUrl.get(position))
                .into(holder.image);

        holder.name.setText(mNames.get(position));
        holder.tvTime.setText(mCreatedAt.get(position));
        holder.tweet.setText(mTweets.get(position));

        holder.tvfavoriteCount.setText(mFavoriteCount.get(position));
//        holder.tvRetweetCount.setText(String.format("%s", mRetweetCount.get(position)));
        holder.tvRetweetCount.setText(mRetweetCount.get(position));

        Log.d(TAG, "onBindViewHolder: Sentiment is: " + mSentiments.get(position));

        switch (mSentiments.get(position)) {

            case "Appreciated":
                holder.sentiment.setImageResource(R.drawable.appreciative);
                break;
            case "Suggestion":
                holder.sentiment.setImageResource(R.drawable.suggestive);
                break;
            case "Abusive":
                holder.sentiment.setImageResource(R.drawable.abusive);
                break;
            case "Disappointed":
                holder.sentiment.setImageResource(R.drawable.disappointed);
                break;
            case "Serious Concern":
                holder.sentiment.setImageResource(R.drawable.serious_concern);
                break;

            default:
                holder.sentiment.setImageResource(R.drawable.neutral);

                holder.sentiment.setVisibility(View.INVISIBLE);

                break;

        }



        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + mNames.get(position));

                Toast.makeText(mContext, mNames.get(position), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, TweetDetailsActivity.class);
                intent.putExtra("image", mProfileImageUrl.get(position));
                intent.putExtra("name", mNames.get(position));
                intent.putExtra("_id", m_Ids.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView name;
        TextView tvTime;
        TextView tweet;
        ImageView sentiment;
        LinearLayout parentLayout;

        TextView tvfavoriteCount;
        TextView tvRetweetCount;


        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imgView_proPic);
            name = itemView.findViewById(R.id.tv_name);
            tvTime = itemView.findViewById(R.id.tv_time);
            tweet = itemView.findViewById(R.id.tv_tweet);

            sentiment = itemView.findViewById(R.id.sentiment);

            tvfavoriteCount = itemView.findViewById(R.id.tv_like);
            tvRetweetCount = itemView.findViewById(R.id.tv_comment);

            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}