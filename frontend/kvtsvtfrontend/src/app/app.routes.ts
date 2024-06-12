import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AppComponent } from './app.component';
import { RegisterComponent } from './register/register.component';
import { GymListComponent } from './gym-list/gym-list.component';
import { GymDetailComponent } from './gym-detail/gym-detail.component';
import { AddGymComponent } from './add-gym/add-gym.component';

export const routes: Routes = [
  { path: '', component: LoginComponent }, // Redirect to login initially
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'gyms', component: GymListComponent },
  { path: 'gyms/:id', component: GymDetailComponent },
  { path: 'add-gym', component: AddGymComponent }
];
