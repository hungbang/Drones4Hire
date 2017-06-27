import {Component, ElementRef, NgZone, OnInit, ViewChild, ViewEncapsulation} from '@angular/core';
import {Router} from '@angular/router';
import {NgProgressService} from 'ngx-progressbar';
import {MapsAPILoader} from '@agm/core';
import {} from '@types/googlemaps';

import {extend} from '../../../shared/common/common-methods';
import {AuthorizationService} from '../../../services/authorization.service/authorization.service';
import {AccountService} from '../../../services/account.service/account.service';
import {ToastrService} from '../../../services/toastr.service/toastr.service';
import {CountryModel} from '../../../services/common.service/country.interface';
import {StateModel} from '../../../services/common.service/state.interface';
import {CommonService} from '../../../services/common.service/common.service';

@Component({
  selector: 'f-authorization',
  templateUrl: './f-authorization.component.html',
  styleUrls: ['./f-authorization.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FAuthorizationComponent implements OnInit {
  public formData = {
    firstName: '',
    lastName: '',
    email: '',
    password: '',
    confirmPassword: '',
    username: '',
    role: '',
    location: {
      address: '',
      city: '',
      coordinates: {
        latitude: null,
        longitude: null
      },
      country: {
        id: null,
        name: null
      },
      state: {
        name: null,
        id: null,
        code: null
      },
      postcode: null
    }
  };
  public isSignUpForm = true;
  public isTermOfUseChecked = false;

  public submitted = false;
  countries: CountryModel[] = [];
  states: StateModel[] = [];
  showConfirmNotification: boolean = false;
  showVerifyNotification: boolean = false;

  @ViewChild("location") public searchElement: ElementRef;

  constructor(
    public _authorizationService: AuthorizationService,
    private _accountService: AccountService,
    private _router: Router,
    private toastrService: ToastrService,
    private progressbarService: NgProgressService,
    private commonService: CommonService,
    private mapsAPILoader: MapsAPILoader,
    private ngZone: NgZone
  ) {
  }

  ngOnInit() {
    this.isSignUpForm = this._authorizationService.signUpFormActive = this._router.url === '/sign-up';
    this.getCountries();

    if (localStorage.getItem('firstLogin')) {
      this.showConfirmNotification = true;
      localStorage.removeItem('firstLogin');
    }

    console.log(this._authorizationService.signUpFormActive);
    console.log(this.isSignUpForm);
  }

  getButtonTitle() {
    return this.isSignUpForm ? 'Sign Up' : 'Login';
  }

  private getCountries() {
    this.commonService.getCountries()
      .subscribe((countries) => this.setCountries(countries));
  }

  private setCountries(countries) {
    this.countries = extend([], countries);

    this.clearCountry();
  }

  get checkCountry() {
    return this.formData.location.country && this.formData.location.country.name === 'United States';
  }

  private clearCountry() {
    this.formData.location.country = {
      id: null,
      name: null
    };
    this.clearState();
  }

  selectCountry(name: string) {
    // if pseudo placeholder option was selected
    if (!name || name === 'null') {
      this.clearCountry();
      return;
    }

    const country = this.countries.find((country) => country.name === name);

    this.setCountry(country);

    if (this.checkCountry) {
      this.getListOfStates();
    } else {
      setTimeout(() => {
        this.loadPlaces();
      }, 1)
    }
    this.formData.location.address = '';
    this.formData.location.coordinates.latitude = null;
    this.formData.location.coordinates.longitude = null;
    this.clearState();
  }

  private setCountry({name, id}) {
    this.formData.location.country.name = name;
    this.formData.location.country.id = id;
  }

  private getListOfStates() {
    this.commonService.getStates()
      .subscribe((states) => this.setStates(states));
  }

  private setStates(states) {
    this.states = extend([], states);
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

  private setState({name, id, code}) {
    this.formData.location.state.name = name;
    this.formData.location.state.id = id;
    this.formData.location.state.code = code;

    setTimeout(() => {
      this.loadPlaces();
    }, 1)
  }

  private clearState() {
    this.formData.location.state = {
      id: null,
      name: null,
      code: null
    };
  }

  private loadPlaces() {
    this.mapsAPILoader.load().then(
      () => {
        this.ngZone.run(
          () => {
            let autocomplete = new google.maps.places.Autocomplete(this.searchElement.nativeElement);

            autocomplete.addListener('place_changed', () => {
              let place: google.maps.places.PlaceResult = autocomplete.getPlace();

              //verify result
              if (place.geometry === undefined || place.geometry === null) {
                return;
              }

              console.log('place:', place);
              this.setLocation(place);
            })
          }
        );
      }
    )
  }

  private setLocation(place) {
    place.address_components.forEach((el, i) => {
      const addressType = place.address_components[i].types[0];

      if (addressType === 'locality') {
        this.formData.location.city = el.long_name;
      } else if (addressType === 'postal_code') {
        this.formData.location.postcode = el.long_name || null;
      } else if (addressType === 'route') {
        this.formData.location.address = el.short_name + this.formData.location.address;
      } else if (addressType === 'street_number') {
        this.formData.location.address += (' ' + el.long_name);
      }
    });
    this.formData.location.coordinates.latitude = place.geometry.location.lat();
    this.formData.location.coordinates.longitude = place.geometry.location.lng();

    console.log(this.formData.location);
  }

  get canAddLocation() {
    const isCountry = this.formData.location.country && this.formData.location.country.name;
    const isState = this.checkCountry ? this.formData.location.state && this.formData.location.state.name : true;

    return isCountry && isState;
  }

  sendAuthorizationRequest(e, form) {
    this._authorizationService.signUpFormActive ? this.sendSignUpRequest(e, form) : this.sendLoginRequest(e, form);
  }

  sendLoginRequest(e, form) {
    e.preventDefault();
    this.submitted = true;

    if (form.invalid) {
      return;
    }

    this.progressbarService.start();
    this._authorizationService.signIn({email: this.formData.email, password: this.formData.password})
      .subscribe(
        () => {
          this.progressbarService.done();
          this._accountService.getAccountData()
            .subscribe(() => {
              if (this._accountService.isUserPilot()) {
                return this._accountService.getAccountLicense().subscribe(() => {
                  this._router.navigate(['/']);
                });
              }
              this._router.navigate(['/']);
            });
        },
        (err) => {
          this.progressbarService.done();
          console.log(err);
          const body = err.json();
          if (err.status === 401) {
            if (body && body.error && body.error.code === 401) {
              this.toastrService.showError('Wrong e-mail or password');
            }
          }
          if (err.status === 400) {
            if (body && body.validationErrors) {
              body.validationErrors.forEach(item => {
                this.toastrService.showError(item.field);
              });
            }
          }
        }
      )
  }

  sendSignUpRequest(e, form) {
    e.preventDefault();
    this.submitted = true;

    if (form.invalid || (this.formData.role === 'ROLE_PILOT' && !this.formData.location.coordinates.latitude)) {
      return;
    }
    // reset location for client
    if (this.formData.role === 'ROLE_CLIENT') {
      this.formData.location = null;
    }

    this.formData.username = this.formData.username.toLowerCase();
    this.progressbarService.start();
    this._authorizationService.signUp(this.formData)
      .subscribe(
        () => {
          this.progressbarService.done();
          localStorage.setItem('firstLogin', 'true');
          this._router.navigate(['/login']);
        },
        (err) => {
          this.progressbarService.done();
          console.log(err);
          const body = err.json();
          if (err.status === 403) {
            if (body && body.error && body.error.code === 1001) {
              this.toastrService.showError('User already exists');
            }
          }
          if (err.status === 400) {
            if (body && body.validationErrors) {
              body.validationErrors.forEach(item => {
                this.toastrService.showError(item.field);
              });
            } else {
              this.toastrService.showError('Invalid form data');
            }
          }
        }
      );
  }

}
