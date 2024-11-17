package com.backend.backend.service;

import com.backend.backend.model.MenuItem;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class MenuItemService {

    private final Firestore db = FirestoreClient.getFirestore();

    public List<MenuItem> getAllMenuItems() throws ExecutionException, InterruptedException {
        List<MenuItem> items = new ArrayList<>();
        for (DocumentSnapshot doc : db.collection("menuItems").get().get().getDocuments()) {
            items.add(doc.toObject(MenuItem.class));
        }
        return items;
    }

    public MenuItem getMenuItemById(String id) throws ExecutionException, InterruptedException {
        DocumentSnapshot doc = db.collection("menuItems").document(id).get().get();
        return doc.exists() ? doc.toObject(MenuItem.class) : null;
    }

    public String createMenuItem(MenuItem menuItem, String categoryId) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("menuItems").document();
        menuItem.setIdItem(docRef.getId());
        db.collection("categories").document(categoryId).collection("menuItems").document(docRef.getId()).set(menuItem).get();
        return docRef.getId();
    }

    public String updateMenuItem(String id, MenuItem menuItem, String categoryId) throws ExecutionException, InterruptedException {
        db.collection("categories").document(categoryId).collection("menuItems").document(id).set(menuItem).get();
        return id;
    }

    public void deleteMenuItem(String id) throws ExecutionException, InterruptedException {
        db.collection("menuItems").document(id).delete().get();
    }
}
