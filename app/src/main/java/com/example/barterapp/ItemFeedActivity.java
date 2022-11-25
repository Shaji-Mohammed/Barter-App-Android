package com.example.barterapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ItemFeedActivity extends AppCompatActivity {

    private EditText searchQuery;
    private ArrayList<Item> itemsList;
    private RecyclerView recyclerView;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_feed);
        itemsList = new ArrayList<>();
//        recyclerView = findViewById(R.id.recyclerView);

        firebaseAuth = FirebaseAuth.getInstance();
        setItemInfo();
        setAdapter();

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

    private void setAdapter() {
        recyclerAdapter adapter = new recyclerAdapter(itemsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setItemInfo() {
        //Dummy data for testing
        itemsList.add(new Item("Tesla Model S", 500, "Elon Musk", null));
        itemsList.add(new Item("Mazda 6", 250, "Bob",null));
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