import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RemarqueService } from './remarque.service';
import { Remarque } from './remarque.model';

@Component({
  selector: 'app-remarque-etudiant',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './remarque-etudiant.component.html',
  styleUrls: ['./remarque-etudiant.component.css']
})
export class RemarqueEtudiantComponent implements OnInit {

  // Liste des remarques affichées
  remarques: Remarque[] = [];

  // ID étudiant pour la recherche
  etudiantIdRecherche: number = 1;

  // Formulaire d'ajout
  nouvelleRemarque: Remarque = {
    contenu: '',
    etudiantId: 1
  };

  // Messages de feedback
  message: string = '';
  messageType: string = ''; // 'success' ou 'error'

  // État de chargement
  chargement: boolean = false;

  constructor(private remarqueService: RemarqueService) {}

  ngOnInit(): void {
    // Charger les remarques de l'étudiant 1 au démarrage
    this.chargerRemarques();
  }

  // Charger les remarques d'un étudiant
  chargerRemarques(): void {
    this.chargement = true;
    this.remarqueService.getRemarquesParEtudiant(this.etudiantIdRecherche).subscribe({
      next: (data) => {
        this.remarques = data;
        this.chargement = false;
        if (data.length === 0) {
          this.afficherMessage(`Aucune remarque pour l'étudiant ${this.etudiantIdRecherche}`, 'info');
        }
      },
      error: (err) => {
        console.error('Erreur:', err);
        this.afficherMessage('Erreur de connexion au backend. Vérifiez que Spring Boot tourne sur le port 8080.', 'error');
        this.chargement = false;
      }
    });
  }

  // Soumettre le formulaire d'ajout
  soumettreFormulaire(): void {
    if (!this.nouvelleRemarque.contenu.trim()) {
      this.afficherMessage('Le contenu ne peut pas être vide !', 'error');
      return;
    }

    this.remarqueService.ajouterRemarque(this.nouvelleRemarque).subscribe({
      next: (remarqueCreee) => {
        this.afficherMessage('Remarque ajoutée avec succès ! ✅', 'success');
        // Réinitialiser le formulaire
        this.nouvelleRemarque = { contenu: '', etudiantId: this.nouvelleRemarque.etudiantId };
        // Recharger si même étudiant
        if (this.etudiantIdRecherche === remarqueCreee.etudiantId) {
          this.chargerRemarques();
        }
      },
      error: (err) => {
        console.error('Erreur:', err);
        this.afficherMessage('Erreur lors de l\'ajout de la remarque.', 'error');
      }
    });
  }

  // Afficher un message temporaire
  afficherMessage(msg: string, type: string): void {
    this.message = msg;
    this.messageType = type;
    setTimeout(() => { this.message = ''; }, 4000);
  }

  // Formater la date pour l'affichage
  formaterDate(dateStr: string | undefined): string {
    if (!dateStr) return '';
    const date = new Date(dateStr);
    return date.toLocaleDateString('fr-FR', {
      day: '2-digit', month: '2-digit', year: 'numeric',
      hour: '2-digit', minute: '2-digit'
    });
  }
}
