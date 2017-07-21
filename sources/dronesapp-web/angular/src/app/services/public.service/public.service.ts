import { Injectable } from '@angular/core';

import {RequestService} from '../request.service/request.service';
import {QuestionModel} from './question.interface';

@Injectable()
export class PublicService {
  profile: any = null;
  services: Array<any> = [];

  constructor(
    private _requestService: RequestService
  ) { }

  getPublicAccount(id) {
    return this._requestService.fetch('get', `/public/account/${id}`)
      .map(
        res => {
          // console.log('get public account:', res);
          this.profile = res;
          return this.profile;
        }
      );
  }

  getPublicAccountServices(id) {
    return this._requestService.fetch('get', `/public/account/${id}/services`)
      .map(
        res => {
          // console.log('get public account services:', res);
          this.services = res;
          return this.services;
        }
      );
  }

  getPublicAccountEquipments(id) {
    return this._requestService.fetch('get', `/public/account/${id}/equipments`)
      .map(
        res => {
          // console.log('get public account equipments:', res);
          return res;
        }
      );
  }

  public sendMessage(message: QuestionModel) {
    return this._requestService.fetch('post', '/public/question', message);
  }
}
