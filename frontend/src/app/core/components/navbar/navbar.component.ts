import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { RouterLink } from '@angular/router';
import { NavbarUserSectionComponent } from '../navbar-user-section/navbar-user-section.component';

@Component({
    selector: 'app-navbar',
    standalone: true,
    imports: [MatIconModule, RouterLink, NavbarUserSectionComponent],
    templateUrl: './navbar.component.html',
    styleUrl: './navbar.component.scss'
})
export class NavbarComponent{

    constructor(){}

    isUserLogged: boolean = false;

}
