package com.unb.devapp.escambinho.Helper.DatabaseFirebase;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ValueEventListener;
import com.unb.devapp.escambinho.Helper.FirebaseDatabaseHelper;
import com.unb.devapp.escambinho.Model.ChatModel;
import com.unb.devapp.escambinho.Model.ItemModel;
import com.unb.devapp.escambinho.Model.MessageModel;

public class ChatDatabaseHelper extends FirebaseDatabaseHelper {
    // Cria o chat no banco
    public static void createChat(ChatModel chatModel, OnSuccessListener successListener) {
        chatModel.setId(getDatabaseReference(CHAT).push().getKey());
        getDatabaseReference(CHAT).child(chatModel.getId()).setValue(chatModel).addOnSuccessListener(successListener);
    }

    // Cria uma mensagem no chat no banco
    public static void createMessage(String id, MessageModel messageModel, OnSuccessListener successListener) {
        getDatabaseReference(CHAT)
                .child(id)
                .child("messages")
                .child(getDatabaseReference(CHAT).child(id).push().getKey())
                .setValue(messageModel).addOnSuccessListener(successListener);
    }
}
