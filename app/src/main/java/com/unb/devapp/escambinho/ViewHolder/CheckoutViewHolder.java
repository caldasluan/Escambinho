package com.unb.devapp.escambinho.ViewHolder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.unb.devapp.escambinho.R;
import com.unb.devapp.escambinho.Util.ClickInterface;

public class CheckoutViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView image;
    public TextView title;
    public MaterialButton button;
    private ClickInterface clickInterface;

    public CheckoutViewHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.view_holder_checkout_image);
        title = itemView.findViewById(R.id.view_holder_checkout_title);
        button = itemView.findViewById(R.id.view_holder_checkout_button);
    }

    public void setOnClickListener(ClickInterface clickInterface) {
        button.setOnClickListener(this);
        this.clickInterface = clickInterface;
    }

    @Override
    public void onClick(View v) {
        clickInterface.onClick(v, getAdapterPosition());
    }
}
