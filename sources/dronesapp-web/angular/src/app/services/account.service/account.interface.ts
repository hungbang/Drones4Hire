export interface AccountModel {
  email: string;
  firstName: string;
  groups: [
    {
      id: number;
      name: string;
      role: string;
    }
  ];
  id: number;
  introduction: string;
  lastName: string;
  location: {
    address: string;
    city: string;
    coordinates: {
      latitude: number;
      longitude: number
    };
    country: {
      id: number;
      name: string
    };
    id: number;
    postcode: number;
    state: {
      code: string;
      id: number;
      name: string
    }
  };
  photoURL: string;
  summary: string;
  username: string;
  wallet?: any;
}
