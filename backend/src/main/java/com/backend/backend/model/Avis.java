package com.backend.backend.model;

public class Avis {

    private String commentaire;  // Commentaire laissé par le client
    private boolean validation;  // Statut de validation de l'avis

    // Constructeurs
    public Avis() {
    }

    public Avis(String commentaire, boolean validation) {
        this.commentaire = commentaire;
        this.validation = validation;
    }

    // Getters et Setters
    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public boolean isValidation() {
        return validation;
    }

    public void setValidation(boolean validation) {
        this.validation = validation;
    }
}
