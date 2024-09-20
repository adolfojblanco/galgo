import { Restaurant } from "./Restaurant";

export interface RestaurantType {
  id?: number,
  name: string
  restaurants?: Restaurant[] | null;
  enabled: boolean;
}