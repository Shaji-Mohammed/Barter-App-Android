package com.example.barterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ItemFeedActivity extends AppCompatActivity {

    private EditText searchQuery;
    private Button tradeCreationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_feed);

        tradeCreationButton = findViewById(R.id.tradeCreationButton);
        tradeCreationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { switchToTradeCreationFormWindow(); }
        });
    }

    public void switchToTradeCreationFormWindow() {
        Intent intent = new Intent(this, TradeCreationFormActivity.class);
        startActivity(intent);
    }
}