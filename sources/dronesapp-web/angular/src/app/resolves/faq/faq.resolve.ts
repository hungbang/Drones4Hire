import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve} from '@angular/router';

import {ContentService} from '../../services/content.service/content.service';

@Injectable()
export class FaqResolve implements Resolve<any> {

  constructor(
    private contentService: ContentService
  ) {}

  resolve(route: ActivatedRouteSnapshot) {
    return this.contentService.getFaqs();
  }
}
