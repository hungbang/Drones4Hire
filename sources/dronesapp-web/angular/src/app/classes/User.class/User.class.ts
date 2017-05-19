import { UserInterface } from './User.interface';

export class User {
  user: UserInterface;
  constructor() {
    this.user = {
      firstName: '',
      introduction: '',
      lastName: '',
      location: {
        address: '',
        city: '',
        coordinates: {
          latitude: 0,
          longitude: 0
        },
        country: {
          id: 0,
          name: ''
        },
        id: 0,
        postcode: 0,
        state: {
          code: '',
          id: 0,
          name: ''
        }
      },
      photoURL: '',
      summary: '',
    };
  }

  getInitialUser() {
    return this.user;
  }
}

