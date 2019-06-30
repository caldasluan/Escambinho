package com.unb.devapp.escambinho.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.unb.devapp.escambinho.Adapter.EscambinhoAdapter;
import com.unb.devapp.escambinho.Helper.DatabaseFirebase.ItemDatabaseHelper;
import com.unb.devapp.escambinho.Helper.DatabaseFirebase.UserDatabaseHelper;
import com.unb.devapp.escambinho.Helper.UserHelper;
import com.unb.devapp.escambinho.Model.ItemModel;
import com.unb.devapp.escambinho.Model.UserModel;
import com.unb.devapp.escambinho.R;
import com.unb.devapp.escambinho.Util.ClickInterface;

import java.util.ArrayList;

public class EscambinhoFragment extends SearchFragment implements ClickInterface, ValueEventListener {
    RecyclerView recyclerView;
    EscambinhoAdapter escambinhoAdapter;
    ArrayList<ItemModel> mList;

    public EscambinhoFragment() {
        // Required empty public constructor
    }

    public static EscambinhoFragment newInstance() {
        return new EscambinhoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_escambinho, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mList = new ArrayList<>();

        // Cria o RecyclerView
        recyclerView = getView().findViewById(R.id.fragment_escambinho_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        escambinhoAdapter = new EscambinhoAdapter();
        escambinhoAdapter.setOnClick(this);
        recyclerView.setAdapter(escambinhoAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        ItemDatabaseHelper.getAllItems(this);
        UserDatabaseHelper.getUser(UserHelper.getUserModel().getId(), new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserModel userModel = dataSnapshot.getValue(UserModel.class);
                UserHelper.setUserModel(userModel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void search(String text) {

    }

    @Override
    public void onClick(View view, int position) {
        HistoricFragment.addItem(mList.get(position));
        Toast.makeText(getContext(), "Adicionado à mochila", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if (dataSnapshot.getValue() == null) return;

        mList.clear();

        for (DataSnapshot data : dataSnapshot.getChildren()) {
            mList.add(data.getValue(ItemModel.class));
        }

        escambinhoAdapter.setList(mList);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {}
}
