import { Injectable } from '@angular/core';

@Injectable()
export class TokenService {
  public accessToken = null;
  public refreshToken = null;
  constructor(
  ) {
    this.accessToken = localStorage.getItem('accessToken');
    this.refreshToken = localStorage.getItem('refreshToken');
  }

  removeTokens() {
    console.log('tokens are removed');
    localStorage.removeItem('accessToken');
    localStorage.removeItem('refreshToken');
    this.accessToken = null;
    this.refreshToken = null;
  }

  setAccessToken(token) {
    this.accessToken = token;
    localStorage.setItem('accessToken', token);
  }

  setRefreshToken(token) {
    this.refreshToken = token;
    localStorage.setItem('refreshToken', token);
  }
}
