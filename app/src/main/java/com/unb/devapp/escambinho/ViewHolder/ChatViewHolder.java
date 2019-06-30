package com.unb.devapp.escambinho.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unb.devapp.escambinho.R;
import com.unb.devapp.escambinho.Util.ClickInterface;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public CircleImageView image;
    public TextView name, message;
    public View newMessage;
    private ClickInterface clickInterface;

    public ChatViewHolder(@NonNull View itemView) {
        super(itemView);

        image = itemView.findViewById(R.id.view_holder_chat_image);
        name = itemView.findViewById(R.id.view_holder_chat_name);
        message = itemView.findViewById(R.id.view_holder_chat_message);
        newMessage = itemView.findViewById(R.id.view_holder_chat_new_message);
    }

    public void setOnClickListener(ClickInterface clickInterface) {
        itemView.setOnClickListener(this);
        this.clickInterface = clickInterface;
    }

    @Override
    public void onClick(View v) {
        clickInterface.onClick(v, getAdapterPosition());
    }
}
