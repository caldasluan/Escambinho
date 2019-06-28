package com.unb.devapp.escambinho.Adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unb.devapp.escambinho.ViewHolder.CheckoutViewHolder;

import java.util.ArrayList;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutViewHolder> {
    private ArrayList mList;
    @NonNull
    @Override
    public CheckoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(ArrayList list) {
        mList = list;
    }
}
