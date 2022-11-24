package com.example.barterapp;

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
        }
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
