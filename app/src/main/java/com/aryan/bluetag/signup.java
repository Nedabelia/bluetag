package com.aryan.bluetag;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class signup extends AppCompatActivity {

    private TextView t1;
    private Button rg_signup;
    private TextInputLayout rg_username, rg_email, rg_password, rg_repassword;
    private FirebaseAuth auth;
    private Uri imageURI;
    private String imageUri;
    private CircleImageView rg_profile;
    private FirebaseDatabase database;
    private FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        t1 = findViewById(R.id.rglog);
        rg_profile = findViewById(R.id.rgprofile);
        rg_username = findViewById(R.id.rgusername);
        rg_email = findViewById(R.id.rgemail);
        rg_password = findViewById(R.id.rgpassword);
        rg_repassword = findViewById(R.id.rgrepassword);
        rg_signup = findViewById(R.id.rgsignup);

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signup.this, login.class);
                startActivity(intent);
            }
        });

        rg_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        rg_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
    }

    private void registerUser() {
        String name = Objects.requireNonNull(rg_username.getEditText()).getText().toString().trim();
        String email = Objects.requireNonNull(rg_email.getEditText()).getText().toString().trim();
        String password = Objects.requireNonNull(rg_password.getEditText()).getText().toString().trim();
        String cpassword = Objects.requireNonNull(rg_repassword.getEditText()).getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) ||
                TextUtils.isEmpty(password) || TextUtils.isEmpty(cpassword)) {
            Toast.makeText(signup.this, "Please enter valid information", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            rg_password.setError("Password must be 6 characters or more");
            return;
        }

        if (!password.equals(cpassword)) {
            rg_password.setError("The passwords do not match");
            return;
        }

        auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String id = Objects.requireNonNull(task.getResult().getUser()).getUid();
                    DatabaseReference reference = database.getReference().child("users").child(id);
                    StorageReference storageReference = storage.getReference().child("upload").child(id);

                    if (imageURI != null) {
                        storageReference.putFile(imageURI).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                if (task.isSuccessful()) {
                                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            imageUri = uri.toString();
                                            reference.child("name").setValue(name);
                                            reference.child("email").setValue(email);
                                            reference.child("password").setValue(password);
                                            reference.child("profileImage").setValue(imageUri);
                                            registrationSuccess();
                                        }
                                    });
                                } else {
                                    Toast.makeText(signup.this, "Error uploading profile image", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        imageUri = "gs://akchat-box.appspot.com/man.png";
                        reference.child("name").setValue(name);
                        reference.child("email").setValue(email);
                        reference.child("password").setValue(password);
                        reference.child("profileImage").setValue(imageUri);
                        registrationSuccess();
                    }
                } else {
                    Toast.makeText(signup.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void registrationSuccess() {
        Toast.makeText(signup.this, "Registration successful", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(signup.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageURI = data.getData();
            rg_profile.setImageURI(imageURI);
        }
    }
}
