package com.ammar.socialpocketa;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{

    private static final String TAG = "PostAdapter";

    //private ArrayList<String> mNames = new ArrayList<>();
    private List<String> mNames = new ArrayList<>();

    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<String> mTimes = new ArrayList<>();

    //private ArrayList<String> mComments = new ArrayList<>();
    private List<String> mComments = new ArrayList<>();

    private Context mContext;

    public CommentAdapter(Context context, List<String> names, ArrayList<String> images, ArrayList<String> times, List<String> comments ) {

        mNames = names;
        mImages = images;
        mContext = context;
        mTimes = times;
        mComments = comments;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_comment, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.image);

        holder.name.setText(mNames.get(position));
        holder.tvTime.setText(mTimes.get(position));
        holder.comment.setText(mComments.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + mNames.get(position));

                Toast.makeText(mContext, mNames.get(position), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, PostDetailsActivity.class);
                intent.putExtra("image", mImages.get(position));
                intent.putExtra("name", mNames.get(position));
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
        TextView comment;
        LinearLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.comment_profile_image);
            name = itemView.findViewById(R.id.comment_username);
            tvTime = itemView.findViewById(R.id.comment_time_posted);
            comment = itemView.findViewById(R.id.tv_comment);

            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}