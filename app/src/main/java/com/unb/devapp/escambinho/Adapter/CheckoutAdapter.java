package com.unb.devapp.escambinho.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.unb.devapp.escambinho.Model.ItemModel;
import com.unb.devapp.escambinho.R;
import com.unb.devapp.escambinho.Util.ClickInterface;
import com.unb.devapp.escambinho.ViewHolder.CheckoutViewHolder;
import com.unb.devapp.escambinho.ViewHolder.EscambinhoViewHolder;

import java.util.ArrayList;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutViewHolder> {
    private ArrayList<ItemModel> mList;
    private ClickInterface clickInterface;

    @NonNull
    @Override
    public CheckoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_holder_checkout, parent, false);
        CheckoutViewHolder checkoutViewHolder = new CheckoutViewHolder(view);
        checkoutViewHolder.setOnClickListener(clickInterface);
        return checkoutViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutViewHolder holder, int position) {
        Picasso.get().load(mList.get(position).getImageUrl()).into(holder.image);
        holder.title.setText(mList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(ArrayList list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void setOnClick(ClickInterface click) {
        clickInterface = click;
    }
}
