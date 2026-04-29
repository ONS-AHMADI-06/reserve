package com.etudiant.remarques.steps;

import com.etudiant.remarques.entity.Remarque;
import com.etudiant.remarques.repository.RemarqueRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DepotCompteRenduSteps {

    @Autowired
    private RemarqueRepository remarqueRepository;

    private boolean etudiantConnecte = false;
    private Remarque remarqueDeposee = null;

    @Given("l'étudiant est connecté")
    public void letudiantEstConnecte() {
        etudiantConnecte = true;
        assertTrue(etudiantConnecte);
        System.out.println("GIVEN : etudiant connecte OK");
    }

    @When("il dépose un fichier valide")
    public void ilDeposeUnFichierValide() {
        Remarque r = new Remarque();
        r.setContenu("Compte rendu semaine 1");
        r.setDate(LocalDateTime.now());
        r.setEtudiantId(1L);
        remarqueDeposee = remarqueRepository.save(r);
        System.out.println("WHEN : fichier depose OK");
    }

    @Then("le système enregistre le fichier")
    public void leSystemeEnregistreLeFichier() {
        assertNotNull(remarqueDeposee);
        assertNotNull(remarqueDeposee.getId());
        List<Remarque> liste = remarqueRepository.findByEtudiantId(1L);
        assertFalse(liste.isEmpty());
        System.out.println("THEN : fichier enregistre OK");
    }
}