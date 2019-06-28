package com.unb.devapp.escambinho.Adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unb.devapp.escambinho.ViewHolder.EscambinhoViewHolder;

import java.util.ArrayList;

public class EscambinhoAdapter extends RecyclerView.Adapter<EscambinhoViewHolder> {
    private ArrayList mList;

    @NonNull
    @Override
    public EscambinhoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull EscambinhoViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(ArrayList list) {
        mList = list;
    }
}
