import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import {AccountService} from '../../../services/account.service/account.service';
import {CommonService} from '../../../services/common.service/common.service';
import {CountryModel} from '../../../services/common.service/country.interface';
import {StateModel} from '../../../services/common.service/state.interface';

@Component({
  selector: 'f-company',
  templateUrl: './f-company.component.html',
  styleUrls: ['./f-company.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FClientCompanyComponent implements OnInit {
  public submitted: boolean = false;
  public countries: CountryModel[] = [];

  constructor(public accountService: AccountService,
              public commonService: CommonService) {
  }

  ngOnInit() {
    this.accountService.getUserCompany()
      .then(() => {
        if (this.commonService.countries.length) {
          this.countries = [...this.commonService.countries];
          this.selectCompanyCountry();
        } else {
          this.commonService.getListOfCountries()
            .then(() => {
              this.countries = [...this.commonService.countries];
              this.selectCompanyCountry();
            })
        }
      });
  }

  selectCompanyCountry() {
    this.commonService.companyCountry = this.countries.filter((country) => {
      if (this.accountService.company.country) {
        return country.id === this.accountService.company.country.id;
      }
      return false;
    })[0].name;
  }

  saveChanges(e, form) {
    e.preventDefault();

    this.submitted = true;

    if (form.invalid) {
      return;
    }

    this.accountService.setUserCompany(this.accountService.company)
      .then((res) => {
        console.log(res.json(), '-save company');
      });
  }

  selectCountry(name: string) {
    let location = this.countries.filter((country) => {
      return name === country.name;
    })[0];

    if (!this.accountService.company.country) {
      this.setCountry();
    }

    this.accountService.company.country.name = location['name'];
    this.accountService.company.country.id = location['id'];
  }

  private setCountry() {
    this.accountService.company.country = {
      id: null,
      name: null
    };
  }
}
