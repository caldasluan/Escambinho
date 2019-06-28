package com.unb.devapp.escambinho;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CadastroActivity extends AppCompatActivity {

    ImageView mImgUpload;
    TextView mTxtTitulo;
    Button mBtnCadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);


        mImgUpload = findViewById(R.id.cadastro_img_upload);
        mTxtTitulo = findViewById(R.id.cadastro_titulo);
        mBtnCadastrar = findViewById(R.id.cadastro_btn_anunciar);
    }

    public void filler() {

        mBtnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    
}
