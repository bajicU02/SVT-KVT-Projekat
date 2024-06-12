import { Component, OnInit } from '@angular/core';
import { GymService } from '../gym.service';
import { Gym } from '../models/gym.model';
import { RouterModule, Router } from '@angular/router'; // Import Router
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-gym-list',
  templateUrl: './gym-list.component.html',
  styleUrls: ['./gym-list.component.css'],
  standalone: true,
  imports: [RouterModule, CommonModule]
})
export class GymListComponent implements OnInit {
  gyms: Gym[] = [];
  isAdmin: boolean = false; // Add this property

  constructor(private gymService: GymService, private router: Router) {} // Inject Router

  ngOnInit() {
    const userType = localStorage.getItem('userType');
    console.log('User type:', userType); // Log user type
    this.isAdmin = userType === 'ADMIN'; // Check if the user is an admin

    this.gymService.getGyms().subscribe({
      next: (gyms) => {
        this.gyms = gyms;
        console.log('Gyms:', gyms); // Log gyms to check data
      },
      error: (error) => {
        console.error('Failed to load gyms', error);
      }
    });
  }

  navigateToAddGym() {
    this.router.navigate(['/add-gym']);
  }

  getAverageRating(ratings: number[]): number {
    if (!ratings || ratings.length === 0) {
      return 0;
    }
    const total = ratings.reduce((acc, rating) => acc + rating, 0);
    return total / ratings.length;
  }
}
