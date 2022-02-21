package com.moringaschool.memecreator.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.moringaschool.memecreator.R;
import com.moringaschool.memecreator.ui.CreatedMemesFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CreatedMemesListViewAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private int mResource;
    private int mOrientation;

    public CreatedMemesListViewAdapter(@NonNull Context context, int resource, @NonNull ArrayList<String> imageUrls) {
        super(context, resource, imageUrls);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        ImageView imageView = convertView.findViewById(R.id.imageView);

        Picasso.get().load(getItem(position)).into(imageView);

        mOrientation = convertView.getResources().getConfiguration().orientation;
        if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            createMemeFragment();
        }


        return convertView;
    }

    private void createMemeFragment() {
        CreatedMemesFragment createdMemesFragment = new CreatedMemesFragment();
    }
}
