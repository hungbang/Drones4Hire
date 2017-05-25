import {Injectable} from '@angular/core';
import {RequestService} from '../request.service/request.service';
import {CountryModel} from './country.interface';
import {StateModel} from './state.interface';
import {NormalizedServiceModel, ServiceModel} from './services.interface';

@Injectable()
export class CommonService {
  public countries: CountryModel[] = [];
  public states: StateModel[] = [];
  public services: ServiceModel[] = [];

  public accountCountry: string = null;
  public accountState: string = null;
  public companyCountry: string = null;
  public companyState: string = null;

  constructor(private _requestService: RequestService) {
  }

  private normalizeServices(services: NormalizedServiceModel[]) {
    if (!services.length) {
      return [];
    }

    let normalizedServices = [];

    services.reduce((initialValue, currentValue) => {
      if (initialValue && initialValue.category.id === currentValue.category.id) {
        let filtered = normalizedServices.filter((service) => {
          return currentValue.category.id === service.id;
        });

        filtered[0]['category'].push({
          id: currentValue.id,
          name: currentValue.name,
          checked: currentValue['checked']
        });
      } else {
        normalizedServices.push({
          id: currentValue.category.id,
          name: currentValue.category.name,
          order: currentValue.category.order,
          category: [{
            id: currentValue.id,
            name: currentValue.name,
            checked: currentValue['checked']
          }]
        });
      }

      return currentValue;
    }, null);

    return normalizedServices.sort((objA, objB) => objA.id - objB.id);
  }

  getListOfCountries() {
    return this._requestService.fetch('get', '/common/countries')
      .map((res) => {
        this.countries = res;
        return this.countries;
      })
  }

  getListOfStates() {
    return this._requestService.fetch('get', '/common/states')
      .map((res) => {
        this.states = res;
        return this.states;
      })
  }

  getListOfServices(activeServices: Array<number>) {
    return this._requestService.fetch('get', '/common/services')
      .subscribe(res => {
        res.forEach((service) => {
          if (activeServices.indexOf(service.id) !== -1) {
            return service.checked = true;
          }
          return service.checked = false;
        });

        this.services = this.normalizeServices(res);

        console.log('common services', this.services);
        return this.services;
      })
  }

  checkCountry(type: string) {
      return this[type] === 'United States';
  }
}
