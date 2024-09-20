import { Address } from "./Address";
import { RestaurantType } from "./RestaurantType";

export interface Restaurant {
  restaurantId?: Number,
  restaurantName: string;
  manager: string,
  email: string,
  mobilePhone: string;
  localPhone: string;
  address?: Address
  restaurantType: RestaurantType;
}