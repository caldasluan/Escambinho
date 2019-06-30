package com.unb.devapp.escambinho;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.unb.devapp.escambinho.Helper.DatabaseFirebase.UserDatabaseHelper;
import com.unb.devapp.escambinho.Helper.UserHelper;
import com.unb.devapp.escambinho.Model.UserModel;

public class LoginActivity extends AppCompatActivity {

    private MaterialButton mEntrar;

    private TextInputEditText mEmail, mSenha;

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
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEntrar = findViewById(R.id.disabled_material_button);
        mEmail = findViewById(R.id.login_edit_email);
        mSenha = findViewById(R.id.login_edit_senha);

        mEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String sEmail, sPass;

                sEmail = mEmail.getText().toString();
                sPass = mSenha.getText().toString();

                signInVerify(sEmail, sPass);

            }
        });


    }

    private void signInVerify(final String sEmail, final String sPass) {
        mAuth.signInWithEmailAndPassword(sEmail, sPass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("EmailSenha", "signInWithEmail:success");

                            Toast.makeText(LoginActivity.this, "Bem-vindo de volta!",
                                    Toast.LENGTH_SHORT).show();


                            UserDatabaseHelper.getUserWithEmail(mAuth.getCurrentUser().getEmail(), new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.getValue() == null) return;

                                    UserModel userModel = dataSnapshot.getChildren().iterator().next().getValue(UserModel.class);
                                    UserHelper.setUserModel(userModel);
                                    Log.d("ReconnectUser", userModel.getName());
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        } else {
                            // If sign in fails, display a message to the user.

                            UserDatabaseHelper.getUserWithEmail(sEmail, new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.getValue() == null) {
                                        Toast.makeText(LoginActivity.this, "Bem-vindo ao Escambinho Unb!",
                                                Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("email", sEmail);
                                        bundle.putString("senha", sPass);
                                        intent.putExtras(bundle);

                                        startActivity(intent);

                                        finish();
                                    }
                                    else {
                                        Toast.makeText(LoginActivity.this, "Senha incorreta!",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                }
                            });

                        }

                        // ...
                    }
                });
    }
}
