import { NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Book } from '../../models/product/Book';

@Component({
    selector: 'app-product-page',
    standalone: true,
    imports: [NgIf],
    templateUrl: './product-page.component.html',
    styleUrl: './product-page.component.scss'
})
export class ProductPageComponent implements OnInit {

    product!: Book;
    isBook: boolean = false;

    constructor(
        private route: ActivatedRoute,
        private router: Router) {
    }

    ngOnInit(): void {
        // Converts the id string to number and calls the http service
        this.loadProduct(Number(this.route.snapshot.paramMap.get('id')));
    }

    //
    loadProduct(productId: number): void {
        // call the http service here
    }



}
