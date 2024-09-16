import { NgIf } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { ProductCard } from '../../models/product/ProductCard';
import { RatingStarComponent } from '../rating-star/rating-star.component';
import { UsCurrencyPipe } from '../../core/pipes/us-currency.pipe';

@Component({
    selector: 'app-product-card',
    standalone: true,
    imports: [MatCardModule, MatIconModule, RatingStarComponent, NgIf, UsCurrencyPipe],
    templateUrl: './product-card.component.html',
    styleUrl: './product-card.component.scss'
})
export class ProductCardComponent implements OnInit {

    @Input() product!: ProductCard;

    originalPrice: number = 0;
    currentPrice: number = 0;

    constructor(){

    }

    ngOnInit(): void {
        this.displayPrice();
    }

    // Checks if there's a discount for this product and alters the template based on it
    displayPrice(): void {

        this.originalPrice = this.product.price;

        if(this.product.discount){
            this.currentPrice = this.product.price - this.product.price * this.product.discount / 100;
        } else {
            this.currentPrice = this.originalPrice;
        }
    }

}
