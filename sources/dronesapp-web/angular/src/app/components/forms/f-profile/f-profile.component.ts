import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {FileUploader} from 'ng2-file-upload';

import {AccountService} from '../../../services/account.service/account.service';
import {RequestService} from '../../../services/request.service/request.service';
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
              public commonService: CommonService) {

    this.uploader.onSuccessItem = (item, response, status, headers) => {
      console.log('onSuccessItem', response);
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
    this.getCountries();
  }

  handlePhotoUpload() {
    this.uploader.uploadAll();
  }

  saveChanges(e, form) {
    e.preventDefault();

    this.submitted = true;

    if (form.invalid || (!this.accountService.account && !this.accountService.account.photoURL)) { //TODO: if photo is required check this for correct work
      return;
    }

    this.accountService.setAccountData(this.accountService.account)
      .subscribe((res) => {
        console.log(res, '-save account');
      });
  }

  private getCountries() {
    this.commonService.getCountries()
      .subscribe((countries) => this.setCountries(countries));
  }

  selectCountry(name: string) {
    // if pseudo placeholder option was selected
    if (!name || name === 'null') {
      this.clearCountry();
      return;
    }

    const country = this.countries.find((country) => country.name === name);

    this.setCountry(country);

    if (this.checkCountry()) {
      this.getListOfStates();
      this.clearState();
    } else {
      delete this.accountService.account.location.state;
    }
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

  private setCountries(countries) {
    this.countries = extend([], countries);

    if (!this.accountService.account.location.country || !this.accountService.account.location.country.name) {
      this.clearCountry();
    }

    if (this.checkCountry()) {
      this.getListOfStates();
    }
  }

  private getListOfStates() {
    this.commonService.getStates()
      .subscribe((states) => this.setStates(states));
  }

  private setStates(states) {
    this.states = extend([], states);

    if (!this.accountService.account.location.state || !this.accountService.account.location.state.name) {
      this.clearState();
    }
  }

  private setCountry({name, id}) {
    this.accountService.account.location.country.name = name;
    this.accountService.account.location.country.id = id;
  }

  private clearCountry() {
    this.accountService.account.location.country = {
      id: null,
      name: null
    };
    delete this.accountService.account.location.state;
  }

  private setState({name, id, code}) {
    this.accountService.account.location.state.name = name;
    this.accountService.account.location.state.id = id;
    this.accountService.account.location.state.code = code;
  }

  private clearState() {
    this.accountService.account.location.state = {
      id: null,
      name: null,
      code: null
    };
  }

  checkCountry() {
    return this.accountService.account.location.country.name === 'United States';
  }
}
