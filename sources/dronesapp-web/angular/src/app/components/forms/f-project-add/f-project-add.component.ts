import {Component, ElementRef, Input, NgZone, OnInit, ViewChild, ViewEncapsulation} from '@angular/core';
import {FileUploader} from 'ng2-file-upload';
import {Router} from '@angular/router';
import * as moment from 'moment';
import {NgProgressService} from 'ngx-progressbar';
import {} from '@types/googlemaps';
import {MapsAPILoader} from '@agm/core'

import {CommonService} from '../../../services/common.service/common.service';
import {RequestService} from '../../../services/request.service/request.service';
import {CountryModel} from '../../../services/common.service/country.interface';
import {StateModel} from '../../../services/common.service/state.interface';
import {BudgetModel} from '../../../services/common.service/budget.interface';
import {NormalizedServiceModel} from '../../../services/common.service/services.interface';
import {mergeDeep, extend, deleteNullOrNaN} from '../../../shared/common/common-methods';
import {DurationModel} from '../../../services/common.service/duration.interface';
import {ProjectService} from '../../../services/project.service/project.service';
import {PaidOptionModel} from '../../../services/project.service/paid-option.interface';
import {ProjectModel} from '../../../services/project.service/project.interface';
import {CategoryModel} from '../../../services/common.service/category.interface';
import {ToastrService} from '../../../services/toastr.service/toastr.service';
import {ModalConfirmationComponent} from '../../modals/modal-confirmation/modal-confirmation.component';
import {ModalService} from '../../../services/modal.service/modal.service';
import {ModalPaymentComponent} from '../../modals/modal-payment/modal-payment.component';
import {PaymentService} from '../../../services/payment.service/payment.service';

@Component({
  selector: 'f-project-add',
  templateUrl: './f-project-add.component.html',
  styleUrls: ['./f-project-add.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FProjectAddComponent implements OnInit {
  @Input() project: ProjectModel = null;
  @ViewChild('location') public searchElement: ElementRef;
  private _now = moment();
  attachmentsLimit = 8;
  acceptedFormats = [
    'image/jpeg',
    'image/png',
    'application/vnd.openxmlformats-officedocument.wordprocessingml.document',
    'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
    'application/pdf',
    'application/msword',
    'application/vnd.ms-excel'
  ];
  isNotAcceptedFormat: boolean = false;
  isLimitReached: boolean = false;
  isSubmitted: boolean = false;
  paymentToken: string = '';
  autocomplete: any = null;

  date = {
    start: moment(),
    end: moment()
  };

  services: NormalizedServiceModel[] = [];

  categories: CategoryModel[] = [];

  countries: CountryModel[] = [];

  states: StateModel[] = [];

  budgets: BudgetModel[] = [];

  durations: DurationModel[] = [];

  paidOptions: PaidOptionModel[] = [];
  paidOptionsChecked: boolean[] = [];
  originalPaidOptions: PaidOptionModel[] = [];

  formData: ProjectModel;
  isEditForm: boolean = false;

  public uploader: FileUploader = new FileUploader({
    url: `${this._requestService.apiUrl}/upload`,
    authToken: this._requestService.getCurrentToken(),
    headers: [{
      name: 'FileType',
      value: 'PROJECT_ATTACHMENT'
    }]
  });

  public hasBaseDropZoneOver = false;

  constructor(
    public commonService: CommonService,
    private projectService: ProjectService,
    private _requestService: RequestService,
    private _elementRef: ElementRef,
    private router: Router,
    private toastrService: ToastrService,
    private _modalService: ModalService,
    private progressbarService: NgProgressService,
    private mapsAPILoader: MapsAPILoader,
    private ngZone: NgZone,
    private paymentService: PaymentService
  ) {

    this.uploader.onSuccessItem = (item, response, status, headers) => {
      console.log('onSuccessItem', response, item);
      const attachment = this.createAttachment(JSON.parse(response)['url'], item.file.name); // TODO: handle same names of files
      if (this.isEditForm) {
        this.projectService.addAttachment(attachment)
          .subscribe(
            () => {
              this.progressbarService.done();
              this.formData.attachments.push(attachment);
              this.toastrService.showSuccess('File saved');
            },
            err => {
              this.progressbarService.done();
              console.log('project attachment error', err);
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
                    this.toastrService.showError('Can\'t save file. Please try again');
                  }
                } else {
                  this.toastrService.showError('Can\'t save file. Please try again');
                }
              }
            }
          );
      } else {
        this.progressbarService.done();
        this.formData.attachments.push(attachment);
      }
      return {item, response, status, headers};
    };

    this.uploader.onErrorItem = (item, response, status, headers) => {
      this.progressbarService.done();
      console.log('problem with upload image');
      this.toastrService.showError('Couldn\'t upload image. Try one more time.');
      return {item, response, status, headers};
    };

    this.uploader.onAfterAddingFile = (item) => {
      console.log('onAfterAddingFile', item);

      this.isNotAcceptedFormat = false;
      if (this.formData.attachments.length < this.attachmentsLimit) {
        if (this.acceptedFormats.indexOf(item.file.type) !== -1) {
          this.progressbarService.start();
          this.uploader.uploadAll();
        } else {
          this.isNotAcceptedFormat = true;
          this.uploader.clearQueue();
        }
      } else {
        this.isLimitReached = true;
        this.uploader.clearQueue();
      }
      return {item};
    };

    this.uploader.onCancelItem = (item, response, status, headers) => {
      console.log('onWhenAddingFileFailed');
      this.uploader.clearQueue();
      return {item, response, status, headers};
    };

    this.formData = {
      attachments: [],
      confirmationValid: true,
      finishDate: parseInt(this.date.end.format('x'), 10),
      startDate: parseInt(this.date.start.format('x'), 10),
      status: 'NEW',
      postProductionRequired: false,
      paidOptions: [],
      budget: {
        confirmationValid: null,
        currency: '',
        id: null,
        max: null,
        min: null,
        order: null,
        title: null
      },
      duration: {
        id: null,
        max: null,
        min: null,
        order: null,
        title: null
      },
      service: {
        id: null,
        name: null,
        category: {
          order: null,
          id: null,
          name: null,
        }
      },
      title: '',
      summary: '',
      imageURL: '',
      location: {
        country: {
          id: null,
          name: null
        },
        state: {
          code: null,
          id: null,
          name: null
        },
        address: '',
        city: '',
        postcode: null,
        coordinates: {
          latitude: null,
          longitude: null
        }
      },
      paymentMethod: ''
    };
  }

  ngOnInit() {
    this.getData();

    if (this.project) {
      this.isEditForm = true;
      this.formData = mergeDeep(this.formData, this.project);
      this.originalPaidOptions = extend([], this.formData.paidOptions);
      this.initPaidOptions();
      this.initCategories();
      this.initDate();
    }
  }


  private getData() {
    this.getServices();
    this.getBudgets();
    this.getCountries();
    this.getListOfStates();
    this.getDurations();
    this.getPaidOptions();
    this.loadPlaces();
    this.getPaymentToken();
  }

  private getCountries() {
    this.commonService.getCountries()
      .subscribe(
        (countries) => {
          this.setCountries(countries);
          if (this.project) {
            this.setAutocompleteCountry();
          } else {
            this.selectCountry('United States');
          }
        }
      );
  }

  private setCountries(countries) {
    this.countries = extend([], countries);
  }

  get paidOptionsClasses() {
    return this.projectService.paidOptionsClasses;
  }

  private getPaidOptions() {
    this.projectService.getPaidOptions()
      .subscribe((paidOptions) => this.setPaidOptions(paidOptions));
  }

  private setPaidOptions(paidOptions) {
    this.paidOptions = extend([], paidOptions);
  }

  private getServices() {
    this.commonService.getServices()
      .subscribe((services) => this.setServices(services));
  }

  private setServices(services) {
    const copyServices = extend([], services);

    this.services = this.commonService.normalizeServices(copyServices);
  }

  private getBudgets() {
    this.commonService.getBudgets()
      .subscribe((budgets) => this.setBudgets(budgets));
  }

  private setBudgets(budgets) {
    this.budgets = extend([], budgets);
  }

  private getDurations() {
    this.commonService.getDurations()
      .subscribe((durations) => this.setDurations(durations));
  }

  private setDurations(durations) {
    this.durations = extend([], durations);
  }

  private getPaymentToken() {
    this.paymentService.getToken()
      .subscribe(
        res => {
          // console.log('payment token', res);
          this.paymentToken = res.clientId;
        },
        err => {
          console.log('payment token error', err);
        }
      );
  }

  selectService(name: string) {
    if (!name || name === 'null') {
      this.clearService();
      return;
    }

    const service = this.services.find((service) => service.name === name);

    this.setServiceToPostData(service);
    this.categories = service['category'];
  }

  private setServiceToPostData({id, name, order}) {
    this.formData.service.category['id'] = id;
    this.formData.service.category['name'] = name;
    this.formData.service.category['order'] = order;
  }

  clearService() {
    this.formData.service = {
      id: null,
      name: null,
      category: {
        order: null,
        id: null,
        name: null,
      }
    };
  }

  initCategories() {
    if (this.project.service.id) {
      const service = this.services.find((service) => service.name === this.project.service.category.name);

      this.categories = service['category'];
    }
  }

  selectBudget(title: string) {
    if (!title || title === 'null') {
      this.formData.budget = {
        confirmationValid: null,
        currency: '',
        id: null,
        max: null,
        min: null,
        order: null,
        title: null
      };
      return;
    }
    this.formData.budget = this.budgets.find((budget) => budget.title === title);
  }

  selectDuration(title: string) {
    if (!title || title === 'null') {
      this.formData.duration = {
        id: null,
        max: null,
        min: null,
        order: null,
        title: null
      };
      return;
    }
    this.formData.duration = this.durations.find((duration) => duration.title === title);
  }

  selectCategory(name: string) {
    if (!name || name === 'null') {
      this.clearService();
      return;
    }

    const category = this.categories.find((category) => category.name === name);

    this.formData.service['id'] = category.id;
    this.formData.service['name'] = category.name;
  }

  selectCountry(name: string, isAutocomplete = false) {
    if (!name || name === 'null') {
      this.clearCountry();
      return;
    }

    const country = this.countries.find((country) => country.name === name);

    this.formData.location.country.id = country.id;
    this.formData.location.country.name = country.name;

    if (this.checkCountry()) {
      this.clearState();
    } else {
      delete this.formData.location.state;
    }

    if (!isAutocomplete) {
      this.resetLocation();
      this.setAutocompleteCountry();
    }
  }

  selectPaidOption(checked, paidOption) {
    let index = -1;
    this.formData.paidOptions.some((option, i) => {
      if (option.id === paidOption.id) {
        index = i;
        return true;
      }
      return false;
    });

    if (checked) {
      index === -1 && this.formData.paidOptions.push(paidOption);
    } else {
      index >= 0 && this.formData.paidOptions.splice(index, 1);
    }

    this.initPaidOptions();
  }

  isRemovablePaidOption(paidOption) {
    if (!this.isEditForm) {
      return true;
    }
    let index = -1;

    this.originalPaidOptions.some((option, i) => {
      if (option.id === paidOption.id) {
        index = i;
        return true;
      }
      return false;
    });

    return index === -1;
  }

  checkRemovement(e, isRemovable) {
    if (this.isEditForm && !isRemovable) {
      e.preventDefault();
    }
  }

  initPaidOptions() {
    this.paidOptionsChecked = this.paidOptions.map((paidOption) => {
      return this.formData.paidOptions.some(option => option.id === paidOption.id);
    });
  }

  selectState(name: string, isAutocomplete = false) {
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

  checkCountry() {
    return this.formData.location.country.name === 'United States';
  }

  private _edit() {
    this.progressbarService.start();
    this.projectService.updateProject(this.formData)
      .subscribe(
        res => {
          // console.log('updated project:', res);
          this.toastrService.showSuccess('Changes saved');
          this.router.navigate(['/project', res.id]);
        },
        err => {
          this.progressbarService.done();

          if (err.status === 500) {
            this.toastrService.showError('Internal server error. Please try again later.');
          } else if (err.status === 403) {
            const body = err.json();

            if (body && body.error) {
              if (body.error.code === 2001) {
                this.toastrService.showError('Unable to process payment');
              }
            }
          } else if (err.status === 400) {
            const body = err.json();

            if (body && body.validationErrors) {
              body.validationErrors.forEach(item => {
                this.toastrService.showError(item.field);
              });
            }
          } else {
            this.toastrService.showError('Please check your data');
          }
        }
      );
  }

  private _post() {
    this.formData.budget.confirmationValid = true;
    this.formData.confirmationValid = true;
    this.formData.paidOptions.forEach((paidOption: PaidOptionModel) => {
      paidOption.confirmationValid = true;
    });

    this.progressbarService.start();
    return this.projectService.postProjects(this.formData)
      .subscribe(
        res => {
          // console.log('saved new project:', res);
          this.toastrService.showInfo('You will start receiving quotes shortly', '', {
            toastLife: 10000
          });
          this.toastrService.showSuccess('Project was posted successfully', '', {
            toastLife: 7000
          });
          this.router.navigate(['/project', res.id]); // TODO: we can redirect after show success notification
        },
        err => {
          this.progressbarService.done();
          console.log(err);

          if (err.status === 500) {
            this.toastrService.showError('Internal server error. Please try again later.');
          } else if (err.status === 403) {
            const body = err.json();

            if (body && body.error) {
              if (body.error.code === 2001) {
                this.toastrService.showError('Unable to process payment');
              }
            }
          } else if (err.status === 400) {
            const body = err.json();

            if (body && body.validationErrors) {
              body.validationErrors.forEach(item => {
                this.toastrService.showError(item.field);
              });
            }
          } else {
            this.toastrService.showError('Please check your data');
          }
        }
      )
  }

  getPayment() {
    this._modalService.push({
      component: ModalPaymentComponent,
      type: 'ModalInformationComponent',
      values: {
        title: 'Payment',
        payAmount: this.getPaymentAmount(),
        message: 'Please choose payment method:',
        clientToken: this.paymentToken,
        paymentFn: (e) => { this.setPayment(e); }
      }
    });
    return;
  }

  setPayment(nonce) {
    this.formData.paymentMethod = nonce;
    this._modalService.pop();

    if (!this.isEditForm) {
      this._post();
    } else {
      this._edit();
    }
  }

  postProject(e, form) {
    e.preventDefault();
    this.isSubmitted = true;

    if (!form.valid) {
      let message = '';
      if (Object.keys(form.controls).find(
          key => form.controls[key].errors && form.controls[key].errors.required
        )) {
        message = 'Please fill in all required fields.'
      }
      this.toastrService.showError(message !== '' ? message : 'Please check your form data.');
      return;
    }

    // console.log('project data to save:', this.formData);
    if (this.isEditForm) {
      if (this.originalPaidOptions.length !== this.formData.paidOptions.length) {
        this._modalService.push({
          component: ModalConfirmationComponent,
          type: 'ModalConfirmationComponent',
          values: {
            title: 'Post a project',
            message: 'Are you sure you want to make the payment?',
            confirm_btn_text: 'Yes',
            cancel_btn_text: 'No',
            confirm: (e) => {
              this._modalService.pop();
              if (!e) {
                return;
              }

              this.getPayment();
            }
          }
        });
      } else {
        this._edit();
      }

    } else {
      if (this.formData.paidOptions.length) {
        this._modalService.push({
          component: ModalConfirmationComponent,
          type: 'ModalConfirmationComponent',
          values: {
            title: 'Post a project',
            message: 'Are you sure you want to make the payment?',
            confirm_btn_text: 'Yes',
            cancel_btn_text: 'No',
            confirm: (e) => {
              this._modalService.pop();
              if (!e) {
                return;
              }

              this.getPayment();
            }
          }
        });
      } else {
        this._post();
      }
    }
  }

  private setStates(states) {
    this.states = extend([], states);
  }

  private setState({name, id, code}) {
    this.formData.location.state.name = name;
    this.formData.location.state.id = id;
    this.formData.location.state.code = code;
  }

  private clearCountry() {
    this.formData.location.country = {
      id: null,
      name: null
    };
    delete this.formData.location.state;
  }

  private clearState() {
    this.formData.location.state = {
      id: null,
      name: null,
      code: null
    };
  }

  private getListOfStates() {
    this.commonService.getStates()
      .subscribe((states) => this.setStates(states));
  }

  selectedDate(selected, date) {
    date.start = selected.start;
    date.end = selected.end;

    this.formData.finishDate = parseInt(date.end.format('x'), 10);
    this.formData.startDate = parseInt(date.start.format('x'), 10);
  }

  initDate() {
    this.date.start = moment(this.formData.startDate, 'x');
    this.date.end = moment(this.formData.finishDate, 'x');
  }


  get now() {
    return this._now;
  }

  public fileOverBase(e: any): void {
    this.hasBaseDropZoneOver = e;
  }

  triggerFileClick() {
    if (this._elementRef && this._elementRef.nativeElement !== null) {
      this._elementRef.nativeElement.querySelector('[type="file"]').click();
    }
  }

  private createAttachment(url: string, filename: string) {
    return {
      attachmentURL: url,
      projectId: this.isEditForm ? this.formData.id : null,
      title: filename,
      type: 'PROJECT_ATTACHMENT'
    }
  }

  deleteAttachment(id) {
    if (this.isEditForm) {
      this.progressbarService.start();
      this.projectService.deleteAttachment(id)
        .subscribe(
          () => {
            this.progressbarService.done();
            this.formData.attachments = this.formData.attachments.filter(attach => attach.id !== id);
          },
          err => {
            this.progressbarService.done();
            console.log('delete attached file error', err);
          }
        );
    } else {
      this.uploader.queue.splice(id, 1);
      this.formData.attachments.splice(id, 1);
    }
  }

  private getPaymentAmount() {
    let total = this.formData.paidOptions.reduce(
      (summ, option) => {
        return summ += option.price;
      }, 0);

    if (this.isEditForm) {
      total = this.originalPaidOptions.reduce(
        (summ, option) => {
          return summ -= option.price;
        }, total);
    }
    return total;
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
        this.formData.location.postcode = parseInt(el.long_name, 10) || null;
      } else if (addressType === 'route') {
        this.formData.location.address = el.short_name + this.formData.location.address;
      } else if (addressType === 'street_number') {
        this.formData.location.address += (', ' + el.long_name);
      } else if (addressType === 'country') {
        const country = this.countries.find((country) => country.code.toLowerCase() === el.short_name.toLowerCase());

        if (!this.formData.location.country ||
          !this.formData.location.country.name ||
          this.formData.location.country.name !== country.name) {
          this.selectCountry(country.name, true);
        }

        if (this.checkCountry()) {
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
    deleteNullOrNaN(this.formData.location, 'postcode');


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

  locationClicked(e) {
    this.getPlace(e.coords);
  }

  markerMoved(e) {
    this.getPlace(e.coords);
  }

  private getPlace(location) {
    const geocoder = new google.maps.Geocoder();

    geocoder.geocode({'location': location}, (res, status) => {
      if (status === google.maps.GeocoderStatus.OK && res.length) {
        this.ngZone.run(() => this.setLocation(res[0]));
      } else {
        this.toastrService.showError('Can\'t resolve address. Please try another point.')
      }
    })
  }
}
