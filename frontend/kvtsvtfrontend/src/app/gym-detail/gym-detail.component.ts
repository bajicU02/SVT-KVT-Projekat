import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { GymService } from '../gym.service';
import { Gym } from '../models/gym.model';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-gym-detail',
  templateUrl: './gym-detail.component.html',
  styleUrls: ['./gym-detail.component.css'],
  standalone: true,
  imports: [CommonModule]
})
export class GymDetailComponent implements OnInit {
  gym: Gym = new Gym();
  userType: string | null = null;

  constructor(
    private route: ActivatedRoute,
    private gymService: GymService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getGymDetails();
    this.userType = this.authService.getUserType();
    console.log('User type:', this.userType); // Debugging line to check user type
  }

  getGymDetails(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.gymService.getGymById(id).subscribe(
      (data: Gym) => this.gym = data,
      (error: any) => console.error(error)
    );
  }

  addRating(): void {
    const rating = prompt('Enter your rating (1-5):');
    if (rating) {
      const ratingValue = Number(rating);
      if (ratingValue >= 1 && ratingValue <= 5) {
        this.gymService.addRating(this.gym.id, ratingValue).subscribe(
          () => {
            alert('Rating added successfully');
            this.getGymDetails(); // Refresh the gym details to show the new rating
          },
          (error: any) => console.error(error)
        );
      } else {
        alert('Rating must be between 1 and 5');
      }
    }
  }

  deleteGym(): void {
    if (confirm('Are you sure you want to delete this gym?')) {
      this.gymService.deleteGym(this.gym.id).subscribe(
        () => {
          alert('Gym deleted successfully');
          this.router.navigate(['/gyms']); // Navigate back to the gyms list
        },
        (error: any) => console.error(error)
      );
    }
  }
}
