import {Injectable} from '@angular/core';
import {RequestService} from '../request.service/request.service';
import {CountryModel} from './country.interface';
import {StateModel} from './state.interface';

@Injectable()
export class CommonService {
  public countries: CountryModel[] = [];
  public states: StateModel[] = [];

  public accountCountry: string = null;
  public accountState: string = null;
  public companyCountry: string = null;
  public companyState: string = null;

  constructor(private _requestService: RequestService) {
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

  checkCountry(type: string) {
      return this[type] === 'United States';
  }
}
