package com.example.barterapp;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<Item> itemsList;

    public RecyclerAdapter(List<Item> itemsList) {
        this.itemsList = itemsList;
    }

    public void setFilteredList(List<Item> filteredList) {
        this.itemsList = filteredList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView itemName;
        private TextView seller;
        private TextView value;
        private ImageView image;

        public MyViewHolder(final View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.textView2);
            seller = itemView.findViewById(R.id.textView3);
            value = itemView.findViewById(R.id.textView);
            image = itemView.findViewById(R.id.imageView3);
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        String itemName = itemsList.get(position).getName();
        String seller = itemsList.get(position).getOwner();
        String value = "$" + itemsList.get(position).getValue();
        String image = itemsList.get(position).getImage();

        holder.itemName.setText(itemName);
        holder.seller.setText(seller);
        holder.value.setText(value);
        if (image != null) {
            Picasso.get().load(image).into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public void clear() {
        int size = itemsList.size();
        itemsList.clear();
        notifyItemRangeRemoved(0, size);
    }
}
