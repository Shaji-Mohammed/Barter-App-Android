package com.example.barterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TradeCreationFormActivity extends AppCompatActivity {

    EditText itemEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_creation_form);

        itemEditText = findViewById(R.id.createItemFormItemName);

        Button createItemButton = findViewById(R.id.createItemSubmitButton);
        createItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { createItem(); }
        });
    }

    public void createItem() {
        itemEditText.setError("AHHHHHHHHHH");
    }
}