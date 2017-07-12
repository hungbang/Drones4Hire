import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs/Observable';

import {AccountService} from '../../services/account.service/account.service';
import {PilotGuard} from '../pilot.guard/pilot.guard';

@Injectable()
export class PilotLicensedGuard implements CanActivate {
  constructor(
    private _accountService: AccountService,
    private pilotGuard: PilotGuard
  ) {
  }

  canActivate(_route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean>|boolean {
    console.log('-activate pilot licensed guard');

    return Observable.create(observer => {
      let isPilot = false;
      this.pilotGuard.canActivate(_route, state)
        .subscribe(
          res => {
            isPilot = res;

            if (!isPilot) {
              observer.next(false);
              observer.complete();
            }

            if (this._accountService.license) {
              observer.next(this._accountService.license.verified);
              observer.complete();
            } else {
              this._accountService.getAccountLicense()
                .subscribe(
                  () => {
                    observer.next(this._accountService.license.verified);
                    observer.complete();
                  },
                  () => {
                    observer.next(false);
                    observer.complete();
                  }
                )
            }
          },
          () => {
            observer.next(false);
            observer.complete();
          }
        );
    });
  }
}
