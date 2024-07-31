import { Injectable } from '@angular/core';
import { Credentials } from '../models/credentials';
import { HttpClient } from '@angular/common/http';
import { API_CONFIG } from '../config/api.config';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  jwtService: JwtHelperService = new JwtHelperService();

  constructor(private http: HttpClient) { }

  authenticate(creds: Credentials) {
    return this.http.post(`${API_CONFIG.baseUrl}/login`, creds, {
      observe: 'response',
      responseType: 'text'
    });
  }

  sucessfullLogin(token: string) {
    localStorage.setItem('authToken', token);
  }

  isAuthenticate() {
    let token = localStorage.getItem('authToken');
    if (token != null) {
      return !this.jwtService.isTokenExpired(token);
    }
    return false;
  }
<<<<<<< HEAD

  logout() {
    localStorage.clear();
  }
=======
>>>>>>> 66374c9 (Updating and separating project files)
}
