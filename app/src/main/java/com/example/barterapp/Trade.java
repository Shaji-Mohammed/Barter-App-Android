package com.example.barterapp;

import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Trade {
    private final FirebaseFirestore firestore;

    public Trade(FirebaseFirestore firestore) {
        this.firestore = firestore;
    }

    public Task<DocumentReference> save(String userId, String name, String desc, Double estimatedPrice) {
        Map<String, Object> data = new HashMap<>();
        data.put("owner", firestore.document("users/" + userId));
        data.put("name", name);
        data.put("desc", desc);
        data.put("estimatedPrice", estimatedPrice);
        data.put("posted", new Timestamp(new Date()));
        return this.firestore.collection("trades").add(data);
    }
}
