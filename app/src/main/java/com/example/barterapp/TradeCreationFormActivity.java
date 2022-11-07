package com.example.barterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TradeCreationFormActivity extends AppCompatActivity {

    EditText itemEditText;
    EditText descEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_creation_form);

        itemEditText = findViewById(R.id.createItemFormItemName);
        descEditText = findViewById(R.id.createItemFormDesc);

        Button createItemButton = findViewById(R.id.createItemSubmitButton);
        createItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { createItem(); }
        });
    }

    public void createItem() {
        boolean isError = false;

        if (FormValidator.isEmpty(itemEditText.getText().toString())) {
            itemEditText.setError("Item name is required.");
            isError = true;
        }

        if (FormValidator.isEmpty(descEditText.getText().toString())) {
            descEditText.setError("Item description is required.");
            isError = true;
        }

        if (!isError) {
            Toast.makeText(TradeCreationFormActivity.this, "Created Item", Toast.LENGTH_LONG).show();
        }
    }
}