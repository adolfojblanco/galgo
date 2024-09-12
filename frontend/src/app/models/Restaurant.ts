import { Adrress } from "./Address";

export interface Restaurant {

  restaurantId?: number,
  restaurantName: string;
  manager: string,
  mobilePhone: string;
  localPhone: string;
  address?: Adrress
}