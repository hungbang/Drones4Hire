import {Injectable} from '@angular/core';
import {Http, Headers, RequestOptions, Response} from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/observable/throw';
import 'rxjs/add/observable/of';
import 'rxjs/add/operator/catch';
import {Router} from '@angular/router';
import {Observable} from 'rxjs/Observable';
import {tokenNotExpired} from 'angular2-jwt';
import { environment } from '../../../environments/environment';

import {TokenService} from '../token.service/token.service';

@Injectable()
export class RequestService {
  public apiUrl = environment.production ? 'https://drones4hire.com/drones-api/api/v1' : 'https://stag.drones4hire.com/drones-api/api/v1'; // TODO: change prod URL
  public authorizationType = 'Bearer';

  private requests = {
    get: this.get.bind(this),
    post: this.post.bind(this),
    put: this.put.bind(this),
    remove: this.remove.bind(this),
    patch: this.patch.bind(this),
    head: this.head.bind(this)
  };

  constructor(
    public _http: Http,
    private _router: Router,
    private _tokenService: TokenService
  ) {
  }

  getCurrentToken() {
    return `${this.authorizationType} ${this._tokenService.accessToken}`;
  }

  prepareOptions() {
    if (this._tokenService.accessToken) {
      const headers = new Headers({
        'Authorization': this.getCurrentToken()

      });
      return new RequestOptions({headers: headers});
    }
    return new RequestOptions();
  }

  private extractData(res: Response) {
    let body;
    if (res['_body']) {
      body = res.json();
    }
    return body || {};
  }

  fetch(method, url, body = {}) {
    return this.requests[method](url, body)
      .map(this.extractData)
      .catch((err) => {
        if (err.status === 401 && this._router.url !== '/login') {
          if (localStorage.getItem('refreshToken') && tokenNotExpired('refreshToken')) {
            return this.refreshToken(localStorage.getItem('refreshToken'))
              .mergeMap(
                (refreshRes) => {
                  this._tokenService.setAccessToken((refreshRes as any).accessToken);
                  this._tokenService.setRefreshToken((refreshRes as any).refreshToken);
                  return this.requests[method](url, body)
                    .map(this.extractData);
                })
              .catch((refreshErr) => {
                if (refreshErr.status === 403) {
                  this._router.navigate(['/login']);
                }
                return Observable.throw(refreshErr);
              });
          } else {
            this._router.navigate(['/login']);
            return Observable.throw(err);
          }
        }
        return Observable.throw(err);
      });
  }


  get(url: string) {
    return this._http.get(this.apiUrl + url, this.prepareOptions())
  }

  post(url: string, body: {}, customHeader: any) {
    return this._http.post(this.apiUrl + url, body, customHeader || this.prepareOptions());
  }

  put(url: string, body: {}) {
    return this._http.put(this.apiUrl + url, body, this.prepareOptions());
  }

  remove(url: string) {
    return this._http.delete(this.apiUrl + url, this.prepareOptions());
  }

  patch(url: string, body: {}) {
    return this._http.patch(this.apiUrl + url, body, this.prepareOptions());
  }

  head(url: string) {
    return this._http.head(this.apiUrl + url, this.prepareOptions());
  }

  refreshToken(token) {
    return this.fetch('post', '/auth/refresh', {refreshToken: token});
  }
}
