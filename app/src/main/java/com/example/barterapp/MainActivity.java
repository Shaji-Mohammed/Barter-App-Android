package com.example.barterapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import android.view.View;
<<<<<<< HEAD
import android.widget.*;
=======
>>>>>>> main
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText firstName;
    EditText lastName;
    EditText address;
    EditText email;
    EditText password;
    Button register;
<<<<<<< HEAD
    Button btnLogout;
=======
    Button login;
>>>>>>> main

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
<<<<<<< HEAD
        register = findViewById(R.id.register);
        btnLogout = findViewById(R.id.btnLogout);
=======
        register = findViewById(R.id.register_submit);
        login = findViewById(R.id.register_login);
>>>>>>> main

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataEntered();
            }
        });

<<<<<<< HEAD
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Intent intentf = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intentf);
                finish();
                Toast.makeText(MainActivity.this, "Logout Successful", Toast.LENGTH_SHORT).show();

            }
        });

                firebaseAuth = FirebaseAuth.getInstance();

=======
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { switchToLoginWindow(); }
        });

        firebaseAuth = FirebaseAuth.getInstance();
>>>>>>> main
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = firebaseAuth.getCurrentUser();
<<<<<<< HEAD
        if (user != null) {

        }
=======
>>>>>>> main
    }

    private void registerUser(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Registered", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    protected void switchToLoginWindow() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    boolean isEmail(String email) {
        if (email == null) {
            return false;
        }
        boolean empty = email.isEmpty();
        boolean valid = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$").matcher(email).matches();
        return !empty && valid;
    }

    boolean isPassword(String password) {
        return !password.isEmpty();
    }

    boolean isEmpty(String str) {
        if (str == null) {
            return true;
        }
        return str.isEmpty();
    }

    void checkDataEntered() {
        boolean isError = false;

        if (isEmpty(firstName.getText().toString())) {
            Toast t = Toast.makeText(this, "You must enter first name to register!", Toast.LENGTH_SHORT);
            t.show();
            isError = true;
        }

        if (isEmpty(lastName.getText().toString())) {
            lastName.setError("Last name is required");
            isError = true;
        }

<<<<<<< HEAD
        if (!isEmail(email)) {
=======
        if (!isEmail(email.getText().toString())) {
>>>>>>> main
            email.setError("Enter valid email");
            isError = true;
        }

<<<<<<< HEAD
        if (!isPassword(password)) {
=======
        if (!isPassword(password.getText().toString())) {
>>>>>>> main
            password.setError("Password is required");
            isError = true;
        }

        if (!isError) {
            registerUser(email.getText().toString(), password.getText().toString());
        }
    }
}
