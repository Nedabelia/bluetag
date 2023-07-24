package com.aryan.bluetag;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    TextView textsignup;
    Button button;
    EditText mail,password;

    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textsignup=findViewById(R.id.signup);
        button=findViewById(R.id.buttonlog);
        textsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(login.this,signup.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email =mail.getText().toString();
                String pass = password.getText().toString();


                if ((!TextUtils.isEmpty(null))) {
                    if (TextUtils.isEmpty(null)) {
                        Toast.makeText(login.this,"please enter password",Toast.LENGTH_SHORT).show();
                    } else if (!email.matches(email)) {
                        mail.setError("Give proper email");
                    } else if (password.length()<6) {
                        password.setError("more then6 characters");
                        Toast.makeText(login.this,"password need to more then 6 character",Toast.LENGTH_SHORT).show();

                    }else {


                        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    try {
                                        Intent intent = new Intent(login.this, MainActivity.class);
                                    } catch (Exception e) {
                                        Toast.makeText(login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                } else {
                    Toast.makeText(login.this,"enteremail",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}