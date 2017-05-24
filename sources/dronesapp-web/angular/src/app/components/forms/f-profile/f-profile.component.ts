import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {AccountService} from '../../../services/account.service/account.service';
import {AppService} from '../../../services/app.service/app.service';
import {FileUploader} from 'ng2-file-upload';
import {RequestService} from '../../../services/request.service/request.service';
import {AuthorizationService} from '../../../services/authorization.service/authorization.service';
import {CommonService} from '../../../services/common.service/common.service';
import {CountryModel} from '../../../services/common.service/country.interface';
import {StateModel} from '../../../services/common.service/state.interface';

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
              public appService: AppService,
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
    this.commonService.getListOfCountries()
      .subscribe(() => {
        this.countries = [...this.commonService.countries];

        this.commonService.accountCountry = this.countries.filter((country) => {
          if (this.accountService.account.location.country) {
            return country.id === this.accountService.account.location.country.id;
          }
          return false;
        })[0].name;

        if (this.commonService.checkCountry('accountCountry')) {
          this.getListOfStates();
        }
      })
  }

  handlePhotoUpload() {
    this.uploader.uploadAll();
  }

  saveChanges(e, form) {
    e.preventDefault();

    this.submitted = true;

    if (form.invalid) {
      return;
    }

    this.accountService.setUserData(this.accountService.account)
      .subscribe((res) => {
        console.log(res, '-save account');
      });
  }

  selectCountry(name: string) {
    let location = this.countries.filter((country) => {
      return name === country.name;
    })[0];

    if (!this.accountService.account.location.country) {
      this.setCountry();
    }

    this.accountService.account.location.country.name = location['name'];
    this.accountService.account.location.country.id = location['id'];

    if (!this.commonService.checkCountry('accountCountry')) {
      this.setState();
    } else {
      this.getListOfStates();
    }
  }

  selectState(name: string) {
    let location = this.states.filter((state) => {
      return name === state.name;
    })[0];

    if (!this.accountService.account.location.state) {
      this.setState();
    }

    this.accountService.account.location.state.name = location['name'];
    this.accountService.account.location.state.id = location['id'];
    this.accountService.account.location.state.code = location['code'];
  }

  private getListOfStates() {
    if (this.commonService.states.length) {
      return;
    }

    this.commonService.getListOfStates()
      .subscribe(() => {
        this.states = [...this.commonService.states];

         let filtered = this.states.filter((state) => {
          if (this.accountService.account.location.state) {
            return state.id === this.accountService.account.location.state.id;
          }
          return false;
        });

        filtered.length && (this.commonService.accountState = filtered[0].name);
      })
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
