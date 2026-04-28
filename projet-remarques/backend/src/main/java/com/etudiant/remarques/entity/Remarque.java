package com.etudiant.remarques.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "remarque")
public class Remarque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String contenu;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(name = "etudiant_id", nullable = false)
    private Long etudiantId;

    // Constructeur vide (requis par JPA)
    public Remarque() {}

    // Constructeur avec paramètres
    public Remarque(String contenu, LocalDateTime date, Long etudiantId) {
        this.contenu = contenu;
        this.date = date;
        this.etudiantId = etudiantId;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getContenu() { return contenu; }
    public void setContenu(String contenu) { this.contenu = contenu; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public Long getEtudiantId() { return etudiantId; }
    public void setEtudiantId(Long etudiantId) { this.etudiantId = etudiantId; }
}
