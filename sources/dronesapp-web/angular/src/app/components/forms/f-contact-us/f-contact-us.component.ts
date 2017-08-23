import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {NgProgressService} from 'ngx-progressbar';

import {extend} from '../../../shared/common/common-methods';
import {ToastrService} from '../../../services/toastr.service/toastr.service';
import {CommonService} from '../../../services/common.service/common.service';
import {CountryModel} from '../../../services/common.service/country.interface';
import {PublicService} from '../../../services/public.service/public.service';

@Component({
  selector: 'f-contact-us',
  templateUrl: './f-contact-us.component.html',
  styleUrls: ['./f-contact-us.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FContactUsComponent implements OnInit {
  submitted: boolean = false;
  countries: CountryModel[] = []; // TODO: get reasons data
  country: string|null = null;
  reason: string|null = null;
  reasons: string[] = [
    'General Questions',
    'Client Question',
    'Drone Pilot Question'
  ];

  constructor(
    private toastrService: ToastrService,
    private progressbarService: NgProgressService,
    private commonService: CommonService,
    private publicService: PublicService
  ) { }

  ngOnInit() {
    this.getCountries();
  }

  private getCountries() {
    this.commonService.getCountries()
      .subscribe((countries) => this.setCountries(countries));
  }

  private setCountries(countries) {
    this.countries = extend([], countries);
  }

  sendMessage(e, form) {
    e.preventDefault();

    this.submitted = true;
    if (form.invalid) {
      return;
    }

    this.progressbarService.start();
    this.publicService.sendMessage(form.value)
      .subscribe(
        () => {
          form.resetForm();
          this.progressbarService.done();
          this.toastrService.showSuccess('Message was sent.');
          this.submitted = false;
        },
        err => {
          this.progressbarService.done();
          if (err.status === 500) {
            this.toastrService.showError('Internal server error. Please try again later.')
          } else {
            if (err.status === 400) {
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
        }
      );
  }
}
