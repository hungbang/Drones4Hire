import {
  Component, ElementRef, NgZone, OnInit, ViewChild,
  ViewEncapsulation
} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
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
  showVerifyNotification: boolean = false;
  showVerifySuccessNotification: boolean = false;
  isVerified: boolean = true;

  @ViewChild('location') public searchElement: ElementRef;
  autocomplete: any = null;

  constructor(
    public _authorizationService: AuthorizationService,
    private _accountService: AccountService,
    private _router: Router,
    private route: ActivatedRoute,
    private toastrService: ToastrService,
    private progressbarService: NgProgressService,
    private commonService: CommonService,
    private mapsAPILoader: MapsAPILoader,
    private ngZone: NgZone
  ) {
  }

  ngOnInit() {
    this.isSignUpForm = this._authorizationService.signUpFormActive = this._router.url.indexOf('/sign-up', 0) === 0;
    if (!this.isSignUpForm) {
      this.verifyEmail();

      if (this._authorizationService.isUserLogin) { // logout user if was redirected because expired tokens
        this._authorizationService.logout();
      }
    } else {
      this.checkUserImport();
    }

    this.getCountries();
    this.getListOfStates();
  }

  getButtonTitle() {
    return this.isSignUpForm ? 'Sign Up' : 'Login';
  }

  private getCountries() {
    this.commonService.getCountries()
      .subscribe(
        (countries) => {
          this.setCountries(countries);
          this.setAutocompleteCountry();
        }
      );
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
      this.setAutocompleteCountry();
    }
  }

  private setCountry({name, id}) {
    this.formData.location.country.name = name;
    this.formData.location.country.id = id;
  }

  private setAutocompleteCountry() {
    if (this.formData.location.country && this.formData.location.country.name) {

      const country = this.countries.find((country) => country.name.toLowerCase() === this.formData.location.country.name.toLowerCase());
      if (country && this.autocomplete) {
        this.autocomplete.setComponentRestrictions({country: country.code.toLowerCase()});
      }

    } else if (this.autocomplete){
      this.autocomplete.setComponentRestrictions({country: []});
    }
  }

  private getListOfStates() {
    this.commonService.getStates()
      .subscribe((states) => this.setStates(states));
  }

  private setStates(states) {
    this.states = extend([], states);
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

  private setState({name, id, code}) {
    this.formData.location.state.name = name;
    this.formData.location.state.id = id;
    this.formData.location.state.code = code;
  }

  private clearState() {
    this.formData.location.state = {
      id: null,
      name: null,
      code: null
    };
  }

  initAutocomplete() {
    setTimeout(
      () => {
        this.loadPlaces();
      },
      1
    )
  }

  private loadPlaces() {
    this.mapsAPILoader.load().then(
      () => {
        this.ngZone.run(
          () => {
            this.autocomplete = new google.maps.places.Autocomplete(this.searchElement.nativeElement,  {types: ['address']});

            if (this.countries.length) {
              this.setAutocompleteCountry();
            }

            this.autocomplete.addListener('place_changed', () => {
              const place: google.maps.places.PlaceResult = this.autocomplete.getPlace();

              // verify result
              if (place.geometry === undefined || place.geometry === null) {
                return;
              }

              console.log('place:', place);
              this.ngZone.run(() => this.setLocation(place));
            })
          }
        );
      }
    )
  }

  private setLocation(place) {
    this.formData.location.address = '';
    let state = '';

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
      } else if (addressType === 'country') {
        const country = this.countries.find((country) => country.code.toLowerCase() === el.short_name.toLowerCase());

        if (!this.formData.location.country ||
          !this.formData.location.country.name ||
          this.formData.location.country.name !== country.name) {
          this.selectCountry(country.name, true);
        }

        if (this.checkCountry) {
          if (!this.formData.location.state ||
            !this.formData.location.state.name ||
            this.formData.location.state.name !== state) {
            this.selectState(state, true);
          }
        } else {
          delete this.formData.location.state;
        }
      } else if (addressType === 'administrative_area_level_1') {
        state = el.long_name;
      }
    });
    if (!this.formData.location.address) {
      this.formData.location.address = place.formatted_address;
    }

    this.formData.location.coordinates.latitude = place.geometry.location.lat();
    this.formData.location.coordinates.longitude = place.geometry.location.lng();
  }

  private resetLocation() {
    this.formData.location.address = '';
    this.formData.location.coordinates = {
      latitude: 0,
      longitude: 0
    }
  }

  sendAuthorizationRequest(e, form) {
    this._authorizationService.signUpFormActive ? this.sendSignUpRequest(e, form) : this.sendLoginRequest(e, form);
  }

  sendLoginRequest(e, form) { // TODO: add redirect if user was redirected from another page cause was not logged in
    e.preventDefault();
    this.submitted = true;

    if (form.invalid || !this.isVerified) {
      return;
    }

    this.progressbarService.start();
    this._authorizationService.signIn({email: this.formData.email, password: this.formData.password})
      .subscribe(
        () => {
          this.progressbarService.done();
          this._accountService.getAccountData()
            .subscribe(
              () => {
                if (this._accountService.isUserPilot()) {
                  this._accountService.getAccountLicense().subscribe(
                    res => {
                      if (!res.verified) {
                        this._router.navigate(['/account', 'pilot', 'details']);
                      } else {
                        this._router.navigate(['/dashboard', 'pilot']);
                      }
                    },
                    err => {
                      console.log('get license error', err);
                      this._router.navigate(['/dashboard', 'pilot']);
                    }
                  );
                } else {
                  this._router.navigate(['/dashboard', 'client']);
                }
              }
            );
        },
        (err) => {
          this.progressbarService.done();
          console.log(err);
          if (err.status === 500) {
            this.toastrService.showError('Internal server error. Please try again later.');
          } else if (err.status === 401) {
            const body = err.json();
            if (body && body.error && body.error.code === 401) {
              this.toastrService.showError('Wrong e-mail or password');
            } else {
              this.toastrService.showError('Please check your data');
            }
          } else if (err.status === 403) {
            const body = err.json();
            if (body && body.error) {
              if (body.error.code === 1003 || body.error.code === 1006) {
                this.showVerifyNotification = true;
                this.isVerified = false;
                this.toastrService.showError('Unverified account.');
              } else {
                this.toastrService.showError('Please check your data');
              }
            } else {
              this.toastrService.showError('Please check your data');
            }
          } else {
            this.toastrService.showError('Please check your data');
          }
        }
      )
  }

  sendSignUpRequest(e, form) {
    e.preventDefault();
    this.submitted = true;

    if (form.invalid || (this.formData.role === 'ROLE_PILOT' && !this.formData.location.coordinates.latitude)) {
      if (Object.keys(form.controls).find(
          key => form.controls[key].errors && form.controls[key].errors.required
        )) {
        this.toastrService.showError('Please fill in all required fields.');
      }
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
          this._router.navigate(['/sign-up-success']);
        },
        (err) => {
          this.progressbarService.done();
          console.log(err);
          if (err.status === 500) {
            this.toastrService.showError('Internal server error. Please try again later.');
          } else if (err.status === 403) {
            const body = err.json();
            if (body && body.error && body.error.code === 1001) {
              this.toastrService.showError('This Username or Email is already taken');
            } else {
              this.toastrService.showError('Please check your data');
            }
          } else {
            this.toastrService.showError('Please check your data');
          }
        }
      );
  }

  verifyEmail() {
    const queryParams = this.route.snapshot.queryParams;

    if (queryParams && queryParams.token) {
      this._authorizationService.verifyEmail(queryParams.token)
        .subscribe(
          () => {
            this.showVerifySuccessNotification = true;
            this.isVerified = true;
          },
          err => {
            console.log('verify email error:', err);
          }
        );
    }
  }

  checkUserImport() {
    const queryParams = this.route.snapshot.queryParams;

    if (queryParams && queryParams.token) {
      this._authorizationService.getImportedUserData(queryParams.token)
        .subscribe(
          res => {
            this.formData.firstName = res.fname;
            this.formData.lastName = res.lname;
            this.formData.email = res.email;
            this.formData.username = res.user_name;
            if (res.role) {
              this.formData.role = res.role;
              if (this.formData.role === 'ROLE_PILOT') {
                this.initAutocomplete();
              }
            }
          }
        );
    }
  }

  onRoleSelect(e, role) {
    e.preventDefault();
    this.formData.role = role;
    if (role === 'ROLE_PILOT' && !this.autocomplete) {
      this.initAutocomplete();
    }
  }
}
