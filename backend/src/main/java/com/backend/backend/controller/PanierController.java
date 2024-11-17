package com.backend.backend.controller;

import com.backend.backend.model.Panier;
import com.backend.backend.service.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/paniers")
public class PanierController {

    @Autowired
    private PanierService panierService;

    @GetMapping
    public List<Panier> getAllPaniers() throws ExecutionException, InterruptedException {
        return panierService.getAllPaniers();
    }

    @GetMapping("/{panierId}")
    public Panier getPanierById(@PathVariable String panierId) throws ExecutionException, InterruptedException {
        return panierService.getPanierById(panierId);
    }

    @PostMapping
    public Panier createPanier(@RequestBody Panier panier) throws ExecutionException, InterruptedException {
        return panierService.createPanier(panier);
    }

    @PutMapping("/{panierId}")
    public Panier updatePanier(@PathVariable String panierId, @RequestBody Panier panier) throws ExecutionException, InterruptedException {
        return panierService.updatePanier(panierId, panier);
    }

    @DeleteMapping("/{panierId}")
    public void deletePanier(@PathVariable String panierId) throws ExecutionException, InterruptedException {
        panierService.deletePanier(panierId);
    }
}
