import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {NgProgressService} from 'ngx-progressbar';

import {AccountService} from '../../../services/account.service/account.service';
import {ToastrService} from '../../../services/toastr.service/toastr.service';

@Component({
  selector: 'f-forgot-password',
  templateUrl: './f-forgot-password.component.html',
  styleUrls: ['./f-forgot-password.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FForgotPasswordComponent implements OnInit {
  submitted: boolean = false;
  formSent: boolean = false;

  constructor(
    private accountService: AccountService,
    private progressbarService: NgProgressService,
    private toastrService: ToastrService
  ) { }

  ngOnInit() {
  }

  forgotPassword(e, form) {
    e.preventDefault();

    this.submitted = true;
    if (form.invalid) {
      return;
    }

    this.progressbarService.start();
    this.accountService.forgotPassword(form.value.email)
      .subscribe(
        () => {
          this.progressbarService.done();
          this.formSent = true;
          form.resetForm();
          this.submitted = false;
        },
        err => {
          this.progressbarService.done();
          console.log('forgot password error:', err);
          if (err.status === 500) {
            this.toastrService.showError('Internal server error. Please try again later.');
          } else {
            this.toastrService.showError('Please check your data.');
          }
        }
      );
  }
}
