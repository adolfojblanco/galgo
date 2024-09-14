import { Address } from "./Address";

export interface Restaurant {

  restaurantId?: number,
  restaurantName: string;
  manager: string,
  email: string,
  mobilePhone: string;
  localPhone: string;
  address?: Address
}