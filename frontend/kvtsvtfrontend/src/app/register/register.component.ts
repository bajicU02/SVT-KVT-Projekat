import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../auth.service';
import { catchError, of } from 'rxjs';
import { Observer } from 'rxjs';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  standalone: true,
  imports: [FormsModule]
})

export class RegisterComponent {
  user: any = {
    name: '',
    surname: '',
    email: '',
    password: '',
    birthday: '',
    phoneNumber: '',
    address: '',
    city: '',
    zipCode: '',
    userType: 'BASIC_USER'
  };

  constructor(private authService: AuthService) {}

  onSubmit() {
    this.authService.register(this.user).pipe(
      catchError(error => {
        console.error('Registration failed', error);
        alert('Registration failed');
        return of(null); // Return an observable with a null value to complete the stream
      })
    ).subscribe((response: any) => { // Explicitly declare the type
      if (response) {
        console.log('Registration successful', response);
        alert('Registered successfully');
        // Clear the form fields
        this.clearFormFields();
      }
    });
  }

  clearFormFields() {
    this.user = {
      name: '',
      surname: '',
      email: '',
      password: '',
      birthday: '',
      phoneNumber: '',
      address: '',
      city: '',
      zipCode: '',
      userType: 'BASIC_USER'
    };
  }
}
