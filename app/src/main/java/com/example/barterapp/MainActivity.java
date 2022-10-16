package com.example.barterapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
<<<<<<< HEAD
import android.widget.TextView;
import android.widget.Toast;
=======
import android.widget.*;

>>>>>>> main
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText firstName;
    EditText lastName;
    EditText email;
    EditText password;
    Button register;
<<<<<<< HEAD
    boolean isError = false;
=======
    Button btnLogout;
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

=======
        btnLogout = findViewById(R.id.btnLogout);
        register = findViewById(R.id.register_submit);
        login = findViewById(R.id.register_login);
>>>>>>> main

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isError = false;
                checkDataEntered();
            }
        });

<<<<<<< HEAD
=======
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

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { switchToLoginWindow(); }
        });

>>>>>>> main
        firebaseAuth = FirebaseAuth.getInstance();
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
        }
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
    protected void setStatusMessage(String message) {
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message.trim());
        isError = true;
    }
    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isPassword(EditText text) {
        CharSequence password = text.getText().toString();
        return !TextUtils.isEmpty(password);
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    void checkDataEntered() {
        String emptyField = "This field is empty";
        String emailError = "This email is invalid";

        if (isEmpty(firstName)) {
            Toast t = Toast.makeText(this, "You must enter first name to register!", Toast.LENGTH_SHORT);
            t.show();
            setStatusMessage(emptyField);
        }

        if (isEmpty(lastName)) {setStatusMessage(emptyField);}
        if (!isEmail(email)) {setStatusMessage(emailError);}
        if (!isPassword(password)) {setStatusMessage(emptyField);}

        if(!isError){registerUser(email.toString(), password.toString());}
    }
}
