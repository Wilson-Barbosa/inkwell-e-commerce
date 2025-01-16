import { Injectable } from '@angular/core';
import { jwtDecode } from "jwt-decode";
import { BehaviorSubject } from 'rxjs';
import { Payload } from '../../../models/user/Payload';
import { HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

/**
 * Service responsible for managing user Session within the application.
 */
@Injectable({
    providedIn: 'root'
})
export class AuthService {

    constructor(private router: Router) {
        if (this.isTokenExpired()) {
            this.isUserLogged$ = new BehaviorSubject(false);
        } else {
            this.isUserLogged$ = new BehaviorSubject(true);
            this.setUpExpirationTimer(new Date().getMilliseconds(), Number(this.getExpDateFromLocalStorage()) * 1000);
        }
    }

    /**
     * Observable that informs subscribers if the user is currently logged or not. Logged users must have
     * a valid jwtToken (i.e credentials like email and roles should exist and the token must not be expired)
     */
    public isUserLogged$: BehaviorSubject<boolean>;

    /**
     * Sets up a user session by deconding the jwt token and saving the email, role and the token it self
     * inside the localStorage.
     *
     * @param token the jwt token from the response
     */
    decodeJwtTokenAndSetupSession(token: string): void {
        const decodedToken: Payload = jwtDecode(token) as Payload;
        localStorage.setItem("email", decodedToken.email);
        localStorage.setItem("role", decodedToken.role);
        localStorage.setItem("exp", decodedToken.exp.toString());
        localStorage.setItem("token", token);

        this.updateLoggedState(true);

        this.setUpExpirationTimer(decodedToken.iat, decodedToken.exp);
    }

    /**
     * Method that sets up a timer making it so when the token expires the logout operation is
     * perfomed and the user is notified of it.
     *
     * @param iat can be either the current time or the time when the token was issued in milliseconds
     * @param exp the expiration time in milliseconds
     */
    private setUpExpirationTimer(iat: number, exp: number): void {
        setTimeout(() => {
            this.performLogout();
        }, (exp - iat) * 1000);
    }

    /**
     * Takes current date and the expiration date to evalute if the token is already expired.
     *
     * @returns true if the token is expired and false if not
     */
    private isTokenExpired(): boolean {
        const currentDate: Date = new Date();

        if (this.getExpDateFromLocalStorage() === null) {
            return true;
        }

        if (currentDate > new Date(Number(this.getExpDateFromLocalStorage()) * 1000)) {
            return true;
        }

        return false;
    }

    /**
     * Returns a properly formatted Http Authorization Header.
     *
     * @returns an http header with the token for authentication
     */
    getAuthorizationHeader(): HttpHeaders {
        return new HttpHeaders({
            Authorization: `Bearer: ${localStorage.getItem("token")}`
        });
    }

    getEmailFromLocalStorage(): string | null {
        return localStorage.getItem("email");
    }

    getRoleFromLocalStorage(): string | null {
        return localStorage.getItem("role");
    }

    getExpDateFromLocalStorage(): string | null {
        return localStorage.getItem("exp");
    }

    updateLoggedState(isLogged: boolean): void {
        this.isUserLogged$.next(isLogged);
    }

    /**
     * There are lots of ways to handle this logout feature. The right approach would be to
     * create a blacklist so that the server can invalidate unexpired tokens. For simplicity
     * and convience I'm TEMPORARILY just removing the data from the localStorage, but this will
     * be changed very soon, as to not get in the way of implementing other important features.
     *
     * TODO: change this logout apporach to a blacklist in spring server (maybe use redis for it)
     */
    performLogout(): void {

        alert("the jwt token has expired");

        localStorage.removeItem("email");
        localStorage.removeItem("role");
        localStorage.removeItem("token");
        localStorage.removeItem("exp");

        this.updateLoggedState(false);

        this.router.navigateByUrl("/home");
    }

}
