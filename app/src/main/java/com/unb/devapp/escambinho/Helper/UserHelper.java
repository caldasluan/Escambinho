package com.unb.devapp.escambinho.Helper;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.unb.devapp.escambinho.Helper.DatabaseFirebase.UserDatabaseHelper;
import com.unb.devapp.escambinho.Model.UserModel;

public class UserHelper {
    static UserModel userModel;

    public void setUserModel(String id) {
        UserDatabaseHelper.getUser(id, new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userModel = dataSnapshot.getValue(UserModel.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static UserModel getUserModel() {
        if (userModel == null) userModel = new UserModel();
        return userModel;
    }
}
