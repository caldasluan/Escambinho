package com.unb.devapp.escambinho.ViewHolder;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unb.devapp.escambinho.R;
import com.unb.devapp.escambinho.Util.ClickInterface;

public class EscambinhoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView image;
    private ClickInterface clickInterface;

    public EscambinhoViewHolder(@NonNull View itemView) {
        super(itemView);

        image = itemView.findViewById(R.id.view_holder_escambinho_image);
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