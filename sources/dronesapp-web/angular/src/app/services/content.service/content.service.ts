import { Injectable } from '@angular/core';

import {RequestService} from '../request.service/request.service';
import {FaqModel} from './faq.interface';

@Injectable()
export class ContentService {

  constructor(
    private _requestService: RequestService
  ) { }

  public getFaqs(): FaqModel[] {
    return this._requestService.fetch('get', '/content/faqs');
  }

}
