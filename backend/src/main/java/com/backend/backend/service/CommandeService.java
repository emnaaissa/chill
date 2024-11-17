package com.backend.backend.service;

import com.backend.backend.model.Commande;
import com.backend.backend.model.PanierItem;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CommandeService {

    private final Firestore db = FirestoreClient.getFirestore();

    // Créer une nouvelle commande
    public Commande createCommande(Commande commande) throws ExecutionException, InterruptedException {
        // Calculer le total de la commande
        double total = 0;
        for (PanierItem item : commande.getItems()) {
            total += item.getQuantity() * item.getPrixUnitaire();
        }
        commande.setTotal(total);

        // Ajouter la commande dans Firestore
        db.collection("commandes").add(commande);

        return commande;
    }

    // Récupérer une commande par son ID
    public Commande getCommandeById(String id) throws ExecutionException, InterruptedException {
        return db.collection("commandes").document(id).get().get().toObject(Commande.class);
    }

    // Mettre à jour l'état d'une commande
    public Commande updateEtatCommande(String id, String etat) throws ExecutionException, InterruptedException {
        db.collection("commandes").document(id).update("etat", etat);
        return getCommandeById(id);
    }

    // Récupérer toutes les commandes
    public List<Commande> getAllCommandes() throws ExecutionException, InterruptedException {
        return db.collection("commandes").get().get().toObjects(Commande.class);
    }

    // Supprimer une commande par son ID
    public void deleteCommande(String id) throws ExecutionException, InterruptedException {
        db.collection("commandes").document(id).delete();
    }
}
