import {Component, ElementRef, Input, OnInit, ViewEncapsulation} from '@angular/core';
import {FileUploader} from 'ng2-file-upload';

import {CommonService} from '../../../services/common.service/common.service';
import {RequestService} from '../../../services/request.service/request.service';
import {CountryModel} from '../../../services/common.service/country.interface';
import {StateModel} from '../../../services/common.service/state.interface';
import {BudgetModel} from '../../../services/common.service/budget.interface';
import {NormalizedServiceModel} from '../../../services/common.service/services.interface';
import {mergeDeep, extend} from '../../../shared/common/common-methods';
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
  @Input() project: ProjectModel = null;
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
    private _elementRef: ElementRef
  ) {

    this.uploader.onSuccessItem = (item, response, status, headers) => {
      console.log('onSuccessItem', response, item);
      const attachment = this.createAttachment(JSON.parse(response)['url'], item.file.name);
      this.formData.attachments.push(attachment);
      this.uploader.clearQueue();
      return {item, response, status, headers};
    };

    this.uploader.onErrorItem = (item, response, status, headers) => {
      console.log('problem with upload image');
      this.uploader.clearQueue();
      return {item, response, status, headers};
    };

    this.uploader.onAfterAddingFile = (item) => {
      console.log('onAfterAddingFile', item);

      this.isNotAcceptedFormat = false;
      if (this.formData.attachments.length < this.attachmentsLimit) {
        if (this.acceptedFormats.indexOf(item.file.type) !== -1) {
          this.uploader.uploadAll();
        } else {
          this.isNotAcceptedFormat = true;
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

    if (this.project) {
      this.isEditForm = true;
      this.formData = mergeDeep(this.formData, this.project);
      this.initPaidOptions();
      this.initCategories();
      console.log(this.formData);
    }
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

  initCategories() {
    if (this.project.service.id) {
      const service = this.services.find((service) => service.name === this.project.service.category.name);

      this.categories = service['category'];
    }

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

  initPaidOptions() {
    if (this.formData.paidOptions.length) {
      this.paidOptionsChecked = this.paidOptions.map((paidOption) => {
        return this.formData.paidOptions.some(option => option.id === paidOption.id);
      });
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

    if (this.isEditForm) {
      console.log(this.formData);
      return this.projectService.updateProject(this.formData)
        .subscribe((data) => {
          console.log(data);
        })
    } else {
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

  triggerFileClick() {
    if (this._elementRef && this._elementRef.nativeElement !== null) {
      this._elementRef.nativeElement.querySelector('[type="file"]').click();
    }
  }

  private createAttachment(url: string, filename: string) {
    return {
      attachmentURL: url,
      projectId: null,
      title: filename,
      type: 'PROJECT_ATTACHMENT'
    }
  }

  deleteAttachment(id) {
    this.formData.attachments = this.formData.attachments.filter(attach => attach.id !== id);
  }
}
