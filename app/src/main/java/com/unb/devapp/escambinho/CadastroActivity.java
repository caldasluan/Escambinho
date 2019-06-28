package com.unb.devapp.escambinho;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.unb.devapp.escambinho.Helper.DatabaseFirebase.ItemDatabaseHelper;
import com.unb.devapp.escambinho.Helper.UserHelper;
import com.unb.devapp.escambinho.Model.ItemModel;

public class CadastroActivity extends AppCompatActivity implements View.OnClickListener {

    TextView title;
    ImageButton imageButton;
    MaterialButton createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        title = findViewById(R.id.activity_cadastro_title);
        imageButton = findViewById(R.id.activity_cadastro_image_upload);
        createButton = findViewById(R.id.activity_cadastro_button);

        imageButton.setOnClickListener(this);
        createButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_cadastro_image_upload:

                break;
            case R.id.activity_cadastro_button:
                registerItem();
                break;
        }
    }

    // TODO colocar imagem
    private void registerItem() {
        ItemModel itemModel = new ItemModel(UserHelper.getUserModel().getId(), title.getText().toString(), "");
        ItemDatabaseHelper.createItem(itemModel, new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                finish();
            }
        });
    }
}
