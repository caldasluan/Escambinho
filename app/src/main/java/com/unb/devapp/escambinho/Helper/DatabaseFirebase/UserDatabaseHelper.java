package com.unb.devapp.escambinho.Helper.DatabaseFirebase;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ValueEventListener;
import com.unb.devapp.escambinho.Helper.FirebaseDatabaseHelper;
import com.unb.devapp.escambinho.Model.UserModel;

public class UserDatabaseHelper extends FirebaseDatabaseHelper {
    // Cria o usuário no banco
    public static void createUser(UserModel userModel, OnSuccessListener successListener) {
        getDatabaseReference(USER).child(userModel.getId()).setValue(userModel).addOnSuccessListener(successListener);
    }

    // Atualiza os dados do usuário
    public static void updateUser(UserModel userModel, OnSuccessListener successListener) {
        createUser(userModel, successListener);
    }

    // Obtém todos os usuários
    public static void getAllUsers(ValueEventListener eventListener) {
        getDatabaseReference(USER).addListenerForSingleValueEvent(eventListener);
    }

    // Obtém dados do usuário com Uid específica
    public static void getUserWithUid(String uid, ValueEventListener eventListener) {
        getDatabaseReference(USER).child(uid).addListenerForSingleValueEvent(eventListener);
    }

    // Acompanha mudanças do usuário no banco de dados
    public static void getUser(String id, ValueEventListener eventListener) {
        getDatabaseReference(USER).child(id).addValueEventListener(eventListener);
    }
}
