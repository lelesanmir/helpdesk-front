import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/authenticate'; // Substitua pela URL do seu backend

  constructor(private http: HttpClient) { }

  authenticate(creds: { email: string; password: string }): Observable<any> {
    return this.http.post<any>(this.apiUrl, creds)
      .pipe(
        catchError(error => {
          console.error('Erro na autenticação:', error);
          return throwError(error);
        })
      );
  }

  sucessfullLogin(token: string): void {
    localStorage.setItem('token', token);
    console.log('Token armazenado:', token);
  }

  logout(): void {
    localStorage.removeItem('token');
  }
<<<<<<< HEAD

  // Adicione o método isAuthenticated
  isAuthenticated(): boolean {
    return !!localStorage.getItem('token');
  }
=======
>>>>>>> 66374c9 (Updating and separating project files)
}
