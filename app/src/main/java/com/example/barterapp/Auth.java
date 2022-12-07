package com.example.barterapp;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class Auth {
    private final FirebaseAuth auth;
    private boolean loggedIn = false;

    public Auth(FirebaseAuth auth) {
        this.auth = auth;
    }

    public String getId() {
        return auth.getCurrentUser().getUid();
    }
}
