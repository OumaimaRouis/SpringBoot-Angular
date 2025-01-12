import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private apiUrl = 'http://localhost:9696/login';  // URL de ton endpoint de login

  constructor(private http: HttpClient) {}

  // Méthode pour se connecter et obtenir le token
  login(username: string, password: string): Observable<any> {
    const payload = { username, password };
    return this.http.post<any>(this.apiUrl, payload);
  }

  // Sauvegarder le token dans localStorage
  saveToken(token: string): void {
    localStorage.setItem('authToken', token);
  }

  // Récupérer le token depuis localStorage
  getToken(): string | null {
    return localStorage.getItem('authToken');
  }

  // Supprimer le token lorsque l'utilisateur se déconnecte
  logout(): void {
    localStorage.removeItem('authToken');
  }
}
