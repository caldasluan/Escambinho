package com.unb.devapp.escambinho.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.unb.devapp.escambinho.Model.ChatModel;
import com.unb.devapp.escambinho.R;
import com.unb.devapp.escambinho.Util.ClickInterface;
import com.unb.devapp.escambinho.ViewHolder.ChatViewHolder;
import com.unb.devapp.escambinho.ViewHolder.EscambinhoViewHolder;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder> {
    ArrayList<ChatModel> mList;
    private ClickInterface clickInterface;

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_holder_chat, parent, false);
        ChatViewHolder chatViewHolder = new ChatViewHolder(view);
        chatViewHolder.setOnClickListener(parent, clickInterface);
        return chatViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        // TODO fazer
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
