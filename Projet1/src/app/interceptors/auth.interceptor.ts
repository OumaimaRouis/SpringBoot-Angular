import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginService } from '../service/login.service'; // Met le bon chemin si nécessaire

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private loginService: LoginService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.loginService.getToken(); // Récupère le token depuis le service

    if (token) {
      // Clone la requête et ajoute l'en-tête Authorization avec le token
      const clonedRequest = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
      return next.handle(clonedRequest); // Passe la requête clonée
    }

    return next.handle(req); // Si pas de token, passe la requête d'origine
  }
}
