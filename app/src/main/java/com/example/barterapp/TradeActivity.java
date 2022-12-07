package com.example.barterapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class TradeActivity extends AppCompatActivity {

    private FirebaseFirestore firestore;

    private TextView nameText;
    private TextView descText;
    private ImageView image;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade);
        String tradeId = getIntent().getStringExtra("tradeId");

        firestore = FirebaseFirestore.getInstance();

        nameText = findViewById(R.id.trade_name);
        descText = findViewById(R.id.trade_desc);
        image = findViewById(R.id.trade_image);
        backButton = findViewById(R.id.trade_back);

        backButton.setOnClickListener(view -> switchToItemFeed());

        firestore.collection("trades").document(tradeId).get()
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    nameText.setText(task.getResult().getString("name"));
                    descText.setText(task.getResult().getString("desc"));
                    String image = task.getResult().getString("imageUrl");
                    if (image != null) {
                        Picasso.get().load(image).into(this.image);
                    }
                }
            });
    }

    private void switchToItemFeed() {
        Intent intent = new Intent(this, ItemFeedActivity.class);
        startActivity(intent);
    }
}