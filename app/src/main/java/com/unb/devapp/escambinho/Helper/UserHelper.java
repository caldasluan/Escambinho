package com.unb.devapp.escambinho.Helper;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.unb.devapp.escambinho.Helper.DatabaseFirebase.UserDatabaseHelper;
import com.unb.devapp.escambinho.Model.UserModel;

public class UserHelper {
    static UserModel userModel;

    public static void setUserModel(UserModel userModel) {
        UserHelper.userModel = userModel;
    }

    public static UserModel getUserModel() {
        if (userModel == null) userModel = new UserModel();
        return userModel;
    }
}
