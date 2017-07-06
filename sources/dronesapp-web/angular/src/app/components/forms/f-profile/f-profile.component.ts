import {Component, ElementRef, NgZone, OnInit, ViewChild, ViewEncapsulation} from '@angular/core';
import {FileUploader} from 'ng2-file-upload';
import {NgProgressService} from 'ngx-progressbar';
import {} from '@types/googlemaps';
import {MapsAPILoader} from '@agm/core';

import {AccountService} from '../../../services/account.service/account.service';
import {RequestService} from '../../../services/request.service/request.service';
import {CommonService} from '../../../services/common.service/common.service';
import {CountryModel} from '../../../services/common.service/country.interface';
import {StateModel} from '../../../services/common.service/state.interface';
import {extend} from '../../../shared/common/common-methods';
import {ToastrService} from '../../../services/toastr.service/toastr.service';

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
  @ViewChild("location") public searchElement: ElementRef;

  constructor(
    public accountService: AccountService,
    private _requestService: RequestService,
    public commonService: CommonService,
    private toastrService: ToastrService,
    private progressbarService: NgProgressService,
    private mapsAPILoader: MapsAPILoader,
    private ngZone: NgZone
  ) {

    this.uploader.onSuccessItem = (item, response, status, headers) => {
      this.progressbarService.done();
      console.log('onSuccessItem', response);
      this.accountService.account['photoURL'] = JSON.parse(response)['url'];
      return {item, response, status, headers};
    };

    this.uploader.onErrorItem = (item, response, status, headers) => {
      this.progressbarService.done();
      console.log('problem with upload image');
      this.toastrService.showError('Couldn\'t upload image. Try one more time.');
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
    this.getListOfStates();
    this.loadPlaces();
  }

  handlePhotoUpload() {
    this.progressbarService.start();
    this.uploader.uploadAll();
  }

  saveChanges(e, form) {
    e.preventDefault();

    this.submitted = true;

    if (form.invalid ||
      (this.accountService.account && !this.accountService.account.photoURL) ||
      (!this.accountService.account.location.coordinates || !this.accountService.account.location.coordinates.latitude)) {
      return;
    }

    this.progressbarService.start();
    this.accountService.setAccountData(this.accountService.account)
      .subscribe(
        (res) => {
          this.progressbarService.done();
          console.log(res, '-save account');
          this.toastrService.showSuccess('Saved.');
          this.submitted = false;
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
  }

  private getCountries() {
    this.commonService.getCountries()
      .subscribe((countries) => this.setCountries(countries));
  }

  selectCountry(name: string, isAutocomplete = false) {
    // if pseudo placeholder option was selected
    if (!name || name === 'null') {
      this.clearCountry();
      return;
    }

    const country = this.countries.find((country) => country.name === name);

    this.setCountry(country);

    this.clearState();

    if (!isAutocomplete) {
      this.resetLocation();
      this.loadPlaces();
    }
  }

  selectState(name: string, isAutocomplete = false) {
    // if pseudo placeholder option was selected
    if (!name || name === 'null') {
      this.clearState();
      return;
    }

    const state = this.states.find((state) => state.name === name);

    this.setState(state);

    if (!isAutocomplete) {
      this.resetLocation();
    }
  }

  private setCountries(countries) {
    this.countries = extend([], countries);

    if (!this.accountService.account.location.country || !this.accountService.account.location.country.name) {
      this.clearCountry();
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
    this.clearState()
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
    return this.accountService.account.location.country && this.accountService.account.location.country.name === 'United States';
  }

  private loadPlaces() {
    this.mapsAPILoader.load().then(
      () => {
        this.ngZone.run(
          () => {
            let autocomplete = new google.maps.places.Autocomplete(this.searchElement.nativeElement);

            if (this.accountService.account.location.country && this.accountService.account.location.country.name) {
              const country = this.countries.find((country) => country.name.toLowerCase() === this.accountService.account.location.country.name.toLowerCase());
              if (country) {
                autocomplete.setComponentRestrictions({country: country.code.toLowerCase()});
              }

            }
            autocomplete.addListener('place_changed', () => {
              let place: google.maps.places.PlaceResult = autocomplete.getPlace();

              //verify result
              if (place.geometry === undefined || place.geometry === null) {
                return;
              }

              // console.log('place:', place);
              this.ngZone.run(() => this.setLocation(place));
            })
          }
        );
      }
    )
  }

  private setLocation(place) {
    this.accountService.account.location.address = '';
    let state = '';

    place.address_components.forEach((el, i) => {
      const addressType = place.address_components[i].types[0];

      if (addressType === 'locality') {
        this.accountService.account.location.city = el.long_name;
      } else if (addressType === 'postal_code') {
        this.accountService.account.location.postcode = el.long_name || null;
      } else if (addressType === 'route') {
        this.accountService.account.location.address = el.short_name + this.accountService.account.location.address;
      } else if (addressType === 'street_number') {
        this.accountService.account.location.address += (', ' + el.long_name);
      } else if (addressType === 'country') {
        const country = this.countries.find((country) => country.code.toLowerCase() === el.short_name.toLowerCase());

        if (!this.accountService.account.location.country ||
          !this.accountService.account.location.country.name ||
          this.accountService.account.location.country.name !== country.name) {
            this.selectCountry(country.name, true);
        }

        if (this.checkCountry()) {
          if (!this.accountService.account.location.state ||
            !this.accountService.account.location.state.name ||
            this.accountService.account.location.state.name !== state) {
            this.selectState(state, true);
          }
        } else {
          this.clearState();
        }
      } else if (addressType === 'administrative_area_level_1') {
        state = el.long_name;
      }
    });
    this.accountService.account.location.coordinates.latitude = place.geometry.location.lat();
    this.accountService.account.location.coordinates.longitude = place.geometry.location.lng();
  }

  private resetLocation() {
    this.accountService.account.location.address = '';
    this.accountService.account.location.coordinates = {
      latitude: 0,
      longitude: 0
    }
  }

  setRange(value) {
    this.accountService.account.location.range = value ? parseInt(value, 10): null;
  }

}
