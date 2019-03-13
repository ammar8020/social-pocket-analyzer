package com.ammar.socialpocketa;

import android.content.Context;
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

public class TrendsAdapter extends RecyclerView.Adapter<TrendsAdapter.ViewHolder>{

    private static final String TAG = "TrendsAdapter";

    private List<String> mTrendNames;

    private Context mContext;


    public TrendsAdapter(Context context, List<String> trendNames ) {
        mTrendNames = trendNames;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_trend, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.name.setText(mTrendNames.get(position));

//        if(mSentiments.get(position).equals("Appreciated")) {
//            holder.sentiment.setImageResource(R.drawable.appreciative);
//        } else if (mSentiments.get(position).equals("Suggestion")) {
//            holder.sentiment.setImageResource(R.drawable.suggestive);
//        }

//        holder.sentiment.setText(mSentiments.get(position));



//        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d(TAG, "onClick: clicked on: " + mTrendNames.get(position));
//
//                Toast.makeText(mContext, mTrendNames.get(position), Toast.LENGTH_SHORT).show();
//
////                Intent intent = new Intent(mContext, TweetDetailsActivity.class);
////                intent.putExtra("image", mImages.get(position));
////                intent.putExtra("name", mNames.get(position));
////                intent.putExtra("_id", m_Ids.get(position));
////                mContext.startActivity(intent);
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return mTrendNames.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;

        LinearLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_trend);

            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}