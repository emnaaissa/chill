package com.backend.backend.service;

import com.backend.backend.model.Client;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ClientService {

    private final Firestore db = FirestoreClient.getFirestore();

    public List<Client> getAllClients() throws ExecutionException, InterruptedException {
        CollectionReference clientsCollection = db.collection("clients");
        return clientsCollection.get().get().toObjects(Client.class);
    }

    public Client getClientById(String clientId) throws ExecutionException, InterruptedException {
        DocumentReference clientRef = db.collection("clients").document(clientId);
        return clientRef.get().get().toObject(Client.class);
    }

    public Client createClient(Client client) throws ExecutionException, InterruptedException {
        DocumentReference clientRef = db.collection("clients").document();
        client.setId(clientRef.getId());
        clientRef.set(client).get();
        return client;
    }

    public Client updateClient(String clientId, Client client) throws ExecutionException, InterruptedException {
        DocumentReference clientRef = db.collection("clients").document(clientId);
        client.setId(clientId);  // Assurez-vous que l'ID est défini correctement
        clientRef.set(client).get();
        return client;
    }

    public void deleteClient(String clientId) throws ExecutionException, InterruptedException {
        db.collection("clients").document(clientId).delete().get();
    }
}
