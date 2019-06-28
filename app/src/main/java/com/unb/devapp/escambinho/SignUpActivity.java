package com.unb.devapp.escambinho;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.unb.devapp.escambinho.Model.UserModel;

public class SignUpActivity extends AppCompatActivity {

    UserModel userModel;

    MaterialButton mContinuar;

    TextInputEditText mName, mBirth, mCourse, mMatr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mContinuar = findViewById(R.id.signup_btn_next);

        mName = findViewById(R.id.signup_edit_nome);
        mBirth = findViewById(R.id.signup_edit_birth);
        mCourse = findViewById(R.id.signup_edit_curso);
        mMatr = findViewById(R.id.signup_edit_matr);

        mContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userModel = new UserModel();

                userModel.setName(mName.getText().toString());
                userModel.setBirthday(mBirth.getText().toString());
                userModel.setCourse(mCourse.getText().toString());
                userModel.setId(mMatr.getText().toString());
            }
        });
    }
}
