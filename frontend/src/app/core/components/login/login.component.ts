import { Component, OnInit } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatCardModule } from '@angular/material/card';
import { RouterLink, Router } from '@angular/router';
import { MatDividerModule } from '@angular/material/divider';
import { LogService } from '../../services/web/log.service';
import { AuthService } from '../../services/security/auth.service';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { LoginRequest } from '../../../models/user/LoginRequest';
import { LoadingSpinnerComponent } from "../../../shared/loading-spinner/loading-spinner.component";
import { NgIf } from '@angular/common';
import { FormErrorMessageComponent } from "../../../shared/form-error-message/form-error-message.component";
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { DialogContentComponent } from '../../../shared/dialog-content/dialog-content.component';
import { ExpiredSessionDialogComponent } from '../../../shared/expired-session-dialog/expired-session-dialog.component';

@Component({
    selector: 'app-login',
    standalone: true,
    imports: [
        MatInputModule,
        MatFormFieldModule,
        MatCardModule,
        RouterLink,
        MatDividerModule,
        ReactiveFormsModule,
        LoadingSpinnerComponent,
        NgIf,
        FormErrorMessageComponent,
    ],
    templateUrl: './login.component.html',
    styleUrl: './login.component.scss'
})
export class LoginComponent implements OnInit {

    isLoginExecuting: boolean = false;
    passwordError: string = "";
    emailErrorMessage: string = "";

    constructor(
        private logService: LogService,
        private authService: AuthService,
        private router: Router,
        private dialogRef: MatDialog
    ) { }


    loginForm: FormGroup = new FormGroup({
        email: new FormControl(''),
        password: new FormControl('')
    });

    ngOnInit(): void {
        this.authService.isUserLogged$.subscribe({
            next: (isLogged) => {
                if (isLogged) this.router.navigateByUrl("/home");
            }
        });
    }

    /**
     * Calls the service to attemp a login operation. In case of a successfull login the session is setup
     * and the user is redirected to the home page. If the operation fails an error message will be sent
     * to the user's view.
    */
    logUser(): void {

        this.isLoginExecuting = true;

        const credentials: LoginRequest = {
            email: this.loginForm.get('email')?.value,
            password: this.loginForm.get('password')?.value
        }

        this.logService.logRegisteredUser(credentials).subscribe({
            next: (response) => { // if login successfull
                this.isLoginExecuting = false;
                this.authService.decodeJwtTokenAndSetupSession(response.jwt);
                this.router.navigateByUrl("/home");
            },
            error: (error) => { // if login failed
                console.log(error);
                this.isLoginExecuting = false;
            }
        })
    }


    openDialog(): void {
        this.dialogRef.open(ExpiredSessionDialogComponent);
    }

}
