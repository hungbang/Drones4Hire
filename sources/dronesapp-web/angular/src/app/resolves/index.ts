import {CountriesResolve} from './countries/countries.resolve';
import {BudgetsResolve} from './budgets/budgets.resolve';
import {ServicesResolve} from './services/budgets.resolve';
import {DurationsResolve} from './durations/durations.resolve';
import {PaidOptionsResolve} from './paid-options/paid-options.resolve';
import {ProjectsResolve} from './projects/projects.resolve';
import {ProjectResolve} from './project/project.resolve';
import {BidsResolve} from './bids/bids';
import { ProfileResolve } from './profile/profile.resolve'
import {CommentsResolve} from './comments/comments';
import {MyProjectsResolve} from './my-projects/my-projects.resolve';

export const AppResolves = [
  BidsResolve,
  BudgetsResolve,
  CommentsResolve,
  CountriesResolve,
  DurationsResolve,
  MyProjectsResolve,
  PaidOptionsResolve,
  ProjectResolve,
  ProjectsResolve,
  ServicesResolve,
  ProfileResolve,
];
