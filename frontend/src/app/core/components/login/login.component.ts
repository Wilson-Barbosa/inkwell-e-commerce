import { Component, OnInit } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { RouterLink, Router } from '@angular/router';
import { MatDividerModule } from '@angular/material/divider';
import { LogService } from '../../services/web/log.service';
import { AuthService } from '../../services/security/auth.service';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { LoginRequest } from '../../../models/user/LoginRequest';

@Component({
    selector: 'app-login',
    standalone: true,
    imports: [MatInputModule,
        MatFormFieldModule,
        MatCardModule,
        MatButtonModule,
        RouterLink,
        MatDividerModule,
        ReactiveFormsModule],
    templateUrl: './login.component.html',
    styleUrl: './login.component.scss'
})
export class LoginComponent implements OnInit {

    constructor(
        private logService: LogService,
        private authService: AuthService,
        private router: Router
    ){}


    loginForm: FormGroup = new FormGroup({
        email: new FormControl(''),
        password: new FormControl('')
    });

    ngOnInit(): void {
        this.authService.isUserLogged$.subscribe({
            next: (isLogged) => {
                if(isLogged) this.router.navigateByUrl("/home");
            }
        });
    }

    logUser(): void {

        const credentials: LoginRequest = {
            email: this.loginForm.get('email')?.value,
            password: this.loginForm.get('password')?.value
        }

        this.logService.logRegisteredUser(credentials).subscribe({
            next: (response) => {
                this.authService.decodeJwtTokenAndSetupSession(response.jwt);
                this.router.navigateByUrl("/home");
            },
            error: () => console.error("Could not log user")
        })
    }

}
