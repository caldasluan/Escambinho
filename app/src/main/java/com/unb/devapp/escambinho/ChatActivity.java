package com.unb.devapp.escambinho;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.unb.devapp.escambinho.Adapter.ChatAdapter;
import com.unb.devapp.escambinho.Adapter.MessageAdapter;
import com.unb.devapp.escambinho.Helper.DatabaseFirebase.ChatDatabaseHelper;
import com.unb.devapp.escambinho.Helper.DatabaseFirebase.UserDatabaseHelper;
import com.unb.devapp.escambinho.Helper.UserHelper;
import com.unb.devapp.escambinho.Model.ChatModel;
import com.unb.devapp.escambinho.Model.MessageModel;
import com.unb.devapp.escambinho.Model.UserModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ChatActivity extends AppCompatActivity {

    TextInputLayout textInputLayout;
    public static final String ID_CHAT = "id_chat";
    ArrayList<MessageModel> mList = new ArrayList<>();
    String idChat = "";
    RecyclerView recyclerView;
    MessageAdapter messageAdapter;
    TextInputEditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Conversa");
        setSupportActionBar(toolbar);

        // Cria o RecyclerView
        recyclerView = findViewById(R.id.activity_chat_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        messageAdapter = new MessageAdapter();
        recyclerView.setAdapter(messageAdapter);

        idChat = getIntent().getExtras().getString(ID_CHAT);
        ChatDatabaseHelper.getChatId(idChat, new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ChatModel chatModel = dataSnapshot.getValue(ChatModel.class);
                mList.clear();
                for (String idMessageModel : chatModel.getMessages().keySet()) {
                    mList.add(chatModel.getMessages().get(idMessageModel));
                }
                Collections.sort(mList, new Comparator<MessageModel>() {
                    @Override
                    public int compare(MessageModel messageModel, MessageModel t1) {
                        return messageModel.getId().compareTo(t1.getId());
                    }
                });
                messageAdapter.setList(mList);
                UserDatabaseHelper.getUserWithUid(UserHelper.getUserModel().getId().equals(chatModel.getUser1()) ? chatModel.getUser2() : chatModel.getUser1(), new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() == null) return;
                        UserModel userModel = dataSnapshot.getValue(UserModel.class);
                        messageAdapter.setImageUrl(userModel.getImageUrl());
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

        textInputLayout = findViewById(R.id.activity_chat_input_layout);
        editText = findViewById(R.id.activity_chat_text);
        textInputLayout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                editText.setText("");
                if (!text.trim().isEmpty()) {
                    MessageModel messageModel = new MessageModel(UserHelper.getUserModel().getId(), text);
                    ChatDatabaseHelper.createMessage(idChat, messageModel, null);
                }
            }
        });
    }
}
