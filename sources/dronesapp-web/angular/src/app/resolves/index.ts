import {CountriesResolve} from './countries/countries.resolve';
import {BudgetsResolve} from './budgets/budgets.resolve';
import {ServicesResolve} from './services/budgets.resolve';
import {DurationsResolve} from './durations/durations.resolve';
import {PaidOptionsResolve} from './paid-options/paid-options.resolve';
import {ProjectsResolve} from './projects/projects.resolve';
import { ProfileResolve } from './profile/profile.resolve'

export const AppResolves = [
  BudgetsResolve,
  CountriesResolve,
  DurationsResolve,
  PaidOptionsResolve,
  ProjectsResolve,
  ServicesResolve,
  ProfileResolve,
];
