import { Injectable } from '@angular/core';

@Injectable()
export class TokenService {

  constructor() {}

  removeTokens() {
    console.log('tokens are removed');
    localStorage.removeItem('accessToken');
    localStorage.removeItem('refreshToken');
  }

  setAccessToken(token) {
    localStorage.setItem('accessToken', token);
  }

  setRefreshToken(token) {
    localStorage.setItem('refreshToken', token);
  }

  get accessToken() {
    return localStorage.getItem('accessToken') || null;
  }

  get refreshToken() {
    return localStorage.getItem('refreshToken') || null;
  }
}
