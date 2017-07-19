import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {NgProgressService} from 'ngx-progressbar';

import {AccountService} from '../../../services/account.service/account.service';
import {CommonService} from '../../../services/common.service/common.service';
import {CountryModel} from '../../../services/common.service/country.interface';
import {extend} from '../../../shared/common/common-methods';
import {StateModel} from '../../../services/common.service/state.interface';
import {ToastrService} from '../../../services/toastr.service/toastr.service';

@Component({
  selector: 'f-company',
  templateUrl: './f-company.component.html',
  styleUrls: ['./f-company.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FClientCompanyComponent implements OnInit { // TODO: check this form when API will fixed
  public submitted: boolean = false;
  public countries: CountryModel[] = [];
  public states: StateModel[] = [];

  constructor(
    public accountService: AccountService,
    public commonService: CommonService,
    private toastrService: ToastrService,
    private progressbarService: NgProgressService
  ) {
  }

  ngOnInit() {
    if (!this.accountService.company) {
      this.accountService.getAccountCompany()
        .subscribe(
          () => {
            this.getCountries();
          }
        );
    } else {
      this.getCountries();
    }
  }

  private getCountries() {
    this.commonService.getCountries()
      .subscribe((countries) => this.setCountries(countries));
  }

  private setCountries(countries) {
    this.countries = extend([], countries);

    if (!this.accountService.company.country || !this.accountService.company.country.name) {
      this.clearCountry();
    }

    if (this.checkCountry()) {
      this.getListOfStates();
    }
  }

  saveChanges(e, form) {
    e.preventDefault();

    this.submitted = true;

    if (form.invalid) {
      return;
    }

    this.progressbarService.start();
    this.accountService.setAccountCompany(this.accountService.company)
      .subscribe(
        (res) => {
          this.progressbarService.done();
          console.log(res, '-save company');
          this.toastrService.showSuccess('Saved')
        },
        err => {
          this.progressbarService.done();
          console.log(err);
          if (err.status === 500) {
            this.toastrService.showError('Internal server error. Please try again later.');
          } else {
            if (err.status === 400) {
              const body = err.json();
              if (body && body.validationErrors) {
                body.validationErrors.forEach(item => {
                  this.toastrService.showError(item.field);
                });
              } else {
                this.toastrService.showError('Can\'t save changes. Please check your data');
              }
            } else {
              this.toastrService.showError('Can\'t save changes. Please check your data');
            }
          }
        }
      );
  }

  selectCountry(name: string) {
    // if pseudo placeholder option was selected
    if (!name || name === 'null') {
      this.clearCountry();
      return;
    }

    const country = this.countries.find((country) => country.name === name);

    this.setCountry(country);

    // TODO: disable until BE ready for state in model
    if (this.checkCountry()) {
      this.getListOfStates();
      this.clearState();
    } else {
      delete this.accountService.company.state;
    }
  }

  private setCountry({name, id}) {
    this.accountService.company.country.name = name;
    this.accountService.company.country.id = id;
  }

  private clearCountry() {
    this.accountService.company.country = {
      id: null,
      name: null
    };
    delete this.accountService.company.state;
  }

  private getListOfStates() {
    this.commonService.getStates()
      .subscribe((states) => this.setStates(states));
  }

  private setStates(states) {
    this.states = extend([], states);

    if (!this.accountService.company.state || !this.accountService.company.state.name) {
      this.clearState();
    }
  }

  private setState({name, id, code}) {
    this.accountService.company.state.name = name;
    this.accountService.company.state.id = id;
    this.accountService.company.state.code = code;
  }

  private clearState() {
    this.accountService.company.state = {
      id: null,
      name: null,
      code: null
    };
  }

  selectState(name: string) {
    // if pseudo placeholder option was selected
    if (!name || name === 'null') {
      this.clearState();
      return;
    }

    const state = this.states.find((state) => state.name === name);

    this.setState(state);
  }

  checkCountry() {
    return this.accountService.company && this.accountService.company.country && this.accountService.company.country.name === 'United States';
  }
}
