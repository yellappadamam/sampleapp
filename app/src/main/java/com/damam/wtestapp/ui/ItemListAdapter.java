package com.damam.wtestapp.ui;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.damam.wtestapp.R;
import com.damam.wtestapp.io.ItemHolder;
import com.damam.wtestapp.io.ListItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Damam on 03-May-16.
 */
public class ItemListAdapter extends RecyclerView.Adapter<ItemHolder> {

    private final ArrayList<ListItem> items;

    public ItemListAdapter(ArrayList<ListItem> items) {
        this.items = items;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        ListItem item = items.get(position);
        holder.titleView.setText(item.title);
        if (!TextUtils.isEmpty(item.description)) {
            holder.descView.setVisibility(View.VISIBLE);
            holder.descView.setText(item.description);
        } else {
            holder.descView.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(item.imageHref)) {
            holder.imageView.setVisibility(View.VISIBLE);
//            Picasso.with(holder.imageView.getContext()).load(item.imageHref).centerCrop()
//                    .resizeDimen(R.dimen.image_icon_width, R.dimen.image_icon_height)
//                    .placeholder(R.drawable.default_image)
//                    .error(R.drawable.default_image)
//                    .into(holder.imageView);

            Glide.with(holder.imageView.getContext()).load(item.imageHref)
                    .centerCrop().placeholder(R.drawable.default_image)
                    .error(R.drawable.default_image)
                    .into(holder.imageView);
        } else {
            holder.imageView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
