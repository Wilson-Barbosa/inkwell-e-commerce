import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';

@Component({
    selector: 'app-customer-dashboard',
    standalone: true,
    imports: [RouterOutlet, RouterLink],
    templateUrl: './customer-dashboard.component.html',
    styleUrl: './customer-dashboard.component.scss'
})
export class CustomerDashboardComponent {

}
