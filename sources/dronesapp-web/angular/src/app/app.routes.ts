import {Routes} from '@angular/router';

import {IndexComponent} from './containers/index/index.component';
import {ProfileComponent} from './containers/profile/profile.component';
import {SearchComponent} from './containers/search/search.component';
import {MyProjectsComponent} from './containers/my-projects/my-projects.component';
import {TransactionsComponent} from './containers/transactions/transactions.component';
import {ProjectComponent} from './containers/project/project.component';
import {DashboardComponent} from './containers/dashboard/dashboard.component';
import {AccountComponent} from './containers/account/account.component';
import { ProjectManageComponent } from './containers/project-manage/project-manage.component';
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
import {ServicesResolve} from './resolves/services/services.resolve';
import {DurationsResolve} from './resolves/durations/durations.resolve';
import {PaidOptionsResolve} from './resolves/paid-options/paid-options.resolve';
import {PortfolioComponent} from './containers/portfolio/portfolio.component';
import {ProjectResolve} from './resolves/project/project.resolve';
import {ProfileResolve} from './resolves/profile/profile.resolve';
import {BidsResolve} from './resolves/bids/bids';
import {CommentsResolve} from './resolves/comments/comments';
import {ProjectDescriptionComponent} from './containers/project-description/project-description.component';
import {ProjectFilesComponent} from './containers/project-files/project-files.component';
import {MyProjectsResolve} from './resolves/my-projects/my-projects.resolve';
import {SMyProjectsComponent} from './components/sections/s-my-projects/s-my-projects.component';
import {PortfolioResolve} from './resolves/portfolio/portfolio.resolve';
import {ProfileServicesResolve} from './resolves/profile-services/profile-services.resolve';
import {FindProjectsResolve} from './resolves/find-projects/find-projects.resolve';
import {ClientProjectsResolve} from './resolves/client-projects/client-projects.resolve';
import {SDashboardComponent} from './components/sections/s-dashboard/s-dashboard.component';
import {SSearchProjectsComponent} from './components/sections/s-search-projects/s-search-projects.component';
import {DashboardProfileResolve} from './resolves/dashboard-profile/dashboard-profile.resolve';
import {PilotProjectsResolve} from './resolves/pilot-projects/pilot-projects.resolve';
import {SProjectAddComponent} from './components/sections/s-project-add/s-project-add.component';
import {SProjectEditComponent} from './components/sections/s-project-edit/s-project-edit.component';
import {BidInfoResolve} from "./resolves/bid-info/bid-info";
import {ProfileEquipmentsResolve} from './resolves/profile-equipments/profile-equipments.resolve';
import {WithdrawalRequestComponent} from './containers/withdrawal-request/withdrawal-request.component';
import {WorkPilotComponent} from "./containers/work-pilot/work-pilot.component";
import {WorkClientComponent} from "./containers/work-client/work-client.component";
import {PrivacyPolicyComponent} from "./containers/privacy-policy/privacy-policy.component";
import {TermsComponent} from "./containers/terms/terms.component";
import {AboutComponent} from "./containers/about/about.component";
import {PaymentComponent} from "./containers/payment/payment.component";

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
    canActivate: [AuthGuard],
    children: [
      {
        path: '',
        redirectTo: '/',
        pathMatch: 'full'
      },
      {
        path: ':user_id',
        component: ProfileComponent,
        resolve: {
          profile: ProfileResolve,
          portfolio: PortfolioResolve,
          services: ProfileServicesResolve,
          equipments: ProfileEquipmentsResolve
        },
      }
    ],
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
        children: [
          {
            path: '',
            redirectTo: '1',
            pathMatch: 'full'
          },
          {
            path: ':page',
            data: {
              pageLink: '/my-projects/bidding',
              status: ['NEW', 'PENDING']
            },
            runGuardsAndResolvers: 'paramsOrQueryParamsChange',
            resolve: {
              projects: MyProjectsResolve
            },
            component: SMyProjectsComponent
          }
        ]
      },
      {
        path: 'progress',
        children: [
          {
            path: '',
            redirectTo: '1',
            pathMatch: 'full'
          },
          {
            path: ':page',
            data: {
              pageLink: '/my-projects/progress',
              status: ['IN_PROGRESS']
            },
            runGuardsAndResolvers: 'paramsOrQueryParamsChange',
            resolve: {
              projects: MyProjectsResolve
            },
            component: SMyProjectsComponent
          }
        ]
      },
      {
        path: 'past',
        children: [
          {
            path: '',
            redirectTo: '1',
            pathMatch: 'full'
          },
          {
            path: ':page',
            data: {
              pageLink: '/my-projects/past',
              status: ['COMPLETED']
            },
            runGuardsAndResolvers: 'paramsOrQueryParamsChange',
            resolve: {
              projects: MyProjectsResolve
            },
            component: SMyProjectsComponent
          }
        ]
      }
    ]
  },
  {
    path: 'project',
    canActivate: [AuthGuard],
    children: [
      {
        path: '',
        redirectTo: '',
        pathMatch: 'full'
      },
      {
        path: 'manage',
        canActivate: [ClientGuard],
        component: ProjectManageComponent,
        data: {
          className: 'p-project'
        },
        resolve: {
          countries: CountriesResolve,
          budgets: BudgetsResolve,
          services: ServicesResolve,
          durations: DurationsResolve,
          paidoptions: PaidOptionsResolve
        },
        children: [
          {
            path: '',
            redirectTo: 'add',
            pathMatch: 'full'
          },
          {
            path: 'add',
            component: SProjectAddComponent
          },
          {
            path: 'edit',
            children: [
              {
                path: '',
                redirectTo: '', // TODO: why not works?
                pathMatch: 'full'
              },
              {
                path: ':projectId',
                resolve: {
                  project: ProjectResolve
                },
                component: SProjectEditComponent
              },
            ]
          },
        ]
      },
      {
        path: ':projectId',
        component: ProjectComponent,
        runGuardsAndResolvers: 'paramsOrQueryParamsChange',
        resolve: {
          project: ProjectResolve,
          bids: BidsResolve,
          comments: CommentsResolve,
          bidInfo: BidInfoResolve
        },
        children: [
          {
            path: '',
            redirectTo: 'description',
            pathMatch: 'full'
          },
          {
            path: 'description',
            component: ProjectDescriptionComponent
          },
          {
            path: 'files',
            component: ProjectFilesComponent
          }
        ]
      }
    ]
  },
  {
    path: 'dashboard',
    data: {
      className: 'p-dashboard'
    },
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
        resolve: {
          profile: DashboardProfileResolve
        },
        children: [
          {
            path: '',
            redirectTo: '1',
            pathMatch: 'full'
          },
          {
            path: ':page',
            resolve: {
              projects: ClientProjectsResolve
            },
            data: {
              pageLink: '/dashboard/client',
              userType: 'client'
            },
            runGuardsAndResolvers: 'paramsOrQueryParamsChange',
            component: SDashboardComponent
          }
        ]
      },
      {
        path: 'pilot',
        component: DashboardComponent,
        canActivate: [PilotGuard],
        resolve: {
          profile: DashboardProfileResolve
        },
        children: [
          {
            path: '',
            redirectTo: '1',
            pathMatch: 'full'
          },
          {
            path: ':page',
            resolve: {
              projects: PilotProjectsResolve
            },
            data: {
              pageLink: '/dashboard/pilot',
              userType: 'pilot'
            },
            runGuardsAndResolvers: 'paramsOrQueryParamsChange',
            component: SDashboardComponent
          }
        ]
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
            }
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
          },
          {
            path: 'portfolio',
            component: PortfolioComponent,
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
    resolve: {
      services: ServicesResolve,
      budgets: BudgetsResolve
    },
    children: [
      {
        path: '',
        redirectTo: '1',
        pathMatch: 'full'
      },
      {
        path: ':page',
        component: SSearchProjectsComponent,
        resolve: {
          projects: FindProjectsResolve
        },
        runGuardsAndResolvers: 'paramsOrQueryParamsChange'
      }
    ],
    data: {
      className: 'p-search'
    }
  },
  {
    path: 'withdrawal-request',
    component: WithdrawalRequestComponent,
    canActivate: [AuthGuard],
    data: {
      className: '_gray-bg'
    }
  },
  {
    path: 'pilot/how-it-works',
    component: WorkPilotComponent,
    data: {
      className: '_work-pilot'
    }
  },
  {
    path: 'client/how-it-works',
    component: WorkClientComponent,
    data: {
      className: '_work-client'
    }
  },
  {
    path: 'privacy-policy',
    component: PrivacyPolicyComponent,
    data: {
      className: '_privacy-policy'
    }
  },
  {
    path: 'terms-and-condition',
    component: TermsComponent,
    data: {
      className: '_terms'
    }
  },
  {
    path: 'about-us',
    component: AboutComponent,
    data: {
      className: '_about'
    }
  },
  {
    path: 'payment',
    canActivate: [AuthGuard],
    component: PaymentComponent,
    data: {
      className: 'p-payment'
    }
  },
  {
    path: '**',
    component: NotFoundComponent,
    data: {
      className: 'p-404'
    }
  }
];
