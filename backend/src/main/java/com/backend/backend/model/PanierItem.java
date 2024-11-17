package com.backend.backend.model;

import com.google.cloud.firestore.annotation.DocumentId;

public class PanierItem {

    @DocumentId
    private String id;  // Unique identifier for PanierItem
    private String panierId;  // Reference to the Panier ID
    private String menuItemId;  // Reference to the MenuItem ID
    private int quantity;  // Quantity of the MenuItem in the Panier
    private double prixUnitaire;  // Price of a single MenuItem

    // Default constructor required for Firestore
    public PanierItem() {}

    // Constructor
    public PanierItem(String panierId, String menuItemId, int quantity, double prixUnitaire) {
        this.panierId = panierId;
        this.menuItemId = menuItemId;
        this.quantity = quantity;
        this.prixUnitaire = prixUnitaire;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPanierId() {
        return panierId;
    }

    public void setPanierId(String panierId) {
        this.panierId = panierId;
    }

    public String getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(String menuItemId) {
        this.menuItemId = menuItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }
}
