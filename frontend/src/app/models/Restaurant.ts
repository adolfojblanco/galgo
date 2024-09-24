import { Address } from "./Address";
import { RestaurantType } from "./RestaurantType";
import { User } from "./User";

export interface Restaurant {
  restaurantId?: number,
  restaurantName: string;
  mobilePhone: string;
  localPhone: string;
  address?: Address
  user?: User
  restaurantType?: RestaurantType;
}