package com.example.barterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class TradeCreationFormActivity extends AppCompatActivity {

    private FirebaseUser user;

    private EditText itemEditText;
    private EditText descEditText;
    private EditText estimatedPriceText;

    private Trade trade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_creation_form);

        itemEditText = findViewById(R.id.trade_create_name);
        descEditText = findViewById(R.id.trade_create_desc);
        estimatedPriceText = findViewById(R.id.trade_create_estimate);

        Button createItemButton = findViewById(R.id.createItemSubmitButton);
        createItemButton.setOnClickListener(view -> createItem());

        Button backButton = findViewById(R.id.createTradeFormBack);
        backButton.setOnClickListener(view -> switchToItemFeedWindow());

        user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

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
        Double estimatedPrice = 0.0;
        try {
            estimatedPrice = Double.parseDouble(estimatedPriceText.getText().toString());
        } catch (NumberFormatException e) {
            isError = true;
        }

        if (!FormValidator.isValidItemName(name)) {
            itemEditText.setError("Item name must be " + FormValidator.MIN_ITEM_NAME_LENGTH + "-" + FormValidator.MAX_ITEM_NAME_LENGTH + " characters.");
            isError = true;
        }

        if (!FormValidator.isValidItemDesc(desc)) {
            descEditText.setError("Item description must be " + FormValidator.MIN_ITEM_DESC_LENGTH + "-" + FormValidator.MAX_ITEM_DESC_LENGTH + " characters.");
            isError = true;
        }

        if (!isError) {
            trade.save(user.getUid(), name, desc, estimatedPrice)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(TradeCreationFormActivity.this, "Posted", Toast.LENGTH_SHORT).show();
                        switchToItemFeedWindow();
                    })
                    .addOnFailureListener(e -> Toast.makeText(TradeCreationFormActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show());
        }


    }
}