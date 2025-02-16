import { Component } from '@angular/core';
import { MatDividerModule } from '@angular/material/divider';
import { MatCardModule } from '@angular/material/card';
import { Router, RouterLink } from '@angular/router';
import { LogService } from '../../services/web/log.service';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RegisterRequest } from '../../../models/user/RegisterRequest';
import { NgIf } from '@angular/common';
import { LoadingSpinnerComponent } from "../../../shared/loading-spinner/loading-spinner.component";


@Component({
    selector: 'app-register-account',
    standalone: true,
    imports: [MatCardModule, MatDividerModule, RouterLink, ReactiveFormsModule, NgIf, LoadingSpinnerComponent],
    templateUrl: './register-account.component.html',
    styleUrl: './register-account.component.scss'
})
export class RegisterAccountComponent {

    isRegisterExecuting: boolean = true;
    displayErroMessages: boolean = false;

    constructor(private logService: LogService, private router: Router) {
    }

    registerForm: FormGroup = new FormGroup({
        firstName: new FormControl(''),
        email: new FormControl(''),
        password: new FormControl('')
    });

    registerAccount(): void {

        this.isRegisterExecuting = true;

        const credentials: RegisterRequest = {
            firstName: this.registerForm.get('firstName')?.value,
            email: this.registerForm.get('email')?.value,
            password: this.registerForm.get('password')?.value
        };

        this.logService.registerNewUser(credentials).subscribe({
            next: () => {
                console.info("User registered");
                this.isRegisterExecuting = false;
            },
            error: (error) => {
                this.isRegisterExecuting = false;
                console.log(error);
            }
        });
    }

    resetForm(): void {
        this.registerForm.reset();
    }

    onSuccessfullRegistration(): void {
        this.resetForm();

        alert("user registered!");

        setTimeout(() => this.router.navigateByUrl("/login"), 2000);
    }

    onFailedRegistration(): void {

    }

}
