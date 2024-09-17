import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './core/components/login/login.component';
import { RegisterAccountComponent } from './core/components/register-account/register-account.component';
import { ContactComponent } from './pages/contact/contact.component';
import { SearchProductComponent } from './pages/search-product/search-product.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { UserPageComponent } from './pages/user/user-page/user-page.component';
import { UserReviewsComponent } from './pages/user/user-reviews/user-reviews.component';
import { PurchaseHistoryComponent } from './pages/user/purchase-history/purchase-history.component';
import { FavoritesComponent } from './pages/user/favorites/favorites.component';
import { PreferencesComponent } from './pages/user/preferences/preferences.component';
import { ProfileComponent } from './pages/user/profile/profile.component';
import { ProductPageComponent } from './pages/product-page/product-page.component';

export const routes: Routes = [

    // public routes
    { path: 'home', component: HomeComponent },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterAccountComponent },
    { path: 'contact-us', component: ContactComponent },
    { path: 'search-product', component: SearchProductComponent },
    { path: 'product-page/:id', component: ProductPageComponent },

    // protected routes, available only to logged users
    { path: 'user-page', component: UserPageComponent,
        children: [
            { path: 'profile', component: ProfileComponent},
            { path: 'purchase-history', component: PurchaseHistoryComponent },
            { path: 'my-reviews', component: UserReviewsComponent },
            { path: 'favorites', component: FavoritesComponent},
            { path: 'preferences', component: PreferencesComponent}
        ]
    },

    // Redirections
    { path: '', redirectTo: '/home', pathMatch: 'full' }, // redirects to the home page
    { path: '**', component: NotFoundComponent }
];
