package com.example.barterapp;

import android.os.Bundle;
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

    private FirebaseUser user;
    private FirebaseFirestore firestore;

    // Components
    private TextView nameDisplay;
    private TextView emailDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        user = FirebaseAuth.getInstance().getCurrentUser();
        firestore = FirebaseFirestore.getInstance();

        nameDisplay = findViewById(R.id.profile_name);
        emailDisplay = findViewById(R.id.profile_email);

        // Set fields
        emailDisplay.setText(user.getEmail());

        firestore.collection("users").document(user.getUid()).get()
            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.getResult().exists()) {
                        nameDisplay.setText(
                                String.format("%s %s",
                                        task.getResult().getString("firstName"),
                                        task.getResult().getString("lastName")
                                )
                        );
                    }
                }
            });
    }
}
