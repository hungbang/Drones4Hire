export interface UserInterface {
  firstName: string;
  introduction: string;
  lastName: string;
  location: {
    address: string;
    city: string;
    coordinates: {
      latitude: 0;
      longitude: 0
    };
    country: {
      id: 0;
      name: string
    };
    id: 0;
    postcode: 0;
    state: {
      code: string;
      id: 0;
      name: string
    }
  };
  photoURL: string;
  summary: string;
}
