package com.unb.devapp.escambinho.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.unb.devapp.escambinho.Adapter.CheckoutAdapter;
import com.unb.devapp.escambinho.Adapter.EscambinhoAdapter;
import com.unb.devapp.escambinho.ChatActivity;
import com.unb.devapp.escambinho.Helper.DatabaseFirebase.ChatDatabaseHelper;
import com.unb.devapp.escambinho.Helper.DatabaseFirebase.ItemDatabaseHelper;
import com.unb.devapp.escambinho.Helper.DatabaseFirebase.UserDatabaseHelper;
import com.unb.devapp.escambinho.Helper.UserHelper;
import com.unb.devapp.escambinho.Model.ChatModel;
import com.unb.devapp.escambinho.Model.ItemModel;
import com.unb.devapp.escambinho.Model.UserModel;
import com.unb.devapp.escambinho.R;
import com.unb.devapp.escambinho.Util.ClickInterface;

import java.util.ArrayList;

public class HistoricFragment extends SearchFragment implements ClickInterface {

    RecyclerView recyclerView;
    CheckoutAdapter checkoutAdapter;
    String chatId;
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
        // TODO Criar
    }

    @Override
    public void onClick(View view, int position) {
        final ItemModel itemModel = mList.get(position);
        if (itemModel.getUserId().equals(UserHelper.getUserModel().getId())) {
            Toast.makeText(getContext(), "Você já é o dono disso!", Toast.LENGTH_SHORT).show();
        }
        else if (UserHelper.getUserModel().getChats().get(itemModel.getUserId()) == null ||
                UserHelper.getUserModel().getChats().get(itemModel.getUserId()).isEmpty()) {
            ChatModel chatModel = new ChatModel(UserHelper.getUserModel().getId(), itemModel.getUserId());
            chatId = ChatDatabaseHelper.createChat(chatModel, new OnSuccessListener() {
                @Override
                public void onSuccess(Object o) {
                    UserDatabaseHelper.addChat(UserHelper.getUserModel().getId(), itemModel.getUserId(), chatId, null);
                    UserDatabaseHelper.addChat(itemModel.getUserId(), UserHelper.getUserModel().getId(), chatId, null);
                    initChat(chatId);
                }
            });
        }
        else {
            initChat(itemModel.getUserId());
        }
    }

    public void initChat(String id) {
        Intent intent = new Intent();
        intent.setClass(getContext(), ChatActivity.class);
        intent.putExtra(ChatActivity.ID_CHAT, UserHelper.getUserModel().getChats().get(id));
        startActivity(intent);
    }

    public static void addItem(ItemModel item) {
        mList.add(item);
    }
}
