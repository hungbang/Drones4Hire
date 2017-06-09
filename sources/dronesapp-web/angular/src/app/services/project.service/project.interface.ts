import {PaidOptionModel} from './paid-option.interface';
import {BudgetModel} from '../common.service/budget.interface';
import {DurationModel} from '../common.service/duration.interface';
import {LocationModel} from '../common.service/location.interface';
import {ServiceModel} from '../common.service/services.interface';

export interface ProjectModel {
  confirmationValid: boolean;
  status: string;
  finishDate: number;
  startDate: number;
  paidOptions: PaidOptionModel[],
  budget: BudgetModel;
  duration: DurationModel;
  service: ServiceModel;
  title: string;
  summary: string;
  imageURL: string;
  location: LocationModel;
  postProductionRequired: boolean;
  id?: number;
  bidId?: number;
}
