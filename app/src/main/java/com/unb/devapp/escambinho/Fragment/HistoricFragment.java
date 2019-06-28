package com.unb.devapp.escambinho.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.unb.devapp.escambinho.Adapter.CheckoutAdapter;
import com.unb.devapp.escambinho.Adapter.EscambinhoAdapter;
import com.unb.devapp.escambinho.Helper.DatabaseFirebase.ItemDatabaseHelper;
import com.unb.devapp.escambinho.Model.ItemModel;
import com.unb.devapp.escambinho.R;
import com.unb.devapp.escambinho.Util.ClickInterface;

import java.util.ArrayList;

public class HistoricFragment extends SearchFragment implements ClickInterface {

    RecyclerView recyclerView;
    CheckoutAdapter checkoutAdapter;
    private static ArrayList<ItemModel> mList = new ArrayList<>();

    public HistoricFragment() {
        // Required empty public constructor
    }

    public static HistoricFragment newInstance() {
        return new HistoricFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_historic, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Cria o RecyclerView
        recyclerView = getView().findViewById(R.id.fragment_checkout_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        checkoutAdapter = new CheckoutAdapter();
        checkoutAdapter.setOnClick(this);
        recyclerView.setAdapter(checkoutAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        checkoutAdapter.setList(mList);
    }

    @Override
    public void search(String text) {
        // TODO Criar
    }

    @Override
    public void onClick(View view, int position) {
        // TODO Criar
    }

    public static void addItem(ItemModel item) {
        mList.add(item);
    }
}
