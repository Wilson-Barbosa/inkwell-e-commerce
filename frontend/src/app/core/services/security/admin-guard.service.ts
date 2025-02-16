import { AuthService } from './auth.service';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanActivateChild, GuardResult, MaybeAsync, Router, RouterStateSnapshot } from '@angular/router';

/**
 * Router authorization guard that checks if the user has a role of ADMIN before allowing
 * navigatiion to itself or it children routes.
 */
@Injectable({
    providedIn: 'root'
})
export class AdminGuardService implements CanActivate, CanActivateChild {

    constructor(private authService: AuthService, private router: Router) { }


    canActivate(): MaybeAsync<GuardResult> {

        if (this.authService.getRoleFromLocalStorage() === "ADMIN") {
            return true;
        }

        this.router.navigateByUrl("/login");
        return false;
    }

    canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): MaybeAsync<GuardResult> {
        throw new Error('Method not implemented.');
    }
}
