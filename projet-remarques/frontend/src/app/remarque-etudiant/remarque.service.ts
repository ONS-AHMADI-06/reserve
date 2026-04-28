import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Remarque } from './remarque.model';

@Injectable({
  providedIn: 'root'
})
export class RemarqueService {

  // URL du backend Spring Boot
  private apiUrl = 'http://localhost:8080/remarques';

  constructor(private http: HttpClient) {}

  // GET /remarques/{etudiantId}
  getRemarquesParEtudiant(etudiantId: number): Observable<Remarque[]> {
    return this.http.get<Remarque[]>(`${this.apiUrl}/${etudiantId}`);
  }

  // POST /remarques
  ajouterRemarque(remarque: Remarque): Observable<Remarque> {
    return this.http.post<Remarque>(this.apiUrl, remarque);
  }
}
