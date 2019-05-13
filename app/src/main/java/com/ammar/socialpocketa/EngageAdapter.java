package com.ammar.socialpocketa;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EngageAdapter extends RecyclerView.Adapter<EngageAdapter.ViewHolder>{

    private static final String TAG = "EngageAdapter";

    private List<String> m_Ids;

    //private ArrayList<String> mNames = new ArrayList<>();
    private List<String> mNames;
//
//    private ArrayList<String> mImages;
//    private ArrayList<String> mTimes;

    //private ArrayList<String> mTweets = new ArrayList<>();
    private List<String> mTweets;
    private List<String> mSentimentAnalysisLogReg;

    private List<Boolean> mRetweeted;
    private List<String> mCreatedAt;
    private List<Bitmap> mProfileImageBitmap;
    private List<String> mRetweetCount;
    private List<String> mFavoriteCount;
    private List<Boolean> mFavorited;

    private Context mContext;

//    DbImageUtil dbImageUtil;

//    DatabaseHelper mDatabaseHelper;

//    byte[] byteImage;

    /*private List<Home> postList;



    /*public HomeAdapter(Context mContext, ArrayList<String> mTweets) {
        this.mTweets = mTweets;
        this.mContext = mContext;

    }*/

    public EngageAdapter() {

    }


    public EngageAdapter(List<String> tweets) {

        mTweets = tweets;

    }



    public EngageAdapter(Context context, List<String> ids, List<String> names, List<String> tweets,
                           List<String> sentimentLogReg, List<Boolean> retweeted, List<String> createdAt,
                           List<Bitmap> profileImageBitmap, List<String> retweetCount,
                           List<String> favoriteCount, List<Boolean> favorited ) {
        mNames = names;
        mContext = context;
        mTweets = tweets;
        m_Ids = ids;
        mSentimentAnalysisLogReg = sentimentLogReg;
        mRetweeted = retweeted ;
        mCreatedAt = createdAt;
        mProfileImageBitmap = profileImageBitmap;
        mRetweetCount = retweetCount;
        mFavoriteCount = favoriteCount;
        mFavorited = favorited;

//        dbImageUtil = new DbImageUtil();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_tweet_alter, parent, false);
        ViewHolder holder = new ViewHolder(view);

//        mDatabaseHelper = new DatabaseHelper(mContext);

        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

//        Glide.with(mContext)
//                .asBitmap()
//                .load(mProfileImageBitmap.get(position))
//                .into(holder.image);



//        TODO: 11
//        Glide.with(mContext)
//                .asBitmap()
//                .load(mProfileImageBitmap.get(position))
//                .into(new SimpleTarget<Bitmap>() {
//                    @Override
//                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
//                        holder.image.setImageBitmap(resource);
//
////                        byteImage = DbImageUtil.getBytes(resource);
//
//                    }
//                });

        holder.image.setImageBitmap(mProfileImageBitmap.get(position));


        holder.name.setText(mNames.get(position));
        holder.tvTime.setText(mCreatedAt.get(position));
        holder.tweet.setText(mTweets.get(position));

        holder.tvfavoriteCount.setText(mFavoriteCount.get(position));
//        holder.tvRetweetCount.setText(String.format("%s", mRetweetCount.get(position)));
        holder.tvRetweetCount.setText(mRetweetCount.get(position));

        Log.d(TAG, "onBindViewHolder: Sentiment is: " + mSentimentAnalysisLogReg.get(position));

        switch (mSentimentAnalysisLogReg.get(position)) {

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
                break;

        }

//        if(mSentiments.get(position).equals("Appreciated")) {
//            holder.sentiment.setImageResource(R.drawable.appreciative);
//        } else if (mSentiments.get(position).equals("Suggestion")) {
//            holder.sentiment.setImageResource(R.drawable.suggestive);
//        }

//        holder.sentiment.setText(mSentiments.get(position));



//        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d(TAG, "onClick: clicked on: " + mNames.get(position));
//
//                Toast.makeText(mContext, mNames.get(position), Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent(mContext, TweetDetailsActivity.class);
//                intent.putExtra("image", mImages.get(position));
//                intent.putExtra("name", mNames.get(position));
//                intent.putExtra("_id", m_Ids.get(position));
//                mContext.startActivity(intent);
//            }
//        });


//        holder.tweet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d(TAG, "onClick: clicked on: " + mNames.get(position));
//
//                Toast.makeText(mContext, mNames.get(position), Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent(mContext, EngageFragment.class);
////                intent.putExtra("image", mProfileImageBitmap.get(position));
////                intent.putExtra("name", mNames.get(position));
////                intent.putExtra("_id", m_Ids.get(position));
//                mContext.startActivity(intent);
//            }
//        });




//        holder.rlEngage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d(TAG, "onClick: Engage Clicked ");
//
//                Log.d(TAG, "onClick: ID is " + m_Ids.get(position));
//                Log.d(TAG, "onClick: Name is " + mNames.get(position));
//                Log.d(TAG, "onClick: Tweet  is " + mTweets.get(position));
//
//                AddData(mTweets.get(position));
//
//
//            }
//        });



    }




//    public void AddData(String newEntry) {
//        boolean insertData = mDatabaseHelper.addData(newEntry, byteImage);
//
//        if (insertData) {
//            Toast.makeText(mContext, "Data Successfully Inserted!", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_SHORT).show();
//        }
//    }



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

        RelativeLayout rlEngage;


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

            rlEngage = itemView.findViewById(R.id.rl_engage);

        }
    }
}