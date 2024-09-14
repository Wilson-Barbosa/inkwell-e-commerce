import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { NavbarUserSectionComponent } from '../navbar-user-section/navbar-user-section.component';


@Component({
    selector: 'app-navbar',
    standalone: true,
    imports: [MatIconModule, RouterLink, RouterLinkActive, NavbarUserSectionComponent],
    templateUrl: './navbar.component.html',
    styleUrl: './navbar.component.scss'
})
export class NavbarComponent {

    isUserLogged: boolean = false;

}
