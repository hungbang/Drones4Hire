import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {AuthorizationService} from '../../../services/authorization.service/authorization.service';
import {Router} from '@angular/router';
import {AccountService} from '../../../services/account.service/account.service';

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

  public submitted = false;

  constructor(public _authorizationService: AuthorizationService,
              private _accountService: AccountService,
              private _router: Router) {
  }

  ngOnInit() {
    this.isSignUpForm = this._authorizationService.signUpFormActive = this._router.url === '/sign-up';
  }

  getButtonTitle() {
    return this.isSignUpForm ? 'Sign Up' : 'Login';
  }

  sendAuthorizationRequest(e, form) {
    this._authorizationService.signUpFormActive ? this.sendSignUpRequest(e, form) : this.sendLoginRequest(e, form);
  }

  sendLoginRequest(e, form) {
    e.preventDefault();
    this.submitted = true;

    if (form.invalid) {
      return;
    }

    this._authorizationService.sendLoginData({email: this.formData.email, password: this.formData.password})
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

  sendSignUpRequest(e, form) {
    e.preventDefault();
    this.submitted = true;

    if (form.invalid) {
      return;
    }

    this._authorizationService.sendSignUpData(this.formData)
      .then((res) => {
        this._authorizationService.signUpFormActive = false;
        this.sendLoginRequest(e, form);
      })
      .catch((err) => {
        console.log(err);
      });
  }

}
