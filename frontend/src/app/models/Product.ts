import { Category } from "./Category";

export interface Product {
  productId?: Number,
  productName: string
  price: Number,
  category: Category,
  enabled: boolean;
}