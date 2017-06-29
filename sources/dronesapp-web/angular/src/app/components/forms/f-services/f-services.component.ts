import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {NgProgressService} from 'ngx-progressbar';

import {AccountService} from '../../../services/account.service/account.service';
import {ToastrService} from '../../../services/toastr.service/toastr.service';

@Component({
  selector: 'f-services',
  templateUrl: './f-services.component.html',
  styleUrls: ['./f-services.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FServicesComponent implements OnInit {
  submitted: boolean = false;
  constructor(
    public accountService: AccountService,
    private toastrService: ToastrService,
    private progressbarService: NgProgressService
  ) {
  }

  ngOnInit() {
    if (this.accountService.activeServices.length) {
      return;
    }
    this.accountService.getAccountServices();
  }

  toggleService(data: {id: number, name: string; checked: boolean}) {
    data.checked = !data.checked;

    let pos = this.accountService.activeServices.indexOf(data.id);

    if (pos !== -1) {
      this.accountService.activeServices.splice(pos, 1);
    } else {
      this.accountService.activeServices.push(data.id);
    }
  }

  changeServices() {
    this.progressbarService.start();
    this.accountService.setAccountServices(this.accountService.activeServices)
      .subscribe(
        () => {
          this.progressbarService.done();
          console.log('services are updated');
          this.submitted = false;
          this.toastrService.showSuccess('Saved')
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

    this.submitted = true;
  }
}
