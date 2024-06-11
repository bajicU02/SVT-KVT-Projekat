// src/app/services/auth.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient) { }

  login(email: string, password: string): Observable<any> {
    const loginData = { email, password };
    console.log('Login data:', loginData); // Log the login data being sent
    return this.http.post(`${this.apiUrl}/login`, loginData);
  }

  register(user: any): Observable<any> {
    return this.http.post(`${this.apiUrl}`, user);
  }
}
