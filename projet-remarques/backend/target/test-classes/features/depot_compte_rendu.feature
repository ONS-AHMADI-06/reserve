Feature: Dépôt des comptes rendus

  En tant qu’étudiant
  Je veux déposer un compte rendu
  Afin de suivre l’avancement de mon projet

  Scenario: Dépôt réussi
    Given l’étudiant est connecté
    When il dépose un fichier valide
    Then le système enregistre le fichier