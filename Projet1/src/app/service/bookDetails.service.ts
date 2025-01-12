import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookDetailsService {

  private apiUrl = 'http://localhost:9696';  

  constructor(private http: HttpClient) { }


  getAuthors(): Observable<any> {
    return this.http.get("/api/details");
  }
}
