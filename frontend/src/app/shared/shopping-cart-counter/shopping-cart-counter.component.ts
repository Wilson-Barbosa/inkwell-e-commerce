import { NgIf } from '@angular/common';
import { Component } from '@angular/core';

@Component({
    selector: 'app-shopping-cart-counter',
    standalone: true,
    imports: [NgIf],
    templateUrl: './shopping-cart-counter.component.html',
    styleUrl: './shopping-cart-counter.component.scss'
})
export class ShoppingCartCounterComponent {

    productQuantity: number = 4;

}
