import {Component, ViewEncapsulation} from '@angular/core';
import {AccountService} from '../../../services/account.service/account.service';

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

  constructor(private _accountService: AccountService) {
  }

  changePassword(e, form) {
    e.preventDefault();
    this.submitted = true;

    if (form.invalid) {
      return;
    }

    this._accountService.setAccountPassword({password: this.password, confirmPassword: this.repassword})
      .subscribe(() => {
        console.log('password is updated');
      });
  }
}
