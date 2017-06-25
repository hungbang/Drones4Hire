import {Component, ViewEncapsulation} from '@angular/core';
import {NgProgressService} from 'ngx-progressbar';

import {AccountService} from '../../../services/account.service/account.service';
import {ToastrService} from '../../../services/toastr.service/toastr.service';

@Component({
  selector: 'f-change-password',
  templateUrl: './f-change-password.component.html',
  styleUrls: ['./f-change-password.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FChangePasswordComponent {
  password: string = null;
  repassword: string = null;
  confirmPassword: string = null;
  submitted: boolean = false;

  constructor(
    private _accountService: AccountService,
    private toastrService: ToastrService,
    private progressbarService: NgProgressService
  ) {
  }

  changePassword(e, form) {
    e.preventDefault();
    this.submitted = true;

    if (form.invalid) {
      return;
    }

    this.progressbarService.start();
    this._accountService.setAccountPassword({password: this.password, confirmPassword: this.repassword})
      .subscribe(
        () => {
          this.progressbarService.done();
          console.log('password is updated');
          this.toastrService.showSuccess('Password have been changed')
        },
        err => {
          this.progressbarService.done();
          console.log(err);
          const body = err.json();

          if (err.status === 400) {
            if (body && body.validationErrors) {
              body.validationErrors.forEach(item => {
                this.toastrService.showError(item.field);
              });
            } else {
              this.toastrService.showError('Please check your data');
            }
          }
        }
      );
  }
}
