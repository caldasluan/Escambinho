package com.unb.devapp.escambinho;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;
import com.unb.devapp.escambinho.Helper.UserHelper;
import com.unb.devapp.escambinho.Model.ItemModel;
import com.unb.devapp.escambinho.Model.UserModel;

public class ItemInfoActivity extends AppCompatActivity {

    TextView name, title, autor, editora, edicao, ano, paginas, condicao, descricao, tags;
    Button backpackButton;
    ImageView itemImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_information);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Informação do produto");
        setSupportActionBar(toolbar);

        itemImage = findViewById(R.id.activity_item_information_image);
        title = findViewById(R.id.activity_item_information_title);
        name = findViewById(R.id.activity_item_information_name);
        autor = findViewById(R.id.activity_item_information_author_name);
        editora = findViewById(R.id.activity_item_information_publisher_name);
        edicao = findViewById(R.id.activity_item_information_edition_number);
        ano = findViewById(R.id.activity_item_information_year_number);
        paginas = findViewById(R.id.activity_item_information_pages_number);
        condicao = findViewById(R.id.activity_item_information_condition_content);
        descricao = findViewById(R.id.activity_item_information_description_content);
        tags = findViewById(R.id.activity_item_information_tags_content);
        backpackButton = findViewById(R.id.activity_item_information_backpack_button);
    }




}
