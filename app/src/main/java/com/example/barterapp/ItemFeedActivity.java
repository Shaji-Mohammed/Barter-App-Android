package com.example.barterapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ItemFeedActivity extends AppCompatActivity {

    private EditText searchQuery;
    private List<Item> itemsList;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_feed);
        itemsList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        setAdapter();

        firestore.collection("trades").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                setItemInfo(task.getResult());
            }
        });
//        firestore.collection("trades").addSnapshotListener((value, error) -> {
//            if (error != null) {
//                return;
//            }
//
//            setItemInfo(value);
//        });

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

    @SuppressLint("NotifyDataSetChanged")
    private void setItemInfo(QuerySnapshot snapshot) {
        itemsList.clear();
        for (DocumentSnapshot doc: snapshot) {
            if (!doc.exists()) {
                continue;
            }

            DocumentReference ownerRef = null;
            try {
                ownerRef = doc.getDocumentReference("owner");
            } catch (RuntimeException e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
//                    Toast.makeText(this, ownerRef.getId(), Toast.LENGTH_LONG).show();

            if (ownerRef != null) {
//                Toast.makeText(this, ownerRef.getId(), Toast.LENGTH_LONG).show();
                firestore.collection("users").document(ownerRef.getId()).get()
                        .addOnCompleteListener(ownerTask -> {
                            if (ownerTask.getResult().exists()) {
                                itemsList.add(new Item(
                                        doc.getString("name"),
                                        doc.getDouble("estimatedPrice").intValue(),
                                        "HI",
                                        null
                                ));
                            }
                            adapter.notifyItemInserted(itemsList.size() - 1);
                        });
            }
        }
    }

    public void switchToTradeCreationFormWindow() {
        Intent intent = new Intent(this, TradeCreationFormActivity.class);
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