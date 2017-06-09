export interface LocationModel {
  address: string,
  city: string,
  coordinates?: {
    latitude: number,
    longitude: number
  },
  country: {
    id: number,
    name: string
  },
  id?: number,
  postcode: number,
  state?: {
    code: string,
    id: number,
    name: string
  }
}
