import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {AccountService} from '../../../services/account.service/account.service';

@Component({
  selector: 'f-email',
  templateUrl: './f-email.component.html',
  styleUrls: ['./f-email.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FEmailComponent {
  email: string = null;
  password: string = null;
  submitted: boolean = false;

  constructor(private _accountService: AccountService) {
  }

  changeEmail(e, form) {
    e.preventDefault();
    this.submitted = true;

    if (form.invalid) {
      return;
    }

    this._accountService.setEmailAdress({email: this.email, password: this.password})
      .then(() => {
        console.log('email is updated');
      });
  }
}
