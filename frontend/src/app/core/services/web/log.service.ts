import { AuthService } from './../security/auth.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RegisterRequest } from '../../../models/user/RegisterRequest';
import { RoutePath } from '../../enums/RoutePath';
import { LoginResponse } from '../../../models/user/LoginResponse';
import { LoginRequest } from '../../../models/user/LoginRequest';

@Injectable({
    providedIn: 'root'
})
export class LogService {

    private readonly apiPath: string = RoutePath.BASEURL;

    constructor(
        private http: HttpClient,
        private authService: AuthService,
    ){}

    registerNewUser(credentials: RegisterRequest): Observable<void> {
        return this.http.post<void>(`${this.apiPath}/${RoutePath.REGISTER}`, credentials);
    }

    logRegisteredUser(credentials: LoginRequest): Observable<LoginResponse> {
        return this.http.post<LoginResponse>(`${this.apiPath}/${RoutePath.LOGIN}`, credentials);
    }

    logOutUser(): Observable<void> {
        const headers: HttpHeaders = this.authService.getAuthorizationHeader();
        return this.http.post<void>(`${this.apiPath}/${RoutePath.LOGOUT}`, {}, {headers});
    }
}
