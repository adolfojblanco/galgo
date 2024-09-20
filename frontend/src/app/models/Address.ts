export interface Address {
  addressId?: number
  buildingNumber: string
  floorNumber: string
  doorNumber: string
  street: string
  area: string
  postalCode: number
  city: string
  state: string
  country: string
  latitude?: string
  longitude?: string
  aenabled: boolean
}