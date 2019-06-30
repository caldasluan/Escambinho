package com.unb.devapp.escambinho;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private UserModel userModel;

    private MaterialButton mContinuar;

    private TextInputEditText mName, mBirth, mCourse, mMatr;

    private String bEmail, bPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mContinuar = findViewById(R.id.signup_btn_next);

        mName = findViewById(R.id.signup_edit_nome);
        mBirth = findViewById(R.id.signup_edit_birth);
        mCourse = findViewById(R.id.signup_edit_curso);
        mMatr = findViewById(R.id.signup_edit_matr);

        bEmail = getIntent().getExtras().getString("email");
        bPass = getIntent().getExtras().getString("senha");

        mContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userModel = new UserModel();

                userModel.setName(mName.getText().toString());
                userModel.setBirthday(mBirth.getText().toString());
                userModel.setCourse(mCourse.getText().toString());
                userModel.setId(mMatr.getText().toString());
                userModel.setEmail(bEmail);

                signUpVerify(userModel, bEmail, bPass);
            }
        });
    }

    private void signUpVerify(final UserModel userModel, String bEmail, String bPass) {

        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(bEmail, bPass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            UserDatabaseHelper.createUser(userModel, new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("EmailSenha", "createUserWithEmail:success");

                                    UserDatabaseHelper.getUserWithEmail(mAuth.getCurrentUser().getEmail(), new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.getValue() == null) return;

                                            UserModel userModel = dataSnapshot.getChildren().iterator().next().getValue(UserModel.class);
                                            UserHelper.setUserModel(userModel);
                                            Log.d("ReconnectUser", userModel.getName());
                                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                }
                            });
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("EmailSenha", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Erro ao criar conta. Tente novamente.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
}
