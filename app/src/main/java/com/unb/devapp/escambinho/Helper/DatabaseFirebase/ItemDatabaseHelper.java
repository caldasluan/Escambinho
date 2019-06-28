package com.unb.devapp.escambinho.Helper.DatabaseFirebase;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ValueEventListener;
import com.unb.devapp.escambinho.Helper.FirebaseDatabaseHelper;
import com.unb.devapp.escambinho.Model.ItemModel;
import com.unb.devapp.escambinho.Model.UserModel;

public class ItemDatabaseHelper extends FirebaseDatabaseHelper {
    // Cria o item no banco
    public static void createItem(ItemModel itemModel, OnSuccessListener successListener) {
        itemModel.setId(getDatabaseReference(ITEM).push().getKey());
        getDatabaseReference(ITEM).child(itemModel.getId()).setValue(itemModel).addOnSuccessListener(successListener);
    }

    // Obtém todos os usuários
    public static void getAllItems(ValueEventListener eventListener) {
        getDatabaseReference(ITEM).addListenerForSingleValueEvent(eventListener);
    }

    // Obtém dados do usuário com Uid específica
    public static void getItemWithUid(String uid, ValueEventListener eventListener) {
        getDatabaseReference(ITEM).child(uid).addListenerForSingleValueEvent(eventListener);
    }
}
