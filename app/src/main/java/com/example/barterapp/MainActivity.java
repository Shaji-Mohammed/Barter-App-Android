package com.example.barterapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
<<<<<<< HEAD
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
=======

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import android.view.View;
>>>>>>> a8eff51fcd198c9d86b4a2780b93fed8518f057c
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
    EditText email;
    EditText password;
    Button register;
<<<<<<< HEAD
    boolean isError = false;
=======
    Button login;
>>>>>>> a8eff51fcd198c9d86b4a2780b93fed8518f057c

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register_submit);
        login = findViewById(R.id.register_login);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isError = false;
                checkDataEntered();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { switchToLoginWindow(); }
        });

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = firebaseAuth.getCurrentUser();
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
<<<<<<< HEAD
    protected void setStatusMessage(String message) {
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message.trim());
        isError = true;
    }
    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
=======

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
>>>>>>> a8eff51fcd198c9d86b4a2780b93fed8518f057c
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
        String emptyField = "This field is empty";
        String emailError = "This email is invalid";

        if (isEmpty(firstName.getText().toString())) {
            Toast t = Toast.makeText(this, "You must enter first name to register!", Toast.LENGTH_SHORT);
            t.show();
<<<<<<< HEAD
            setStatusMessage(emptyField);
        }

        if (isEmpty(lastName)) {setStatusMessage(emptyField);}
        if (!isEmail(email)) {setStatusMessage(emailError);}
        if (!isPassword(password)) {setStatusMessage(emptyField);}

        if(!isError){registerUser(email.toString(), password.toString());}
=======
            isError = true;
        }

        if (isEmpty(lastName.getText().toString())) {
            lastName.setError("Last name is required");
            isError = true;
        }

        if (!isEmail(email.getText().toString())) {
            email.setError("Enter valid email");
            isError = true;
        }

        if (!isPassword(password.getText().toString())) {
            password.setError("Password is required");
            isError = true;
        }

        if (!isError) {
            registerUser(email.getText().toString(), password.getText().toString());
        }
>>>>>>> a8eff51fcd198c9d86b4a2780b93fed8518f057c
    }
}
