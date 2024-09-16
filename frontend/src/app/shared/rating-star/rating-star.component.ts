import { Component, Input } from '@angular/core';
import { NgSwitch, NgSwitchCase, NgSwitchDefault } from '@angular/common';
import { NgIf } from '@angular/common';

@Component({
    selector: 'app-rating-star',
    standalone: true,
    imports: [NgIf, NgSwitch, NgSwitchCase, NgSwitchDefault],
    templateUrl: './rating-star.component.html',
    styleUrl: './rating-star.component.scss'
})
export class RatingStarComponent {

    @Input() productScore!: number;
    @Input() numberOfRatings!: number;
    @Input() onlyDisplay!: boolean;

    constructor() {

    }

    /// Calls the http method to display a summary of the reviews
    onHover(): void {
        console.log("icon hovered");
    }

}
