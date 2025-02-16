import { AuthService } from './../../services/security/auth.service';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { Router, RouterLink } from '@angular/router';
import { NgIf } from '@angular/common';
import { Subscription } from 'rxjs';
import { LogService } from '../../services/web/log.service';
import { ThemeSelectorComponent } from "../theme-selector/theme-selector.component";
import { ShoppingCartCounterComponent } from "../../../shared/shopping-cart-counter/shopping-cart-counter.component";

@Component({
  selector: 'app-navbar-user-section',
  standalone: true,
  imports: [MatIconModule, RouterLink, NgIf, ThemeSelectorComponent, ShoppingCartCounterComponent],
  templateUrl: './navbar-user-section.component.html',
  styleUrl: './navbar-user-section.component.scss'
})
export class NavbarUserSectionComponent implements OnInit, OnDestroy {

    constructor(
        private authService: AuthService,
        private logService: LogService,
        private router: Router){
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
        this.logService.logOutUser().subscribe({
            next: () => this.authService.removeUserInfoFromLocalStorage()
        });
    }

    navigateToDashboard(): void {
        const role = this.authService.getRoleFromLocalStorage();

        if(role === "CUSTOMER") this.router.navigateByUrl("/customer/dashboard");
        if(role === "ADMIN") this.router.navigateByUrl("/admin/dashboard");
    }
}
