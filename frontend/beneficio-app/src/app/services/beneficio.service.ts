import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Beneficio } from '../Models/Beneficio.model';

@Injectable({
  providedIn: 'root'
})
export class BeneficioService {

  private apiUrl = 'http://localhost:8080/api/v1/beneficios';

  constructor(private http: HttpClient) { }

  listarBeneficios(): Observable<Beneficio[]> {
    return this.http.get<Beneficio[]>(this.apiUrl);
  }

  traferirBeneficio(fromId: number, toId: number, amount: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/transferir`, { fromId, toId, amount }, { responseType: 'text' });
  }

  criarBeneficio(beneficio: Beneficio): Observable<Beneficio> {
    return this.http.post<Beneficio>(this.apiUrl, beneficio);
  }

  atualizarBeneficio(id: number, beneficio: Beneficio): Observable<Beneficio> {
    return this.http.put<Beneficio>(`${this.apiUrl}/${id}`, beneficio);
  }

  deletarBeneficio(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
