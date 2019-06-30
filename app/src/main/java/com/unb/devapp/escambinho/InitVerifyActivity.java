package com.unb.devapp.escambinho;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.unb.devapp.escambinho.Helper.DatabaseFirebase.UserDatabaseHelper;
import com.unb.devapp.escambinho.Helper.UserHelper;
import com.unb.devapp.escambinho.Model.UserModel;

public class InitVerifyActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            UserDatabaseHelper.getUserWithEmail(mAuth.getCurrentUser().getEmail(), new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue() == null) return;

                    UserModel userModel = dataSnapshot.getChildren().iterator().next().getValue(UserModel.class);
                    UserHelper.setUserModel(userModel);
                    Log.d("ReconnectUser", userModel.getName());
                    Intent intent = new Intent(InitVerifyActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else {
            Intent intent = new Intent(InitVerifyActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_verify);
    }
}
