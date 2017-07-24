import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';

import {ContentService} from '../../services/content.service/content.service';
import {createObservable} from '../../shared/common/common-methods';

@Injectable()
export class FaqResolve implements Resolve<any> {

  constructor(
    private contentService: ContentService
  ) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const query = state.url.split('/').pop();
    const key = query === 'pilot' ? 'ROLE_PILOT' : query === 'client' ? 'ROLE_CLIENT' : '';


    if (!key) {
      return createObservable(null);
    }

    return this.contentService.getFaqs(key);
  }
}
