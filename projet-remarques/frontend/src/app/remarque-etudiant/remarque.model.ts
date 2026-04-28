// Modèle de données - correspond à l'entité Java
export interface Remarque {
  id?: number;
  contenu: string;
  date?: string;
  etudiantId: number;
}
