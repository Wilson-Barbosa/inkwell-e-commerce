import { Component } from '@angular/core';
import { RouterOutlet, RouterLink } from '@angular/router';

@Component({
    selector: 'app-user-page',
    standalone: true,
    imports: [RouterOutlet, RouterLink],
    templateUrl: './user-page.component.html',
    styleUrl: './user-page.component.scss'
})
export class UserPageComponent {

}
