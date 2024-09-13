import { Product } from "./Product";

export interface Book extends Product {
    author: string;
    numberOfPages: number;
    isbn: string;
}
