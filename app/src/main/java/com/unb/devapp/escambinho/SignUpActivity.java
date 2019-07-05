package com.unb.devapp.escambinho;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.unb.devapp.escambinho.Helper.DatabaseFirebase.UserDatabaseHelper;
import com.unb.devapp.escambinho.Helper.UserHelper;
import com.unb.devapp.escambinho.Model.UserModel;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private UserModel userModel;

    private MaterialButton mContinuar, mGoback;

    private TextInputEditText mName, mBirth, mCourse, mMatr;

    private String bEmail, bPass;

    private Uri mCropImageUri;

    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mContinuar = findViewById(R.id.signup_btn_next);
        mGoback = findViewById(R.id.signup_btn_goback);
        imageButton = findViewById(R.id.activity_signup_image_upload);

        mName = findViewById(R.id.signup_edit_nome);
        mBirth = findViewById(R.id.signup_edit_birth);
        mCourse = findViewById(R.id.signup_edit_curso);
        mMatr = findViewById(R.id.signup_edit_matr);

        bEmail = getIntent().getExtras().getString("email");
        bPass = getIntent().getExtras().getString("senha");

        mCropImageUri = new Uri.Builder().build();

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

        mGoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.startPickImageActivity(SignUpActivity.this);
            }
        });
    }

    private void createUser(final UserModel userModel) {
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
    }

    private void signUpVerify(final UserModel userModel, String bEmail, String bPass) {

        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(bEmail, bPass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (mCropImageUri == null) {
                                createUser(userModel);
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
                                            userModel.setImageUrl(imageUrl);
                                            createUser(userModel);
                                        } else {
                                            createUser(userModel);
                                        }
                                    }
                                });
                            }

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
