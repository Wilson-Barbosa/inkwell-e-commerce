import { Component } from '@angular/core';
import { MatDividerModule } from '@angular/material/divider';
import { MatCardModule } from '@angular/material/card';
import { RouterLink } from '@angular/router';
import { LogService } from '../../services/web/log.service';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RegisterRequest } from '../../../models/user/RegisterRequest';
import { AuthService } from '../../services/security/auth.service';


@Component({
    selector: 'app-register-account',
    standalone: true,
    imports: [MatCardModule, MatDividerModule, RouterLink, ReactiveFormsModule],
    templateUrl: './register-account.component.html',
    styleUrl: './register-account.component.scss'
})
export class RegisterAccountComponent {

    constructor(private logService: LogService){
    }

    registerForm: FormGroup = new FormGroup({
        firstName: new FormControl(''),
        email: new FormControl(''),
        password: new FormControl('')
    });

    registerAccount(): void {

        const credentials: RegisterRequest = {
            firstName: this.registerForm.get('firstName')?.value,
            email: this.registerForm.get('email')?.value,
            password: this.registerForm.get('password')?.value
        };

        this.logService.registerNewUser(credentials).subscribe({
            next: () => console.info("User registered"),
            error: (error) => console.log(error)
        });
    }


    resetForm(): void {
        this.registerForm.reset();
    }

}
