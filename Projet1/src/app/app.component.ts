import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { BookDashboardComponent } from "./book-dashboard/book-dashboard.component";
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { LoginComponent } from "./login/login.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [BookDashboardComponent,
    ReactiveFormsModule,
    CommonModule, LoginComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'My App';

  isLoggedIn(): boolean {
    if (typeof window !== 'undefined' && localStorage) {
      const token = localStorage.getItem('token');
      return !!token;
    }
    return false;
  }
  
}