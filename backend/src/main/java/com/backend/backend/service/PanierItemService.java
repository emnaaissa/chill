package com.backend.backend.service;

import com.backend.backend.model.PanierItem;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class PanierItemService {

    private final Firestore db = FirestoreClient.getFirestore();

    public List<PanierItem> getAllPanierItems(String panierId) throws ExecutionException, InterruptedException {
        CollectionReference collection = db.collection("paniers").document(panierId).collection("items");
        return collection.get().get().toObjects(PanierItem.class);
    }

    public PanierItem getPanierItemById(String panierId, String itemId) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("paniers").document(panierId).collection("items").document(itemId);
        return docRef.get().get().toObject(PanierItem.class);
    }

    public PanierItem createPanierItem(String panierId, PanierItem panierItem) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("paniers").document(panierId).collection("items").document();
        panierItem.setId(docRef.getId());  // Générer un ID pour PanierItem
        docRef.set(panierItem).get();  // Enregistrer dans Firestore
        return panierItem;
    }

    public PanierItem updatePanierItem(String panierId, String itemId, PanierItem panierItem) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("paniers").document(panierId).collection("items").document(itemId);
        docRef.set(panierItem).get();
        return panierItem;
    }

    public void deletePanierItem(String panierId, String itemId) throws ExecutionException, InterruptedException {
        db.collection("paniers").document(panierId).collection("items").document(itemId).delete().get();
    }
}
