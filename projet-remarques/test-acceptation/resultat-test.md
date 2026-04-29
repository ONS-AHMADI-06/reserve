# Test d'acceptation — User Story : Dépôt des comptes rendus

## User Story
En tant qu'étudiant
Je veux déposer un compte rendu
Afin de suivre l'avancement de mon projet

## Feature testée
Feature: Dépôt des comptes rendus

  Scenario: Dépôt réussi
    Given l'étudiant est connecté
    When il dépose un fichier valide
    Then le système enregistre le fichier

## Résultats

| Étape | Action effectuée                        | Résultat attendu                  | Statut |
|-------|-----------------------------------------|-----------------------------------|--------|
| GIVEN | Ouvrir http://localhost:4200            | La page s'affiche correctement    | PASSED |
| WHEN  | Remplir le formulaire et cliquer Enregistrer | Message "Remarque ajoutée avec succès !" | PASSED |
| THEN  | GET http://localhost:8080/remarques/1   | La remarque apparaît dans le JSON | PASSED |

## Conclusion
Statut final : PASSED
Date du test : 28/04/2026
Testeur : ONS-AHMADI-06