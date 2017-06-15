import {CountriesResolve} from './countries/countries.resolve';
import {BudgetsResolve} from './budgets/budgets.resolve';
import {ServicesResolve} from './services/services.resolve';
import {DurationsResolve} from './durations/durations.resolve';
import {PaidOptionsResolve} from './paid-options/paid-options.resolve';
import {ProjectsResolve} from './projects/projects.resolve';
import {ProjectResolve} from './project/project.resolve';
import {BidsResolve} from './bids/bids';
import {ProfileResolve} from './profile/profile.resolve'
import {CommentsResolve} from './comments/comments';
import {MyProjectsResolve} from './my-projects/my-projects.resolve';
import {PortfolioResolve} from './portfolio/portfolio.resolve';
import {ProfileServicesResolve} from './profile-services/profile-services.resolve';
import {FindProjectsResolve} from './find-projects/find-projects.resolve';
import {ClientProjectsResolve} from './client-projects/client-projects.resolve';
import { DashboardProfileResolve } from './dashboard-profile/dashboard-profile.resolve';
import {PilotProjectsResolve} from "./pilot-projects/pilot-projects.resolve";

export const AppResolves = [
  BidsResolve,
  BudgetsResolve,
  ClientProjectsResolve,
  CommentsResolve,
  CountriesResolve,
  DashboardProfileResolve,
  DurationsResolve,
  FindProjectsResolve,
  MyProjectsResolve,
  PaidOptionsResolve,
  PilotProjectsResolve,
  PortfolioResolve,
  ProfileResolve,
  ProfileServicesResolve,
  ProjectResolve,
  ProjectsResolve,
  ServicesResolve,
];
