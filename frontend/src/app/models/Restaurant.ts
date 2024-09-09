import { Adrress } from "./Address";

export interface Restaurant {

  id?: number,
  restaurantName: string;
  manager: string,
  mobilePhone: string;
  localPhone: string;
  address?: Adrress
}