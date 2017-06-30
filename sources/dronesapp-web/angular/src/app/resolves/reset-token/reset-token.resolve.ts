import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, Router} from '@angular/router';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class ResetTokenResolve implements Resolve<any> {

  constructor(
    private router: Router
  ) {}

  resolve(route: ActivatedRouteSnapshot) {
    const token = route.queryParams['token'];

    return Observable.create(observer => {
      if (!token) {
        this.router.navigate(['/password', 'forgot']);
        observer.next(null);
        observer.complete();
      }

      observer.next(token);
      observer.complete();
    });
  }
}
