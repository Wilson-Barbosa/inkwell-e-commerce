import { ProductCategory } from "./ProductCategory";
import { ProductReview } from "./ProductReview";

export interface Product {
    id: number
    title: string;
    description: string;
    price: number;
    discount: number;
    quantity: number;
    categories: ProductCategory[];
    score: number;
    reviews: ProductReview[];
}
