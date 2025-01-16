import { AuthService } from './../../services/security/auth.service';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { RouterLink } from '@angular/router';
import { NgIf } from '@angular/common';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-navbar-user-section',
  standalone: true,
  imports: [MatIconModule, RouterLink, NgIf],
  templateUrl: './navbar-user-section.component.html',
  styleUrl: './navbar-user-section.component.scss'
})
export class NavbarUserSectionComponent implements OnInit, OnDestroy {

    constructor(private authService: AuthService){
    }

    loggedSubscription: Subscription = new Subscription();
    isUserLogged: boolean = false;
    userEmail: String | null = "";

    ngOnInit(): void {
        this.loggedSubscription = this.authService.isUserLogged$.subscribe({
            next: (logStatus) => {
                this.isUserLogged = logStatus;
                this.userEmail = this.authService.getEmailFromLocalStorage();
            }
        });
    }

    ngOnDestroy(): void {
        this.loggedSubscription.unsubscribe();
    }


    logout(): void {
        this.authService.performLogout();
    }
}
