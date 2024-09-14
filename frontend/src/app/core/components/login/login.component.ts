import { Component } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { RouterLink } from '@angular/router';
import { MatDividerModule } from '@angular/material/divider';

@Component({
    selector: 'app-login',
    standalone: true,
    imports: [MatInputModule, MatFormFieldModule, MatCardModule, MatButtonModule, RouterLink, MatDividerModule],
    templateUrl: './login.component.html',
    styleUrl: './login.component.scss'
})
export class LoginComponent {

    makeLogin(): void {
        alert("login works!");
    }

}
