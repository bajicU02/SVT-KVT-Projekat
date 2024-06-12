import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms'; // Import FormsModule
import { CommonModule } from '@angular/common'; // Import CommonModule
import { GymService } from '../gym.service';
import { Gym } from '../models/gym.model';

@Component({
  selector: 'app-add-gym',
  templateUrl: './add-gym.component.html',
  styleUrls: ['./add-gym.component.css'],
  standalone: true,
  imports: [FormsModule, CommonModule] // Include FormsModule and CommonModule
})
export class AddGymComponent {
  gym: Gym = new Gym();
  gymDisciplines: string = '';

  constructor(private gymService: GymService, private router: Router) {}

  onSubmit(): void {
    const disciplinesArray = this.gymDisciplines.split(',').map(d => d.trim());
    const newGym: Gym = {
      ...this.gym,
      disciplines: disciplinesArray,
      ratings: [],
      workingTimes: [
        { dayOfWeek: 'Monday', openingTime: '06:00:00', closingTime: '21:00:00' },
        { dayOfWeek: 'Tuesday', openingTime: '06:00:00', closingTime: '21:00:00' },
        { dayOfWeek: 'Wednesday', openingTime: '06:00:00', closingTime: '21:00:00' },
        { dayOfWeek: 'Thursday', openingTime: '06:00:00', closingTime: '21:00:00' },
        { dayOfWeek: 'Friday', openingTime: '06:00:00', closingTime: '21:00:00' },
        { dayOfWeek: 'Saturday', openingTime: '08:00:00', closingTime: '20:00:00' },
        { dayOfWeek: 'Sunday', openingTime: '08:00:00', closingTime: '20:00:00' }
      ]
    };

    this.gymService.addGym(newGym).subscribe(
      (response) => {
        console.log('Gym added successfully', response);
        alert('Gym added successfully');
        this.router.navigate(['/gyms']);
      },
      (error) => {
        console.error('Failed to add gym', error);
        alert('Failed to add gym');
      }
    );
  }
}
