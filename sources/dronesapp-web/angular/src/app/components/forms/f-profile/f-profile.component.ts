import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {AccountService} from '../../../services/account.service/account.service';
import {FileUploader} from 'ng2-file-upload';
import {RequestService} from '../../../services/request.service/request.service';
import {AuthorizationService} from '../../../services/authorization.service/authorization.service';
import {CommonService} from '../../../services/common.service/common.service';
import {CountryModel} from '../../../services/common.service/country.interface';
import {StateModel} from '../../../services/common.service/state.interface';
import {extend} from '../../../shared/common/common-methods';

@Component({
  selector: 'f-profile',
  templateUrl: './f-profile.component.html',
  styleUrls: ['./f-profile.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FClientProfileComponent implements OnInit {
  public uploader: FileUploader = new FileUploader({
    url: `${this._requestService.apiUrl}/upload`,
    authToken: this._requestService.getCurrentToken(),
    headers: [{
      name: 'FileType',
      value: 'USER_PHOTO'
    }]
  });

  submitted: boolean = false;
  countries: CountryModel[] = [];
  states: StateModel[] = [];

  constructor(public accountService: AccountService,
              private _requestService: RequestService,
              public authorizationService: AuthorizationService,
              public commonService: CommonService) {

    this.uploader.onSuccessItem = (item, response, status, headers) => {
      console.log('onSuccessItem', response);
      console.log(JSON.parse(response));
      this.accountService.account['photoURL'] = JSON.parse(response)['url'];
      this.uploader.clearQueue();
      return {item, response, status, headers};
    };

    this.uploader.onErrorItem = (item, response, status, headers) => {
      console.log('problem with upload image');
      this.uploader.clearQueue();
      return {item, response, status, headers};
    };

    this.uploader.onAfterAddingFile = (item) => {
      console.log('onAfterAddingFile');
      this.uploader.queue[0].upload();
      return {item};
    };

    this.uploader.onCancelItem = (item, response, status, headers) => {
      console.log('onWhenAddingFileFailed');
      this.uploader.clearQueue();
      return {item, response, status, headers};
    };
  }

  ngOnInit() {
    this.commonService.getCountries()
      .subscribe((countries) => this.setCountries(countries));
  }

  handlePhotoUpload() {
    this.uploader.uploadAll();
  }

  saveChanges(e, form) {
    e.preventDefault();

    this.submitted = true;

    if (form.invalid || (!this.accountService.account && !this.accountService.account.photoURL)) {
      return;
    }

    this.accountService.setAccountData(this.accountService.account)
      .subscribe((res) => {
        console.log(res, '-save account');
      });
  }

  selectCountry(name: string) {
    this.countries.filter((country) => {
      if (name === country.name) {
        this.accountService.account.location.country.name = country['name'];
        this.accountService.account.location.country.id = country['id'];
      }
    });

    if (!this.accountService.account.location.country) {
      this.setCountry();
    }

    if (!this.commonService.checkCountry('accountCountry')) {
      this.setState();
    } else {
      this.getListOfStates();
    }
  }

  selectState(name: string) {
    this.states.filter((state) => {
      if (name === state.name) {
        this.accountService.account.location.state.name = state['name'];
        this.accountService.account.location.state.id = state['id'];
        this.accountService.account.location.state.code = state['code'];
      }
    });

    if (!this.accountService.account.location.state) {
      this.setState();
    }
  }

  private setCountries(countries) {
    this.countries = extend([], countries);

    this.countries.filter((country) => {
      if (this.accountService.account.location.country
        && (country.id === this.accountService.account.location.country.id)) {
        this.commonService.accountCountry = country.name;
      }
    });

    if (this.commonService.checkCountry('accountCountry')) {
      this.getListOfStates();
    }
  }

  private setStates(states) {
    this.states = extend([], states);

    this.states.filter((state) => {
      if (this.accountService.account.location.state
        && (state.id === this.accountService.account.location.state.id)) {
        this.commonService.accountState = state.name;
      }
    });
  }

  private getListOfStates() {
    this.commonService.getStates()
      .subscribe((states) => this.setStates(states));
  }

  private setCountry() {
    this.accountService.account.location.country = {
      id: null,
      name: null
    };
  }

  private setState() {
    this.accountService.account.location.state = {
      id: null,
      name: null,
      code: null
    };
  }
}
