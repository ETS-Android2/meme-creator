package com.moringaschool.memecreator.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.memecreator.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreatedMemesRecyclerViewAdapter extends RecyclerView.Adapter<CreatedMemesRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<String> mMemeNames;
    private ArrayList<String> mCreatedMemesUrl;

    public CreatedMemesRecyclerViewAdapter(Context mContext, ArrayList<String> mMemeNames, ArrayList<String> mCreatedMemesUrl) {
        this.mContext = mContext;
        this.mMemeNames = mMemeNames;
        this.mCreatedMemesUrl = mCreatedMemesUrl;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.created_meme, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mMemeNameTextView.setText(mMemeNames.get(position));
        Picasso.get().load(mCreatedMemesUrl.get(position)).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mCreatedMemesUrl.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mMemeNameTextView;
        ImageView mImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mMemeNameTextView = itemView.findViewById(R.id.memeNameTextView);
            mImageView = itemView.findViewById(R.id.imageView);
        }
    }
}
