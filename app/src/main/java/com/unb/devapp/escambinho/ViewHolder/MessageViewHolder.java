package com.unb.devapp.escambinho.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unb.devapp.escambinho.Adapter.MessageAdapter;
import com.unb.devapp.escambinho.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageViewHolder extends RecyclerView.ViewHolder {
    CircleImageView image;
    TextView message;

    public MessageViewHolder(@NonNull View itemView, int itemType) {
        super(itemView);
        message = itemView.findViewById(R.id.view_holder_message_text);
        if (itemType == MessageAdapter.OTHER_MESSAGE)
            image = itemView.findViewById(R.id.view_holder_message_image);
    }
}
