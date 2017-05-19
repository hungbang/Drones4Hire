import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { AuthorizationService } from '../../../services/authorization.service/authorization.service';
import { Router, RouterStateSnapshot } from '@angular/router';
import { AccountService } from '../../../services/account.service/account.service';

@Component({
  selector: 'f-authorization',
  templateUrl: './f-authorization.component.html',
  styleUrls: ['./f-authorization.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FAuthorizationComponent implements OnInit {
  public formData = {
    firstName: '',
    lastName: '',
    email: '',
    password: '',
    confirmPassword: '',
    username: '',
    role: ''
  };
  public isSignUpForm = true;
  public isTermOfUseChecked = false;
  constructor(
    public _authorizationService: AuthorizationService,
    private _accountService: AccountService,
    private _router: Router
  ) { }

  ngOnInit() {
    this.isSignUpForm = this._authorizationService.signUpFormActive = this._router.url === '/sign-up';
  }

  getButtonTitle() {
    return this.isSignUpForm ? 'Sign Up' : 'Login';
  }

  sendAuthorizationRequest() {
    this._authorizationService.signUpFormActive ? this.sendSignUpRequest() : this.sendLoginRequest();
  }

  sendLoginRequest() {
    this._authorizationService.sendLoginData({ email: this.formData.email, password: this.formData.password })
      .then((res) => {
        this._accountService.getUserData()
          .then((userData) => {
            this._accountService.currentUser = userData.json();
            sessionStorage.setItem('user', JSON.stringify(this._accountService.currentUser));
            this._authorizationService.isUserLogin = true;
            this._router.navigate(['/']);
          });
      })
      .catch((err) => {
        console.log(err);
      });
  }

  sendSignUpRequest() {
    this._authorizationService.sendSignUpData(this.formData)
      .then((res) => {
        this._authorizationService.signUpFormActive = false;
        this._router.navigate(['login']);
      })
      .catch((err) => {
        console.log(err);
      });
  }

}
