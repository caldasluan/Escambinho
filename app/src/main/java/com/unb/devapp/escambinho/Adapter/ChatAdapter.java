package com.unb.devapp.escambinho.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.unb.devapp.escambinho.Model.ChatModel;
import com.unb.devapp.escambinho.Model.ChatViewModel;
import com.unb.devapp.escambinho.R;
import com.unb.devapp.escambinho.Util.ClickInterface;
import com.unb.devapp.escambinho.ViewHolder.ChatViewHolder;
import com.unb.devapp.escambinho.ViewHolder.EscambinhoViewHolder;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder> {
    ArrayList<ChatViewModel> mList = new ArrayList<>();
    private ClickInterface clickInterface;

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_holder_chat, parent, false);
        ChatViewHolder chatViewHolder = new ChatViewHolder(view);
        chatViewHolder.setOnClickListener(clickInterface);
        return chatViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        if (mList.get(position).getImage() == null || mList.get(position).getImage().isEmpty())
            Picasso.get().load(R.drawable.ic_person_black).into(holder.image);
        else
            Picasso.get().load(mList.get(position).getImage()).into(holder.image);

        holder.name.setText(mList.get(position).getName());
        holder.message.setText(mList.get(position).getMessage());
        holder.newMessage.setVisibility(View.GONE);
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
