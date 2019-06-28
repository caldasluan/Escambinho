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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.unb.devapp.escambinho.Adapter.ChatAdapter;
import com.unb.devapp.escambinho.Adapter.EscambinhoAdapter;
import com.unb.devapp.escambinho.ChatActivity;
import com.unb.devapp.escambinho.Helper.DatabaseFirebase.ChatDatabaseHelper;
import com.unb.devapp.escambinho.Helper.DatabaseFirebase.ItemDatabaseHelper;
import com.unb.devapp.escambinho.Helper.DatabaseFirebase.UserDatabaseHelper;
import com.unb.devapp.escambinho.Helper.UserHelper;
import com.unb.devapp.escambinho.Model.ChatModel;
import com.unb.devapp.escambinho.Model.ChatViewModel;
import com.unb.devapp.escambinho.Model.ItemModel;
import com.unb.devapp.escambinho.Model.UserModel;
import com.unb.devapp.escambinho.R;
import com.unb.devapp.escambinho.Util.ClickInterface;

import java.util.ArrayList;
import java.util.Iterator;


public class ChatFragment extends SearchFragment implements ClickInterface, ValueEventListener {
    RecyclerView recyclerView;
    ChatAdapter chatAdapter;
    ArrayList<ChatViewModel> mList;

    public ChatFragment() {
        // Required empty public constructor
    }

    public static ChatFragment newInstance() {
        return new ChatFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mList = new ArrayList<>();

        // Cria o RecyclerView
        recyclerView = getView().findViewById(R.id.fragment_chat_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        chatAdapter = new ChatAdapter();
        chatAdapter.setOnClick(this);
        recyclerView.setAdapter(chatAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mList.clear();
        UserDatabaseHelper.getUser(UserHelper.getUserModel().getId(), new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final UserModel userModel = dataSnapshot.getValue(UserModel.class);
                UserHelper.setUserModel(userModel);
                for (final String chatId : userModel.getChats().keySet()) {
                    ChatDatabaseHelper.getChatId(userModel.getChats().get(chatId), new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getValue() == null) return;
                            String messageId = "";
                            String tempMessage = "";

                            final ChatModel chatModel = dataSnapshot.getValue(ChatModel.class);
                            if (chatModel.getMessages().size() > 0) {
                                Iterator<String> iterable = chatModel.getMessages().keySet().iterator();
                                while (iterable.hasNext()) messageId = iterable.next();
                                tempMessage = chatModel.getMessages().get(messageId).getMessage();
                            }

                            final String message = tempMessage;
                            final String chatUserId = chatModel.getUser1().equals(UserHelper.getUserModel().getId()) ? chatModel.getUser2() : chatModel.getUser1();
                            UserDatabaseHelper.getUserWithUid(chatUserId, new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    UserModel user = dataSnapshot.getValue(UserModel.class);
                                    addChat(chatModel.getId(), message, user);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
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
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent();
        intent.setClass(getContext(), ChatActivity.class);
        intent.putExtra(ChatActivity.ID_CHAT, UserHelper.getUserModel().getChats().get(mList.get(position).getChatId()));
        startActivity(intent);
    }

    public void addChat(String chatId, String message, UserModel userModel) {
        mList.add(new ChatViewModel(chatId, userModel.getImageUrl(), userModel.getName(), message, ""));
        chatAdapter.setList(mList);
    }
}
