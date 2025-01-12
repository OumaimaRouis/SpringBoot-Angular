import { Component, EventEmitter, Output } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { LoginService } from '../service/login.service';
import { BookDashboardComponent } from "../book-dashboard/book-dashboard.component";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule, BookDashboardComponent],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  errorMessage: string = '';  // Pour afficher les erreurs
  isLoggedIn: boolean = false;  // Variable pour vérifier si l'utilisateur est connecté

  constructor(private loginService: LoginService) {}

  // Méthode pour effectuer la connexion
  login(): void {
    this.loginService.login(this.username, this.password).subscribe({
      next: (response) => {
        console.log(response.token);
        // Sauvegarder le token dans localStorage
        this.loginService.saveToken(response.token);
        // Mettre à jour l'état de la connexion
        this.isLoggedIn = true;
        // Optionnel: afficher un message de bienvenue
      },
      error: (err) => {
        this.errorMessage = 'Login failed! Please check your credentials.';
        console.error('Error during login:', err);
      }
    });
  }

  // Méthode pour se déconnecter
  logout(): void {
    this.loginService.logout();
    this.isLoggedIn = false;
  }
}
