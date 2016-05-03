package com.damam.wtestapp.io;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.damam.wtestapp.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Damam on 03-May-16.
 */
public class ItemHolder extends RecyclerView.ViewHolder {

    @InjectView(R.id.item_title)
    public TextView titleView;

    @InjectView(R.id.item_desc)
    public TextView descView;

    @InjectView(R.id.item_image)
    public ImageView imageView;

    public ItemHolder(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
    }
}
