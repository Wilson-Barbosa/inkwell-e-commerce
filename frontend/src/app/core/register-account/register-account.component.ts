import { Component } from '@angular/core';
import { MatDividerModule } from '@angular/material/divider';
import { MatCardModule } from '@angular/material/card';
import { RouterLink } from '@angular/router';


@Component({
    selector: 'app-register-account',
    standalone: true,
    imports: [MatCardModule, MatDividerModule, RouterLink],
    templateUrl: './register-account.component.html',
    styleUrl: './register-account.component.scss'
})
export class RegisterAccountComponent {

    registerAccount(): void {

    }

}
