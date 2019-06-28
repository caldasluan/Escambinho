package com.unb.devapp.escambinho.Helper.DatabaseFirebase;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ValueEventListener;
import com.unb.devapp.escambinho.Helper.FirebaseDatabaseHelper;
import com.unb.devapp.escambinho.Model.ChatModel;
import com.unb.devapp.escambinho.Model.ItemModel;
import com.unb.devapp.escambinho.Model.MessageModel;

public class ChatDatabaseHelper extends FirebaseDatabaseHelper {
    // Cria o chat no banco
    public static String createChat(ChatModel chatModel, OnSuccessListener successListener) {
        String id = getDatabaseReference(CHAT).push().getKey();
        chatModel.setId(id);
        getDatabaseReference(CHAT).child(chatModel.getId()).setValue(chatModel).addOnSuccessListener(successListener);
        return id;
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
