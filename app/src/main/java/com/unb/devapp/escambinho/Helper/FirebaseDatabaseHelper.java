package com.unb.devapp.escambinho.Helper;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public abstract class FirebaseDatabaseHelper {

    protected final static String USER = "user";
    protected final static String ITEM = "item";
    protected final static String CHAT = "chat";

    protected static DatabaseReference getDatabaseReference(String reference) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        return firebaseDatabase.getReference(reference);
    }
}
