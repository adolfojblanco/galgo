export interface Address {
  addressId?: number
  addressName: string
  buildingNumber: string
  floorNumber: string
  doorNumber: string
  street: string
  area: string
  postalCode: number
  city: string
  country: string
  latitude?: string
  longitude?: string
  anabled: boolean
}