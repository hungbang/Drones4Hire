import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {CommonService} from '../../../services/common.service/common.service';
import {FileUploader} from 'ng2-file-upload';
import {RequestService} from '../../../services/request.service/request.service';
import {CountryModel} from '../../../services/common.service/country.interface';
import {StateModel} from '../../../services/common.service/state.interface';
import {BudgetModel} from '../../../services/common.service/budget.interface';
import {NormalizedServiceModel} from '../../../services/common.service/services.interface';
import {extend} from '../../../shared/common/common-methods';
import {DurationModel} from '../../../services/common.service/duration.interface';
import {ProjectService} from '../../../services/project.service/project.service';
import {PaidOptionModel} from '../../../services/project.service/paid-option.interface';
import * as moment from 'moment';
import {ProjectModel} from '../../../services/project.service/project.interface';
import {CategoryModel} from '../../../services/common.service/category.interface';

@Component({
  selector: 'f-project-add',
  templateUrl: './f-project-add.component.html',
  styleUrls: ['./f-project-add.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FProjectAddComponent implements OnInit {
  private _now = moment();

  date = {
    start: moment(),
    end: moment()
  };

  services: NormalizedServiceModel[] = [];
  service: string = null;

  categories: CategoryModel[] = [];
  category: string = null;

  countries: CountryModel[] = [];
  country: string = null;

  states: StateModel[] = [];
  state: string = null;

  budgets: BudgetModel[] = [];
  budget: string;

  durations: DurationModel[] = [];
  duration: string;

  paidOptions: PaidOptionModel[] = [];
  paidOptionsChecked: boolean[] = [];

  productionRequired: boolean = false;

  formData: ProjectModel;

  public uploader: FileUploader = new FileUploader({
    url: `${this._requestService.apiUrl}/upload`,
    authToken: this._requestService.getCurrentToken(),
    headers: [{
      name: 'FileType',
      value: 'PROJECT_IMAGE_URL'
    }]
  });

  public hasBaseDropZoneOver = false;

  constructor(public commonService: CommonService,
              private projectService: ProjectService,
              private _requestService: RequestService) {

    this.uploader.onSuccessItem = (item, response, status, headers) => {
      console.log('onSuccessItem', response);
      this.formData.imageURL = JSON.parse(response)['url']; // TODO: array of docs when API will provided
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

    this.formData = {
      confirmationValid: true,
      finishDate: parseInt(this.date.end.format('x'), 10),
      startDate: parseInt(this.date.start.format('x'), 10),
      status: 'NEW',
      postProductionRequired: this.productionRequired,
      paidOptions: [],
      budget: {
        confirmationValid: null,
        currency: '',
        id: null,
        max: null,
        min: null,
        order: null,
        title: ''
      },
      duration: {
        id: null,
        max: null,
        min: null,
        order: null,
        title: ''
      },
      service: {
        id: null,
        name: '',
        category: {
          order: null,
          id: null,
          name: '',
        }
      },
      title: '',
      summary: '',
      imageURL: '',
      location: {
        country: {
          id: null,
          name: ''
        },
        address: '',
        city: '',
        postcode: null,
      }
    };
  }

  ngOnInit() {
    this.getData();
  }

  private getData() {
    this.getServices();
    this.getBudgets();
    this.getCountries();
    this.getDurations();
    this.getPaidOptions();
  }

  private getCountries() {
    this.commonService.getCountries()
      .subscribe((countries) => this.setCountries(countries));
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

  selectService(name: string) {
    const service = this.services.find((service) => service.name === name);

    this.setServiceToPostData(service);
    this.categories = service['category'];
    this.category = null;
  }

  private setServiceToPostData({id, name, order}) {
    this.formData.service.category['id'] = id;
    this.formData.service.category['name'] = name;
    this.formData.service.category['order'] = order;
  }

  selectBudget(title: string) {
    this.formData.budget = this.budgets.find((budget) => budget.title === title);
  }

  selectDuration(title: string) {
    this.formData.duration = this.durations.find((duration) => duration.title === title);
  }

  selectCategory(name: string) {
    const category = this.categories.find((category) => category.name === name);

    this.formData.service['id'] = category.id;
    this.formData.service['name'] = category.name;
  }

  selectCountry(name: string) {
    const country = this.countries.find((country) => country.name === name);

    this.formData.location.country.id = country.id;
    this.formData.location.country.name = country.name;

    if (this.checkCountry()) {
      this.getListOfStates();
      this.clearState();
    } else {
      this.state = '';
      delete this.formData.location.state;
    }
  }

  selectPaidOption(checked, paidOption) {
    const index = this.formData.paidOptions.indexOf(paidOption);

    if (checked) {
      index === -1 && this.formData.paidOptions.push(paidOption);
    } else {
      index >= 0 && this.formData.paidOptions.splice(index, 1);
    }
  }

  selectState(name: string) {
    const state = this.states.find((state) => state.name === name);

    this.setState(state);
  }

  setProductionRequired(productionRequired) {
    this.productionRequired = !productionRequired;
    this.formData.postProductionRequired = this.productionRequired;
  }

  checkCountry() {
    return this.country === 'United States';
  }

  postProject(e, form) {
    e.preventDefault();

    this.formData.budget.confirmationValid = true;
    this.formData.confirmationValid = true;
    this.formData.paidOptions.forEach((paidOption: PaidOptionModel) => {
      paidOption.confirmationValid = true;
    });

    return this.projectService.postProjects(this.formData)
      .subscribe((data) => {
        console.log(data);
      })
  }

  handlePhotoUpload() {
    this.uploader.uploadAll();
  }

  private setStates(states) {
    this.states = extend([], states);
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

  get now() {
    return this._now;
  }

  public fileOverBase(e: any): void {
    this.hasBaseDropZoneOver = e;
  }
}
