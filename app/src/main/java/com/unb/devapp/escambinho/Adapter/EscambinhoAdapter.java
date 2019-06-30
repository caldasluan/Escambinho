package com.unb.devapp.escambinho.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.unb.devapp.escambinho.Fragment.EscambinhoFragment;
import com.unb.devapp.escambinho.Model.ItemModel;
import com.unb.devapp.escambinho.R;
import com.unb.devapp.escambinho.Util.ClickInterface;
import com.unb.devapp.escambinho.ViewHolder.EscambinhoViewHolder;

import java.util.ArrayList;

public class EscambinhoAdapter extends RecyclerView.Adapter<EscambinhoViewHolder> {
    private ArrayList<ItemModel> mList = new ArrayList<>();
    private ClickInterface clickInterface;

    @NonNull
    @Override
    public EscambinhoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_holder_escambinho, parent, false);
        EscambinhoViewHolder escambinhoViewHolder = new EscambinhoViewHolder(view);
        escambinhoViewHolder.setOnClickListener(clickInterface);
        return escambinhoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EscambinhoViewHolder holder, int position) {
        if (mList.get(position).getImageUrl() == null || mList.get(position).getImageUrl().isEmpty())
            Picasso.get().load(R.drawable.ic_badge).into(holder.image);
        else
            Picasso.get().load(mList.get(position).getImageUrl()).into(holder.image);
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
