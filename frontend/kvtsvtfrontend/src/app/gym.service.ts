import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Gym } from './models/gym.model';

@Injectable({
  providedIn: 'root'
})
export class GymService {
  private apiUrl = 'http://localhost:8080/api/gyms'; // Replace with your backend API URL

  constructor(private http: HttpClient) {}

  getGyms(): Observable<Gym[]> {
    return this.http.get<Gym[]>(`${this.apiUrl}`);
  }

  getGymById(id: number): Observable<Gym> {
    return this.http.get<Gym>(`${this.apiUrl}/${id}`);
  }

  addRating(gymId: number, rating: number): Observable<Gym> {
    return this.http.post<Gym>(`${this.apiUrl}/${gymId}/ratings`, rating);
  }

  deleteGym(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
