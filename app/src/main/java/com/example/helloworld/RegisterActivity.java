package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView logoImage;
    private TextView bckLogin;
    private Button btnRegister;
    private EditText editFullName, editAge, editEmail, editPassword, editConPassword;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        logoImage = (ImageView) findViewById(R.id.logoImage);
        logoImage.setOnClickListener(this);

        bckLogin = (TextView) findViewById(R.id.bckLogin);
        bckLogin.setOnClickListener(this);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

        editFullName = (EditText) findViewById(R.id.fullname);
        editAge = (EditText) findViewById(R.id.age);
        editEmail = (EditText) findViewById(R.id.emailadd);
        editPassword = (EditText) findViewById(R.id.password);
        editConPassword = (EditText) findViewById(R.id.conPassword);

        progressBar = (ProgressBar) findViewById(R.id.progressBars);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logoImage:
                startActivity(new Intent(this, LoginActivity.class));
                break;

            case R.id.bckLogin:
                startActivity(new Intent(this, LoginActivity.class));
                break;

            case R.id.btnRegister:
                btnRegister();
                break;
        }

    }

    private void btnRegister() {
        String fullname = editFullName.getText().toString().trim();
        String age = editAge.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        String conpassword = editConPassword.getText().toString().trim();

        if(fullname.isEmpty()){
            editFullName.setError("Name is required");
            editFullName.requestFocus();
            return;
        }
        if(age.isEmpty()){
            editAge.setError("Age is required");
            editAge.requestFocus();
            return;
        }
        if(email.isEmpty()){
            editEmail.setError("Email is required");
            editEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editEmail.setError("Please provide valid email!");
            editEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editPassword.setError("Password is required");
            editPassword.requestFocus();
            return;
        }
        if(!password.equals(conpassword)){
            editPassword.setError("Password are not matching");
            editPassword.requestFocus();
            editConPassword.requestFocus();
            return;
        }
        if(password.length() < 6){
            editPassword.setError("Minimum password length is 6 characters");
            editPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            User user = new User(fullname, age, email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                        finish();
                                    }else{
                                        Toast.makeText(RegisterActivity.this,"Failed to register! Try again!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(RegisterActivity.this,"Failed to register!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}