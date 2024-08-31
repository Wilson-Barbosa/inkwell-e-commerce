import { Component } from '@angular/core';
import { ProductCardComponent } from '../../shared/product-card/product-card.component';
import { NgFor } from '@angular/common';

@Component({
    selector: 'app-home',
    standalone: true,
    imports: [ProductCardComponent, NgFor],
    templateUrl: './home.component.html',
    styleUrl: './home.component.scss'
})
export class HomeComponent {

    cardList: number[] = [1, 2, 3, 4, 5];


}
