package com.example.barterapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class TradeCreationFormActivity extends AppCompatActivity {

    FirebaseFirestore firestore;

    EditText itemEditText;
    EditText descEditText;
    Button backButton;

    private Trade trade;

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

        Button backButton = findViewById(R.id.createTradeFormBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { switchToItemFeedWindow(); }
        });

        firestore = FirebaseFirestore.getInstance();

        trade = new Trade(firestore);
    }

    public void switchToItemFeedWindow() {
        Intent intent = new Intent(this, ItemFeedActivity.class);
        startActivity(intent);
    }

    public void createItem() {
        boolean isError = false;

        String name = itemEditText.getText().toString();
        String desc = descEditText.getText().toString();

        if (!FormValidator.isValidItemName(name)) {
            itemEditText.setError("Item name must be " + FormValidator.MIN_ITEM_NAME_LENGTH + "-" + FormValidator.MAX_ITEM_NAME_LENGTH + " characters.");
            isError = true;
        }

        if (!FormValidator.isValidItemDesc(desc)) {
            descEditText.setError("Item description must be " + FormValidator.MIN_ITEM_DESC_LENGTH + "-" + FormValidator.MAX_ITEM_DESC_LENGTH + " characters.");
            isError = true;
        }

        if (!isError) {
            trade.save(name, desc)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(TradeCreationFormActivity.this, "Data added", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(TradeCreationFormActivity.this, "Failed to add: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }


    }
}