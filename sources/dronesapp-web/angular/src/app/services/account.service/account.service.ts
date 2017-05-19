import { Injectable } from '@angular/core';
import { RequestService } from '../request.service/request.service';
import 'rxjs/add/operator/map';
import { RequestOptions, Headers } from '@angular/http';

@Injectable()
export class AccountService {
  currentUser: any;
  account = {
      '_id': '58fa06d5070639d287c1e697',
      general: {
        firstName: 'Marlene',
        lastName: 'Wise',
        address: '605 Borinquen Pl',
        country: 'USA',
        state: 'Colorado',
        city: 'Stollings',
        postalCode: 708011,
        flightHours: 5,
        profileImage: 'http://placehold.it/256x256',
        introduction: 'Nostrud qui reprehenderit qui tempor id esse ad deserunt elit cupidatat aute quis. Irure dolor exercitation culpa ex id do eiusmod aliquip consequat ad nulla et. Quis eiusmod nulla ad nulla cillum labore consectetur voluptate officia. Amet dolore consequat consequat cupidatat id deserunt sunt velit. Ad adipisicing mollit fugiat veniam duis deserunt laboris ea minim exercitation ad magna. Commodo ea officia cupidatat et nisi est aliqua ex est deserunt ipsum. Velit Lorem amet commodo eu est nulla culpa pariatur ea.',
      },
      company: {
        name: 'NSPIRE',
        url: 'http://beta.json-generator.com/',
        contactName: 'Susanna Shaw',
        contactEmail: 'Carroll.Griffith@example.com',
        country: 'USA'
      }
    };

  constructor(
    private _requestService: RequestService
  ) {}

  isUserClient() {
    return this.currentUser.groups.map((group) => group.role).pop() === 'ROLE_CLIENT';
  }

  isUserPilot() {
    return this.currentUser.groups.map((group) => group.role).pop() === 'ROLE_PILOT';
  }

  getUserData() {
    return this._requestService.fetch('get', '/account');
  }

  saveUserPhoto(file) {
    const headers = new Headers({
      'Authorization': this._requestService.getCurrentToken()
    });
    return new RequestOptions({ headers: headers });
  }

  saveUserData(data) {
    return this._requestService.fetch('put', '/account', data);
  }

}
