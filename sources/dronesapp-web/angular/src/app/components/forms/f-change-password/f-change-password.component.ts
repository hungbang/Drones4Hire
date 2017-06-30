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
  currentPassword: string = null;
  newPassword: string = null;
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
    this._accountService.setAccountPassword({currentPassword: this.currentPassword, newPassword: this.newPassword, confirmPassword: this.confirmPassword})
      .subscribe(
        () => {
          this.progressbarService.done();
          form.resetForm();
          this.submitted = false;
          this.toastrService.showSuccess('Password has been changed successfully.')
        },
        err => {
          this.progressbarService.done();
          console.log(err);

          if (err.status === 500) {
            this.toastrService.showError('Internal server error. Please try again later.');
          } else if (err.status === 400) {
            const body = err.json();

            if (body && body.validationErrors) {
              body.validationErrors.forEach(item => {
                this.toastrService.showError(item.field);
              });
            } else {
              this.toastrService.showError('Please check your data');
            }
          } else {
            this.toastrService.showError('Please check your data');
          }
        }
      );
  }
}
