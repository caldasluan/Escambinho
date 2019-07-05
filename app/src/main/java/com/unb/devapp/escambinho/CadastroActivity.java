package com.unb.devapp.escambinho;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.unb.devapp.escambinho.Helper.DatabaseFirebase.ItemDatabaseHelper;
import com.unb.devapp.escambinho.Helper.UserHelper;
import com.unb.devapp.escambinho.Model.ItemModel;

public class CadastroActivity extends AppCompatActivity implements View.OnClickListener {

    TextView title, autor, editora, edicao, ano, paginas, condicao, descricao, tags;
    ImageButton imageButton;
    MaterialButton createButton;
    Uri mCropImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Adicionar Item");
        setSupportActionBar(toolbar);

        title = findViewById(R.id.activity_cadastro_title);
        autor = findViewById(R.id.activity_cadastro_author);
        editora = findViewById(R.id.activity_cadastro_edit);
        edicao = findViewById(R.id.activity_cadastro_edition);
        ano = findViewById(R.id.activity_cadastro_year);
        paginas = findViewById(R.id.activity_cadastro_page);
        condicao = findViewById(R.id.activity_cadastro_condition);
        descricao = findViewById(R.id.activity_cadastro_description);
        tags = findViewById(R.id.activity_cadastro_tags);
        imageButton = findViewById(R.id.activity_cadastro_image_upload);
        createButton = findViewById(R.id.activity_cadastro_button);

        imageButton.setOnClickListener(this);
        createButton.setOnClickListener(this);

        mCropImageUri = new Uri.Builder().build();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_cadastro_image_upload:
                CropImage.startPickImageActivity(CadastroActivity.this);
                break;
            case R.id.activity_cadastro_button:
                registerItem();
                break;
        }
    }

    private void registerItem() {
        if (mCropImageUri == null) {
            saveItem("");
        } else {
            final StorageReference reference = FirebaseStorage.getInstance()
                    .getReference()
                    .child(String.valueOf(System.currentTimeMillis()));
            reference.putFile(mCropImageUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) throw task.getException();
                    return reference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        String imageUrl = task.getResult().toString();
                        saveItem(imageUrl);
                    } else {
                        saveItem("");
                    }
                }
            });
        }
    }

    private void saveItem(String imageUrl) {
        ItemModel itemModel = new ItemModel(
                UserHelper.getUserModel().getId(),
                title.getText().toString(),
                imageUrl,
                autor.getText().toString(),
                editora.getText().toString(),
                edicao.getText().toString(),
                ano.getText().toString(),
                paginas.getText().toString(),
                condicao.getText().toString(),
                descricao.getText().toString(),
                tags.getText().toString());
        ItemDatabaseHelper.createItem(itemModel, new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                finish();
            }
        });
    }

    // Inicia a atividade para cortar a imagem
    private void startCropImageActivity(Uri imageUri) {
        CropImage.activity(imageUri)
                .setActivityTitle("Cortar")
                .setAspectRatio(1, 1)
                .setMinCropWindowSize(0, 0)
                .start(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE) {
            if (mCropImageUri != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Requer permissão para iniciar atividade
                startCropImageActivity(mCropImageUri);
            } else {
                Snackbar.make(imageButton, "Cancelando, permissão não concedida.", Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // handle result of pick image chooser
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri imageUri = CropImage.getPickImageResultUri(this, data);

//             For API >= 23 we need to check specifically that we have permissions to read external storage.
            if (CropImage.isReadExternalStoragePermissionsRequired(this, imageUri)) {
                // request permissions and handle the result in onRequestPermissionsResult()
                mCropImageUri = imageUri;
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE);
            } else {
                // no permissions required or already granted, can start crop image activity
                startCropImageActivity(imageUri);
            }
        } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            mCropImageUri = CropImage.getActivityResult(data).getUri();
            Picasso.get().load(mCropImageUri).into(imageButton);
        }
    }
}
