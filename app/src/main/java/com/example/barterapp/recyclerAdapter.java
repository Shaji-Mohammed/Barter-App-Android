package com.example.barterapp;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {

    private ArrayList<Item> itemsList;

    public recyclerAdapter(ArrayList<Item> itemsList) {
        this.itemsList = itemsList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView itemName;
        private TextView seller;
        private TextView value;
        private ImageView image;

        public MyViewHolder(final View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.textView6);
            seller = itemView.findViewById(R.id.textView7);
            value = itemView.findViewById(R.id.textView8);
            image = itemView.findViewById(R.id.imageView);
        }
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        String itemName = itemsList.get(position).getName();
        String seller = itemsList.get(position).getOwner();
        String value = String.valueOf(itemsList.get(position).getValue());
        Image image = itemsList.get(position).getImage();

//        holder.image.setIma
        holder.itemName.setText(itemName);
        holder.seller.setText(seller);
        holder.value.setText(value);
//        holder.image.setImageURI();
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}
