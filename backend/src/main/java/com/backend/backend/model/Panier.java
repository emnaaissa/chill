package com.backend.backend.model;

import java.util.List;

public class Panier {

    private String id;  // Unique identifier for the Panier (cart)
    private String clientId;  // ID of the client who owns the cart
    private double total;     // Total price of items in the cart
    private boolean active;   // Whether the cart is active or not
    private List<PanierItem> items;  // List of items in the cart

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<PanierItem> getItems() {
        return items;
    }

    public void setItems(List<PanierItem> items) {
        this.items = items;
    }
}
