package com.example.barterapp;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Trade {
    private final FirebaseFirestore firestore;

    public Trade(FirebaseFirestore firestore) {
        this.firestore = firestore;
    }

    public Task<DocumentReference> save(String name, String desc) {
        Map<String, String> data = new HashMap<>();
        data.put("name", name);
        data.put("desc", desc);
        return this.firestore.collection("trades").add(data);
    }
}
