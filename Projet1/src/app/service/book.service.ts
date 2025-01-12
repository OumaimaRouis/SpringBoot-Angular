import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  //private apiUrl = 'http://localhost:9696';  

  constructor(private http: HttpClient) { }

  // Helper function to get Authorization headers
  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('authToken');
    return new HttpHeaders().set('Authorization', `Bearer ${token}`);
  }

  // Get all books from backend
  getBooks(): Observable<any> {
    const headers = this.getHeaders();
    return this.http.get("/api/books", { headers });
  }

  // Add a new book
  addBook(formData: FormData): Observable<any> {
    const headers = this.getHeaders();
        return this.http.post(`/api/saveWithImage`, formData, { headers })
  }

  // Get image by ID
  getImage(id: number): Observable<Blob> {
    const headers = this.getHeaders();
    return this.http.get(`/api/getImage/${id}`, { responseType: 'blob', headers });
  }

  // Update book by ID
  updateBook(book: any, id: number): Observable<any> {
    const headers = this.getHeaders();
    return this.http.put<any>("/api/update/" + id, book, { headers });
  }

  // Delete a book by ID
  deleteBook(bookId: number): Observable<any> {
    const headers = this.getHeaders();
    return this.http.delete("/api/delete/" + bookId, { headers, responseType: 'text' });
  }

  // Get all authors
  getAuthors(): Observable<any> {
    const headers = this.getHeaders();
    return this.http.get("/api/authors", { headers });
  }
}
