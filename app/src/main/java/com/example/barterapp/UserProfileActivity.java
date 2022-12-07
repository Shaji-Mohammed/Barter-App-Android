package com.example.barterapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserProfileActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseFirestore firestore;

    // Widgets
    private TextView nameDisplay;
    private TextView emailDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        firestore = FirebaseFirestore.getInstance();

        nameDisplay = findViewById(R.id.profile_name);
        emailDisplay = findViewById(R.id.profile_email);
        ImageView settingsButton = findViewById(R.id.profile_settings);

        settingsButton.setOnClickListener(view -> {
            auth.signOut();
            switchToLoginWindow();
        });
        // Set fields
        emailDisplay.setText(user.getEmail());

        firestore.collection("users").document(user.getUid()).get()
            .addOnCompleteListener(task -> {
                if (task.getResult().exists()) {
                    nameDisplay.setText(
                            String.format("%s %s",
                                    task.getResult().getString("firstName"),
                                    task.getResult().getString("lastName")
                            )
                    );
                }
            });
    }

    public void switchToLoginWindow() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
