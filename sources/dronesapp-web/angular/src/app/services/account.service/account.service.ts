import { Injectable } from '@angular/core';

@Injectable()
export class AccountService {
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

  constructor() {}

}
