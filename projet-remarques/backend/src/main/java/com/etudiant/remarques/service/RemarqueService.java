package com.etudiant.remarques.service;

import com.etudiant.remarques.entity.Remarque;
import com.etudiant.remarques.repository.RemarqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RemarqueService {

    @Autowired
    private RemarqueRepository remarqueRepository;

    // Récupérer toutes les remarques d'un étudiant
    public List<Remarque> getRemarquesParEtudiant(Long etudiantId) {
        return remarqueRepository.findByEtudiantId(etudiantId);
    }

    // Ajouter une nouvelle remarque
    public Remarque ajouterRemarque(Remarque remarque) {
        // Définir la date automatiquement si elle est nulle
        if (remarque.getDate() == null) {
            remarque.setDate(LocalDateTime.now());
        }
        return remarqueRepository.save(remarque);
    }

    // Récupérer toutes les remarques
    public List<Remarque> getToutesLesRemarques() {
        return remarqueRepository.findAll();
    }
}
