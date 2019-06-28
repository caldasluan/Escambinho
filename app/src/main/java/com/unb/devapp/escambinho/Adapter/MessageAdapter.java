package com.unb.devapp.escambinho.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.unb.devapp.escambinho.Helper.UserHelper;
import com.unb.devapp.escambinho.Model.MessageModel;
import com.unb.devapp.escambinho.R;
import com.unb.devapp.escambinho.ViewHolder.EscambinhoViewHolder;
import com.unb.devapp.escambinho.ViewHolder.MessageViewHolder;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {
    private ArrayList<MessageModel> mList = new ArrayList<>();
    public static int MY_MESSAGE = 0;
    public static int OTHER_MESSAGE = 1;

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == MY_MESSAGE)
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_holder_message_my, parent, false);
        else
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_holder_message_other, parent, false);
        MessageViewHolder messageViewHolder = new MessageViewHolder(view, viewType);
        return messageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        holder.message.setText(mList.get(position).getMessage());
        if (!mList.get(position).getUserId().equals(UserHelper.getUserModel().getId()))
            Picasso.get().load(R.drawable.ic_person_black).into(holder.image);
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getUserId().equals(UserHelper.getUserModel().getId()) ? MY_MESSAGE : OTHER_MESSAGE;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(ArrayList list) {
        mList = list;
        notifyDataSetChanged();
    }
}
