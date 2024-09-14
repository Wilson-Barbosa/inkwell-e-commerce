import { Component } from '@angular/core';
import { ProductCardComponent } from '../../shared/product-card/product-card.component';
import { NgFor } from '@angular/common';
import { Book } from '../../models/product/Book';
import { ProductCard } from '../../models/product/ProductCard';

@Component({
    selector: 'app-home',
    standalone: true,
    imports: [ProductCardComponent, NgFor],
    templateUrl: './home.component.html',
    styleUrl: './home.component.scss'
})
export class HomeComponent {

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
        },
        {
          id: 6,
          title: "Design Patterns in Java",
          price: 55.99,
          discount: 12
        },
        {
          id: 7,
          title: "Mastering React.js",
          price: 49.99,
          discount: 18
        },
        {
          id: 8,
          title: "Full-Stack Development with Node.js",
          price: 64.99,
          discount: 20
        },
        {
          id: 9,
          title: "Advanced TypeScript",
          price: 42.99,
          discount: 15
        },
        {
          id: 10,
          title: "Docker & Kubernetes for Developers",
          price: 59.99,
          discount: 22
        },
        {
          id: 11,
          title: "Microservices with Spring Cloud",
          price: 69.99,
          discount: 10
        },
        {
          id: 12,
          title: "Building APIs with Express.js",
          price: 39.99,
          discount: 18
        },
        {
          id: 13,
          title: "Understanding RESTful Services",
          price: 34.99,
          discount: 25
        },
        {
          id: 14,
          title: "UX Design for Developers",
          price: 27.50,
          discount: 30
        },
        {
          id: 15,
          title: "Clean Code in JavaScript",
          price: 52.99,
          discount: 10
        },
        {
          id: 16,
          title: "Functional Programming in Java",
          price: 47.99,
          discount: 12
        },
        {
          id: 17,
          title: "Mobile App Development with Flutter",
          price: 59.99,
          discount: 15
        },
        {
          id: 18,
          title: "Concurrency in Java",
          price: 54.99,
          discount: 10
        },
        {
          id: 19,
          title: "Effective Unit Testing in Java",
          price: 44.99,
          discount: 8
        },
        {
          id: 20,
          title: "DevOps for Developers",
          price: 66.99,
          discount: 20
        }
      ];



}
