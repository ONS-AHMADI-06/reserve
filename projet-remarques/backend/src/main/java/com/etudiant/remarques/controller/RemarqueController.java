package com.etudiant.remarques.controller;

import com.etudiant.remarques.entity.Remarque;
import com.etudiant.remarques.service.RemarqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/remarques")
@CrossOrigin(origins = "http://localhost:4200") // Autoriser Angular
public class RemarqueController {

    @Autowired
    private RemarqueService remarqueService;

    // GET /remarques/{etudiantId} → Récupérer les remarques d'un étudiant
    @GetMapping("/{etudiantId}")
    public ResponseEntity<List<Remarque>> getRemarques(@PathVariable Long etudiantId) {
        List<Remarque> remarques = remarqueService.getRemarquesParEtudiant(etudiantId);
        return ResponseEntity.ok(remarques);
    }

    // POST /remarques → Ajouter une remarque
    @PostMapping
    public ResponseEntity<Remarque> ajouterRemarque(@RequestBody Remarque remarque) {
        Remarque nouvelle = remarqueService.ajouterRemarque(remarque);
        return ResponseEntity.ok(nouvelle);
    }

    // GET /remarques → Récupérer toutes les remarques (bonus)
    @GetMapping
    public ResponseEntity<List<Remarque>> getToutesRemarques() {
        return ResponseEntity.ok(remarqueService.getToutesLesRemarques());
    }
}
