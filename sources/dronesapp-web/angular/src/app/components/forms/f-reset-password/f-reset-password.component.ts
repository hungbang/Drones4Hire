import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {NgProgressService} from 'ngx-progressbar';

import {AccountService} from '../../../services/account.service/account.service';
import {ToastrService} from '../../../services/toastr.service/toastr.service';

@Component({
  selector: 'f-reset-password',
  templateUrl: './f-reset-password.component.html',
  styleUrls: ['./f-reset-password.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FResetPasswordComponent implements OnInit {
  submitted: boolean = false;
  token: string = '';

  constructor(
    private route: ActivatedRoute,
    private accountService: AccountService,
    private progressbarService: NgProgressService,
    private toastrService: ToastrService,
    private router: Router
  ) { }

  ngOnInit() {
    this.token = this.route.snapshot.data['token'];
  }

  resetPassword(e, form) {
    e.preventDefault();

    this.submitted = true;
    if (form.invalid) {
      return;
    }

    this.progressbarService.start();
    this.accountService.resetPassword(this.token, form.value)
      .subscribe(
        () => {
          this.toastrService.showSuccess('The password has been saved successfully. You may login now.');
          this.router.navigate(['/login']);
        },
        err => {
          this.progressbarService.done();
          console.log('reset password error:', err);
          if (err.status === 500) {
            this.toastrService.showError('Internal server error. Please try again later.');
          } else {
            this.toastrService.showError('Please check your data.');
          }
        }
      );
  }

}
