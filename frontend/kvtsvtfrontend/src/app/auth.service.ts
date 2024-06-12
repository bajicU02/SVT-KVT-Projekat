import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/users'; // Replace with your backend API URL

  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<any> {
    const loginData = { email, password };
    return this.http.post<any>(`${this.apiUrl}/login`, loginData).pipe(
      tap(response => {
        // Assuming the response contains the user type
        localStorage.setItem('userType', response.userType);
      })
    );
  }
  
  getUserType(): string | null {
    return localStorage.getItem('userType');
  }

  register(user: any): Observable<any> {
    return this.http.post(`${this.apiUrl}`, user);
  }

  isAdmin(): boolean {
    return this.getUserType() === 'ADMIN';
  }
}
