import { Component } from '@angular/core';
import { NgFor } from '@angular/common'
import { ProductCard } from '../../../models/product/ProductCard';
import { ProductCardComponent } from '../../../shared/product-card/product-card.component';

@Component({
  selector: 'app-favorites',
  standalone: true,
  imports: [NgFor, ProductCardComponent],
  templateUrl: './favorites.component.html',
  styleUrl: './favorites.component.scss'
})
export class FavoritesComponent {

    productList: ProductCard[] = [
        {
          id: 1,
          title: "The Angular Journey",
          price: 49.99,
          discount: 0
        },
        {
          id: 2,
          title: "Spring Boot in Action",
          price: 59.99,
          discount: 15
        },
        {
          id: 3,
          title: "Java Mastery",
          price: 45.00,
          discount: 5
        },
        {
          id: 4,
          title: "Web Development with TypeScript",
          price: 39.99,
          discount: 20
        },
        {
          id: 5,
          title: "Learning SCSS for Angular",
          price: 29.99,
          discount: 25
        }
    ]
}
