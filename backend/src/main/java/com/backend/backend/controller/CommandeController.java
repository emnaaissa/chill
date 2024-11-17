package com.backend.backend.controller;

import com.backend.backend.model.Commande;
import com.backend.backend.service.CommandeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    private final CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    // Créer une nouvelle commande
    @PostMapping
    public ResponseEntity<Commande> createCommande(@RequestBody Commande commande) throws ExecutionException, InterruptedException {
        Commande savedCommande = commandeService.createCommande(commande);
        return ResponseEntity.ok(savedCommande);
    }

    // Récupérer une commande par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommandeById(@PathVariable String id) throws ExecutionException, InterruptedException {
        Commande commande = commandeService.getCommandeById(id);
        return ResponseEntity.ok(commande);
    }

    // Mettre à jour l'état d'une commande
    @PutMapping("/{id}/etat")
    public ResponseEntity<Commande> updateEtatCommande(@PathVariable String id, @RequestParam String etat) throws ExecutionException, InterruptedException {
        Commande updatedCommande = commandeService.updateEtatCommande(id, etat);
        return ResponseEntity.ok(updatedCommande);
    }

    // Récupérer toutes les commandes
    @GetMapping
    public ResponseEntity<List<Commande>> getAllCommandes() throws ExecutionException, InterruptedException {
        List<Commande> commandes = commandeService.getAllCommandes();
        return ResponseEntity.ok(commandes);
    }

    // Supprimer une commande par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommande(@PathVariable String id) throws ExecutionException, InterruptedException {
        commandeService.deleteCommande(id);
        return ResponseEntity.noContent().build();
    }
}
