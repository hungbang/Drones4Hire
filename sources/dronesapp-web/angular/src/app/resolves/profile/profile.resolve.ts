import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve} from '@angular/router';

import {PublicService} from '../../services/public.service/public.service';

@Injectable()
export class ProfileResolve implements Resolve<any> {

  constructor(
    private _publicService: PublicService
  ) {}

  resolve(route: ActivatedRouteSnapshot) {
    const user_id = route.params['user_id'];

    return this._publicService.getPublicAccount(user_id);
  }
}
