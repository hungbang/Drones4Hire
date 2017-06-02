import {Routes} from '@angular/router';
import {IndexComponent} from './containers/index/index.component';
import {ProfileComponent} from './containers/profile/profile.component';
import {SearchComponent} from './containers/search/search.component';
import {MyProjectsComponent} from './containers/my-projects/my-projects.component';
import {TransactionsComponent} from './containers/transactions/transactions.component';
import {ProjectComponent} from './containers/project/project.component';
import {DashboardComponent} from './containers/dashboard/dashboard.component';
import {AccountComponent} from './containers/account/account.component';
import {ProjectAddComponent} from './containers/project-add/project-add.component';
import {SAuthorizationComponent} from './components/sections/s-authorization/s-authorization.component';
import {DetailsComponent} from './containers/details/details.component';
import {PreferencesComponent} from './containers/preferences/preferences.component';

import {AuthGuard} from './guards/auth.guard/auth.guard';
import {ClientGuard} from './guards/client.guard/client.guard';
import {PilotGuard} from './guards/pilot.guard/pilot.guard';
import {SecurityComponent} from './containers/security/security.component';
import {NotificationsComponent} from './containers/notifications/notifications.component';
import {NotFoundComponent} from './containers/not-found/not-found.component';
import {CountriesResolve} from './resolves/countries/countries.resolve';
import {BudgetsResolve} from './resolves/budgets/budgets.resolve';
import {ServicesResolve} from './resolves/services/budgets.resolve';
import {DurationsResolve} from './resolves/durations/durations.resolve';
import {PaidOptionsResolve} from './resolves/paid-options/paid-options.resolve';
import {ProjectsResolve} from './resolves/projects/projects.resolve';
import {BProjectsSearchComponent} from './components/blocks/b-projects-search/b-projects-search.component';

export const ROUTES: Routes = [
  {
    path: '',
    component: IndexComponent,
    canActivate: [AuthGuard],
    data: {
      className: 'p-index'
    }
  },
  {
    path: 'sign-up',
    component: SAuthorizationComponent,
    data: {
      className: 'p-signup'
    }
  },
  {
    path: 'login',
    component: SAuthorizationComponent,
    data: {
      className: 'p-login'
    }
  },
  {
    path: 'profile',
    canActivate: [PilotGuard],
    component: ProfileComponent,
    data: {
      className: 'p-profile'
    }
  },
  {
    path: 'my-projects',
    canActivate: [ClientGuard],
    component: MyProjectsComponent,
    data: {
      className: 'p-projects'
    },
    children: [
      {
        path: '',
        redirectTo: 'bidding',
        pathMatch: 'full'
      },
      {
        path: 'bidding',
        component: BProjectsSearchComponent,
        resolve: {
          projects: ProjectsResolve
        },
        data: {
          status: 'NEW'
        }
      },
      {
        path: 'progress',
        component: BProjectsSearchComponent,
        resolve: {
          projects: ProjectsResolve
        },
        data: {
          status: 'NEW'
        }
      },
      {
        path: 'past',
        component: BProjectsSearchComponent,
        resolve: {
          projects: ProjectsResolve
        },
        data: {
          status: 'NEW'
        }
      }
    ]
  },
  {
    path: 'project',
    children: [
      {
        path: '',
        redirectTo: '',
        pathMatch: 'full'
      },
      {
        path: 'pilot',
        component: ProjectComponent,
        canActivate: [PilotGuard],
        data: {
          isPilotPage: true,
          className: 'p-project'
        }
      },
      {
        path: 'client',
        component: ProjectComponent,
        canActivate: [ClientGuard],
        data: {
          isClientPage: true,
          className: 'p-project'
        }
      },
      {
        path: 'add',
        canActivate: [ClientGuard],
        component: ProjectAddComponent,
        data: {
          isClientPage: true,
          className: 'p-project'
        },
        resolve: {
          countries: CountriesResolve,
          budgets: BudgetsResolve,
          services: ServicesResolve,
          durations: DurationsResolve,
          paidoptions: PaidOptionsResolve
        }
      }
    ]
  },
  {
    path: 'dashboard',
    children: [
      {
        path: '',
        pathMatch: 'full',
        redirectTo: ''
      },
      {
        path: 'client',
        component: DashboardComponent,
        canActivate: [ClientGuard],
        data: {
          className: 'p-dashboard'
        }
      },
      {
        path: 'pilot',
        component: DashboardComponent,
        canActivate: [PilotGuard],
        data: {
          className: 'p-dashboard'
        }
      }
    ]
  },
  {
    path: 'account',
    children: [
      {
        path: '',
        redirectTo: '',
        pathMatch: 'full'
      },
      {
        path: 'pilot',
        component: AccountComponent,
        canActivate: [PilotGuard],
        data: {
          className: 'p-account'
        },
        children: [
          {
            path: '',
            redirectTo: 'details',
            pathMatch: 'full'
          },
          {
            path: 'details',
            component: DetailsComponent,
            canActivate: [PilotGuard],
            data: {
              className: 'p-account'
            }
          },
          {
            path: 'preferences',
            component: PreferencesComponent,
            canActivate: [PilotGuard],
            data: {
              className: 'p-account'
            },
            children: [
              {
                path: '',
                redirectTo: 'photo',
                pathMatch: 'full'
              },
              {
                path: 'photo',
                component: NotFoundComponent,
                canActivate: [PilotGuard],
                data: {
                  className: 'p-account'
                }
              },
              {
                path: 'video',
                component: NotFoundComponent,
                canActivate: [PilotGuard],
                data: {
                  className: 'p-account'
                }
              }
            ]
          },
          {
            path: 'security',
            component: SecurityComponent,
            canActivate: [PilotGuard],
            data: {
              className: 'p-account'
            }
          },
          {
            path: 'notifications',
            component: NotificationsComponent,
            canActivate: [PilotGuard],
            data: {
              className: 'p-account'
            }
          }
        ]
      },
      {
        path: 'client',
        component: AccountComponent,
        canActivate: [ClientGuard],
        data: {
          className: 'p-account'
        },
        children: [
          {
            path: '',
            redirectTo: 'details',
            pathMatch: 'full'
          },
          {
            path: 'details',
            component: DetailsComponent,
            canActivate: [ClientGuard],
            data: {
              className: 'p-account'
            }
          },
          {
            path: 'security',
            component: SecurityComponent,
            canActivate: [ClientGuard],
            data: {
              className: 'p-account'
            }
          },
          {
            path: 'notifications',
            component: NotificationsComponent,
            canActivate: [ClientGuard],
            data: {
              className: 'p-account'
            }
          }
        ]
      }
    ]
  },
  {
    path: 'transactions',
    canActivate: [AuthGuard],
    component: TransactionsComponent,
    data: {
      className: 'p-transactions'
    }
  },
  {
    path: 'search',
    canActivate: [PilotGuard],
    component: SearchComponent,
    data: {
      className: 'p-search'
    }
  }
];
