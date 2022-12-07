package com.example.barterapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class ItemFeedActivity extends AppCompatActivity {

    private EditText searchQuery;
    private List<Item> itemsList;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;

    FirebaseAuth firebaseAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_feed);
        itemsList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

        firebaseAuth = FirebaseAuth.getInstance();
        setItemInfo();
        setAdapter();

        SearchView searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

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

        Button btn = (Button)findViewById(R.id.profile_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ItemFeedActivity.this, UserProfileActivity.class));
            }
        });
    }

    private void setAdapter() {
        adapter = new RecyclerAdapter(itemsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setItemInfo() {
        //Dummy data for testing
        itemsList.add(new Item("Tesla Model S", 500, "Elon Musk", null));
        itemsList.add(new Item("Iphone 5s", 100, "Lisa",null));
        itemsList.add(new Item("Mazda 6", 250, "Bob",null));
        itemsList.add(new Item("Bottle", 15, "Lewis",null));
        itemsList.add(new Item("Toyota Camry", 125, "david",null));
    }

    public void switchToTradeCreationFormWindow() {
        Intent intent = new Intent(this, TradeCreationFormActivity.class);
        startActivity(intent);
    }

    public void switchToLoginWindow() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void filterList(String text) {
        List<Item> filteredList = new ArrayList<>();
        for (Item item : itemsList) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(this,"No Item found",Toast.LENGTH_SHORT).show();
        } else {
            adapter.setFilteredList(filteredList);
        }
    }
}