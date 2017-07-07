import {Injectable} from '@angular/core';
import {RequestService} from '../request.service/request.service';
import {CountryModel} from './country.interface';
import {StateModel} from './state.interface';
import {ServiceModel} from './services.interface';
import {BudgetModel} from './budget.interface';
import {createObservable} from '../../shared/common/common-methods';
import {DurationModel} from './duration.interface';

@Injectable()
export class CommonService {
  public countries: CountryModel[] = [];
  public states: StateModel[] = [];
  public services: ServiceModel[] = [];
  public budgets: BudgetModel[] = [];
  public durations: DurationModel[] = [];

  constructor(private _requestService: RequestService) {
  }

  private fillCategory(data: {id: number; name: string; checked?: boolean}) {
    let category = {};
    category['id'] = data.id;
    category['name'] = data.name;

    if (typeof data['checked'] !== 'undefined') {
      category['checked'] = data.checked;
    }

    return category;
  }

  public normalizeServices(services: ServiceModel[]) {
    if (!services.length) {
      return [];
    }

    let normalizedServices = [];

    services.reduce((initialValue, currentValue) => {
      if (initialValue && initialValue.category.id === currentValue.category.id) {
        normalizedServices.filter((service) => {
          if (currentValue.category.id === service.id) {
            service['category'].push(this.fillCategory(currentValue));
          }
        });
      } else {
        normalizedServices.push({
          id: currentValue.category.id,
          name: currentValue.category.name,
          order: currentValue.category.order,
          category: [this.fillCategory(currentValue)]
        });
      }

      return currentValue;
    }, null);

    normalizedServices.forEach(el => {
      el.category.sort((objA: any, objB: any) => {
        return objA.name.toLowerCase() < objB.name.toLowerCase() ? -1 : 1
      })
    });

    return normalizedServices.sort((objA, objB) => {
      return objA.name.toLowerCase() < objB.name.toLowerCase() ? -1 : 1
    });


  }

  getCountries() {
    if (this.countries.length) {
      return createObservable(this.countries);
    }

    return this.getListOfCountries();
  }
  getListOfCountries() {
    return this._requestService.fetch('get', '/common/countries')
      .map((res) => {
        this.countries = res;
        return this.countries;
      })
  }

  getStates() {
    if (this.states.length) {
      return createObservable(this.states);
    }

    return this.getListOfStates();
  }
  private getListOfStates() {
    return this._requestService.fetch('get', '/common/states')
      .map((res) => {
        this.states = res;
        return this.states;
      })
  }

  getServices() {
    if(this.services.length) {
      return createObservable(this.services);
    }

    return this.getListOfServices();
  }
  private getListOfServices() {
    return this._requestService.fetch('get', '/common/services')
      .map(res => {
        this.services = res;
        // console.log('common services', this.services);
        return this.services;
      })
  }

  getDurations() {
    if(this.durations.length) {
      return createObservable(this.durations);
    }

    return this.getListOfDurations();
  }
  private getListOfDurations() {
    return this._requestService.fetch('get', '/common/durations')
      .map(res => {
        this.durations = res;
        // console.log('common durations', this.durations);
        return this.durations;
      })
  }

  getBudgets() {
    if (this.budgets.length) {
      return createObservable(this.budgets);
    }

    return this.getListOfBudgets();
  }
  private getListOfBudgets() {
    return this._requestService.fetch('get', '/common/budgets')
      .map(res => {
        this.budgets = res;
        // console.log('common budgets', this.budgets);
        return this.budgets;
      });
  }

  public getFee() {
    return this._requestService.fetch('get', '/common/services/fees')
  }
}
