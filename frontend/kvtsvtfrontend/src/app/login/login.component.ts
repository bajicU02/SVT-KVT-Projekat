// src/app/login/login.component.ts
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../auth.service';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  standalone: true,
  imports: [FormsModule]
})
export class LoginComponent {
  email: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit() {
    console.log('Form submitted with email:', this.email, 'and password:', this.password); // Log form submission
    this.authService.login(this.email, this.password).pipe(
      catchError(error => {
        console.error('Login failed', error);
        alert('Invalid credentials');
        return of(null); // Return an observable with a null value to complete the stream
      })
    ).subscribe((response: any) => { // Explicitly declare the type
      console.log('Response from login:', response); // Log the response received
      if (response) {
        console.log('Login successful', response);
        alert('Logged in');
        this.router.navigate(['/gyms']); // Navigate to the gym list page
      }
    });
  }
}
