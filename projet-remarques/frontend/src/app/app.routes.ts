import { Routes } from '@angular/router';
import { RemarqueEtudiantComponent } from './remarque-etudiant/remarque-etudiant.component';

export const routes: Routes = [
  { path: '', component: RemarqueEtudiantComponent },
  { path: 'remarques', component: RemarqueEtudiantComponent }
];
