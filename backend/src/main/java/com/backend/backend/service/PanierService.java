package com.backend.backend.service;

import com.backend.backend.model.Panier;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class PanierService {

    private final Firestore db = FirestoreClient.getFirestore();

    public List<Panier> getAllPaniers() throws ExecutionException, InterruptedException {
        CollectionReference collection = db.collection("paniers");
        return collection.get().get().toObjects(Panier.class);
    }

    public Panier getPanierById(String panierId) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("paniers").document(panierId);
        return docRef.get().get().toObject(Panier.class);
    }

    public Panier createPanier(Panier panier) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("paniers").document();
        panier.setId(docRef.getId());  // Assign an ID to Panier
        docRef.set(panier).get();  // Save to Firestore
        return panier;
    }


    public Panier updatePanier(String panierId, Panier panier) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("paniers").document(panierId);
        docRef.set(panier).get();  // Mettre à jour le panier
        return panier;
    }

    public void deletePanier(String panierId) throws ExecutionException, InterruptedException {
        db.collection("paniers").document(panierId).delete().get();
    }
}
