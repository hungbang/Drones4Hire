import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {Router} from '@angular/router';
import {NgProgressService} from 'ngx-progressbar';

import {AuthorizationService} from '../../../services/authorization.service/authorization.service';
import {AccountService} from '../../../services/account.service/account.service';
import {ToastrService} from '../../../services/toastr.service/toastr.service';

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

  constructor(
    public _authorizationService: AuthorizationService,
    private _accountService: AccountService,
    private _router: Router,
    private toastrService: ToastrService,
    private progressbarService: NgProgressService
  ) {
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

    this.progressbarService.start();
    this._authorizationService.signIn({email: this.formData.email, password: this.formData.password})
      .subscribe(
        () => {
          this.progressbarService.done();
          this._accountService.getAccountData()
            .subscribe(() => {
              if (this._accountService.isUserPilot()) {
                return this._accountService.getAccountLicense().subscribe(() => {
                  this._router.navigate(['/']);
                });
              }
              this._router.navigate(['/']);
            });
        },
        (err) => {
          this.progressbarService.done();
          console.log(err);
          const body = err.json();
          if (err.status === 401) {
            if (body && body.error && body.error.code === 401) {
              this.toastrService.showError('Wrong e-mail or password');
            }
          }
          if (err.status === 400) {
            if (body && body.validationErrors) {
              body.validationErrors.forEach(item => {
                this.toastrService.showError(item.field);
              });
            }
          }
        }
      )
  }

  sendSignUpRequest(e, form) {
    e.preventDefault();
    this.submitted = true;

    if (form.invalid) {
      return;
    }

    this.formData.username = this.formData.username.toLowerCase();
    this.progressbarService.start();
    this._authorizationService.signUp(this.formData)
      .subscribe(
        () => {
          this.progressbarService.done();
          this._authorizationService.signUpFormActive = false;
          this.isSignUpForm = false;
          this.toastrService.showSuccess('You have been sign up successfully! To complete your registration please verify your email.', null, {toastLife: 5000});
        },
        (err) => {
          this.progressbarService.done();
          console.log(err);
          const body = err.json();
          if (err.status === 403) {
            if (body && body.error && body.error.code === 1001) {
              this.toastrService.showError('User already exists');
            }
          }
          if (err.status === 400) {
            if (body && body.validationErrors) {
              body.validationErrors.forEach(item => {
                this.toastrService.showError(item.field);
              });
            } else {
              this.toastrService.showError('Invalid form data');
            }
          }
        }
      );
  }

}
