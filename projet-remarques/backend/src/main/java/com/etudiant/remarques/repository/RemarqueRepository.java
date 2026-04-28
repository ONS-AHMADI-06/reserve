package com.etudiant.remarques.repository;

import com.etudiant.remarques.entity.Remarque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RemarqueRepository extends JpaRepository<Remarque, Long> {

    // Spring Data JPA génère automatiquement la requête SQL
    List<Remarque> findByEtudiantId(Long etudiantId);
}
