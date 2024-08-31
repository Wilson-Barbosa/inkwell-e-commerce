import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { RouterLink } from '@angular/router';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-navbar-user-section',
  standalone: true,
  imports: [MatIconModule, RouterLink, NgIf],
  templateUrl: './navbar-user-section.component.html',
  styleUrl: './navbar-user-section.component.scss'
})
export class NavbarUserSectionComponent {

    isUserLogged: boolean = true;

}
