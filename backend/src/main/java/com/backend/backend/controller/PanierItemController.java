package com.backend.backend.controller;

import com.backend.backend.model.PanierItem;
import com.backend.backend.service.PanierItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/paniers/{panierId}/items")
public class PanierItemController {

    @Autowired
    private PanierItemService panierItemService;

    @GetMapping
    public List<PanierItem> getAllPanierItems(@PathVariable String panierId) throws ExecutionException, InterruptedException {
        return panierItemService.getAllPanierItems(panierId);
    }

    @GetMapping("/{itemId}")
    public PanierItem getPanierItemById(@PathVariable String panierId, @PathVariable String itemId) throws ExecutionException, InterruptedException {
        return panierItemService.getPanierItemById(panierId, itemId);
    }

    @PostMapping
    public PanierItem createPanierItem(@PathVariable String panierId, @RequestBody PanierItem panierItem) throws ExecutionException, InterruptedException {
        return panierItemService.createPanierItem(panierId, panierItem);
    }

    @PutMapping("/{itemId}")
    public PanierItem updatePanierItem(@PathVariable String panierId, @PathVariable String itemId, @RequestBody PanierItem panierItem) throws ExecutionException, InterruptedException {
        return panierItemService.updatePanierItem(panierId, itemId, panierItem);
    }

    @DeleteMapping("/{itemId}")
    public void deletePanierItem(@PathVariable String panierId, @PathVariable String itemId) throws ExecutionException, InterruptedException {
        panierItemService.deletePanierItem(panierId, itemId);
    }
}
