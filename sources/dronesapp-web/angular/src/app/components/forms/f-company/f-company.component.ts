import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {AccountService} from '../../../services/account.service/account.service';
import {CommonService} from '../../../services/common.service/common.service';
import {CountryModel} from '../../../services/common.service/country.interface';
import {extend} from '../../../shared/common/common-methods';
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
  public states: StateModel[] = [];

  constructor(public accountService: AccountService,
              public commonService: CommonService) {
  }

  ngOnInit() {
    if (!this.accountService.company) {
      this.accountService.getAccountCompany()
        .subscribe(() => {
          this.commonService.getCountries()
            .subscribe((countries) => this.selectCompanyCountry(countries));
        });
    } else {
      this.selectCompanyCountry(this.commonService.countries);
    }
  }

  selectCompanyCountry(countries) {
    this.countries = extend([], countries);

    // let filtered = this.countries.filter((country) => {
    //   if (this.accountService.company.country) {
    //     return country.id === this.accountService.company.country.id;
    //   }
    //   return false;
    // });

    this.countries.filter((country) => {
      if (name === country.name) {
        this.accountService.company.country.name = country['name'];
        this.accountService.company.country.id = country['id'];
      }
    });

    // filtered.length && (this.commonService.companyCountry = filtered[0].name);

    if (!this.accountService.company.country) {
      this.setCountry();
    }

    // if (!this.commonService.checkCountry('companyCountry')) {
    //   this.setState();
    // } else {
    //   this.getListOfStates();
    // }
  }

  saveChanges(e, form) {
    e.preventDefault();

    this.submitted = true;

    if (form.invalid) {
      return;
    }

    console.log(this.accountService.company);
    this.accountService.setAccountCompany(this.accountService.company)
      .subscribe((res) => {
        console.log(res, '-save company');
      });
  }

  selectCountry(name: string) {
    this.countries.filter((country) => {
      if (name === country.name) {
        this.accountService.company.country.name = country['name'];
        this.accountService.company.country.id = country['id'];
      }
    });

    if (!this.accountService.company.country) {
      this.setCountry();
    }

    // TODO: disable until BE ready for state in model
    // if (!this.commonService.checkCountry('companyCountry')) {
    //   this.setState();
    // } else {
    //   this.getListOfStates();
    // }
  }

  private setCountry() {
    this.accountService.company.country = {
      id: null,
      name: null
    };
  }

  private setStates(states) {
    this.states = extend([], states);

    console.log(this.states);

    this.states.filter((state) => {
      if (this.accountService.company.state
        && (state.id === this.accountService.company.state.id)) {
        this.accountService.company.state.name = state.name;
      }
    });
  }

  private getListOfStates() {
    this.commonService.getStates()
      .subscribe((states) => this.setStates(states));
  }

  private setState() {
    this.accountService.company.state = {
      id: null,
      name: null,
      code: null
    };
  }

  selectState(name: string) {
    this.states.filter((state) => {
      if (name === state.name) {
        this.accountService.company.state.name = state['name'];
        this.accountService.company.state.id = state['id'];
        this.accountService.company.state.code = state['code'];
      }
    });

    if (!this.accountService.company.state) {
      this.setState();
    }
  }
}
