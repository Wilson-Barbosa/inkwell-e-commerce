import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './core/components/login/login.component';
import { RegisterAccountComponent } from './core/components/register-account/register-account.component';
import { ContactComponent } from './pages/contact/contact.component';
import { SearchProductComponent } from './pages/search-product/search-product.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { ProductPageComponent } from './pages/product-page/product-page.component';
import { AdminDashboardComponent } from './pages/admin/admin-dashboard/admin-dashboard.component';
import { CustomerDashboardComponent } from './pages/customer/customer-dashboard/customer-dashboard.component';
import { AdminGuardService } from './core/services/security/admin-guard.service';
import { CustomerGuardService } from './core/services/security/customer-guard.service';
import { CustomerProfileComponent } from './pages/customer/customer-profile/customer-profile.component';
import { CustomerReviewsComponent } from './pages/customer/customer-reviews/customer-reviews.component';

export const routes: Routes = [

    // public routes
    { path: 'home', component: HomeComponent, title: "Home" },
    { path: 'login', component: LoginComponent, title: "Login Page" },
    { path: 'register', component: RegisterAccountComponent, title: "Register" },
    { path: 'contact-us', component: ContactComponent, title: "Contact Page" },
    { path: 'search-product', component: SearchProductComponent },
    { path: 'product-page/:id', component: ProductPageComponent },

    // protected admin routes
    { path: 'admin/dashboard', component: AdminDashboardComponent, canActivate: [AdminGuardService] },

    // protected customer routes
    { path: 'customer/dashboard',
        component: CustomerDashboardComponent,
        canActivate: [CustomerGuardService],
        canActivateChild: [CustomerGuardService],
        children: [
            { path: 'my-profile', component: CustomerProfileComponent },
            { path: 'my-reviews', component: CustomerReviewsComponent }
        ]
    },

    // Redirections
    { path: '', redirectTo: '/home', pathMatch: 'full' }, // redirects to the home page
    { path: '**', component: NotFoundComponent }
];
