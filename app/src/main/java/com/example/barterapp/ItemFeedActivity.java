package com.example.barterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ItemFeedActivity extends AppCompatActivity {

    private EditText searchQuery;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_feed);

        firebaseAuth = FirebaseAuth.getInstance();

        Button tradeCreationButton = findViewById(R.id.tradeCreationButton);
        tradeCreationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { switchToTradeCreationFormWindow(); }
        });

        Button logoutButton = findViewById(R.id.logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                switchToLoginWindow();
            }
        });
    }

    public void switchToTradeCreationFormWindow() {
        Intent intent = new Intent(this, TradeCreationFormActivity.class);
        startActivity(intent);
    }

    public void switchToLoginWindow() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


}