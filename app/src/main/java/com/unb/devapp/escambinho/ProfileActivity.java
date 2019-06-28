package com.unb.devapp.escambinho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.unb.devapp.escambinho.Helper.UserHelper;
import com.unb.devapp.escambinho.Model.UserModel;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    CircleImageView image;
    TextView name, birthday, course, registration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Perfil");
        setSupportActionBar(toolbar);

        image = findViewById(R.id.activity_profile_image);
        name = findViewById(R.id.activity_profile_name);
        birthday = findViewById(R.id.activity_profile_birthday);
        course = findViewById(R.id.activity_profile_course);
        registration = findViewById(R.id.activity_profile_registration);

        UserModel userModel = UserHelper.getUserModel();

        Picasso.get().load(userModel.getImageUrl()).into(image);
        name.setText(userModel.getName());
        birthday.setText(userModel.getBirthday());
        course.setText(userModel.getCourse());
        registration.setText(userModel.getId());
    }
}
