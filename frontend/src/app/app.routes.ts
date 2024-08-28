import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './core/login/login.component';
import { RegisterAccountComponent } from './core/register-account/register-account.component';
import { ContactComponent } from './pages/contact/contact.component';

export const routes: Routes = [
    // public routes
    { path: 'home', component: HomeComponent },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterAccountComponent },
    { path: 'contact-is', component: ContactComponent },

    // protected routes, available only to logged users

    // Redirections
    { path: '', redirectTo: '/home', pathMatch: 'full' } // redirects to the home page
];
