import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { ActivatedRouteSnapshot, CanActivate, CanActivateChild, GuardResult, MaybeAsync, Router, RouterStateSnapshot } from '@angular/router';


/**
 * Router authorization guard that checks if the user has a role of CUSTOMER before allowing
 * navigatiion to itself or it children routes.
 */
@Injectable({
    providedIn: 'root'
})
export class CustomerGuardService implements CanActivate, CanActivateChild {

    constructor(private authService: AuthService, private router: Router) { }

    canActivate(): MaybeAsync<GuardResult> {
        if (this.authService.getRoleFromLocalStorage() === "CUSTOMER") {
            return true;
        }

        this.router.navigateByUrl("/home");
        return false;
    }

    canActivateChild(): MaybeAsync<GuardResult> {
        if (this.authService.getRoleFromLocalStorage() === "CUSTOMER") {
            return true;
        }

        this.router.navigateByUrl("/home");
        return false;
    }
}
