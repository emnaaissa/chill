package com.backend.backend.service;

import com.backend.backend.model.Avis;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class AvisService {

    private final Firestore db = FirestoreClient.getFirestore();

    public List<Avis> getAllAvis(String clientId) throws ExecutionException, InterruptedException {
        CollectionReference avisCollection = db.collection("clients").document(clientId).collection("avis");
        return avisCollection.get().get().toObjects(Avis.class);
    }

    public Avis getAvisById(String clientId, String avisId) throws ExecutionException, InterruptedException {
        DocumentReference avisRef = db.collection("clients").document(clientId).collection("avis").document(avisId);
        return avisRef.get().get().toObject(Avis.class);
    }

    public Avis createAvis(String clientId, Avis avis) throws ExecutionException, InterruptedException {
        DocumentReference avisRef = db.collection("clients").document(clientId).collection("avis").document();
        avisRef.set(avis).get();
        return avis;
    }

    public Avis updateAvis(String clientId, String avisId, Avis avis) throws ExecutionException, InterruptedException {
        DocumentReference avisRef = db.collection("clients").document(clientId).collection("avis").document(avisId);
        avisRef.set(avis).get();
        return avis;
    }

    public void deleteAvis(String clientId, String avisId) throws ExecutionException, InterruptedException {
        db.collection("clients").document(clientId).collection("avis").document(avisId).delete().get();
    }
}
