import {Routes} from '@angular/router';
import {IndexComponent} from './containers/index/index.component';
import {ProfileComponent} from './containers/profile/profile.component';
import {SearchComponent} from './containers/search/search.component';
import {MyProjectsComponent} from './containers/my-projects/my-projects.component';
import { TransactionsComponent } from './containers/transactions/transactions.component';
import { ProjectComponent } from './containers/project/project.component';
import { DashboardComponent } from './containers/dashboard/dashboard.component';
import { AccountComponent } from './containers/account/account.component';
import { ProjectAddComponent } from './containers/project-add/project-add.component';
import { SAuthorizationComponent } from './components/sections/s-authorization/s-authorization.component';
import { AuthGuard } from './guards/auth.guard/auth.guard';
import { ClientGuard } from './guards/client.guard/client.guard';
import { PilotGuard } from './guards/pilot.guard/pilot.guard';

export const ROUTES: Routes = [
  {
    path: '',
    component: IndexComponent
  },
  {
    path: 'sign-up',
    component: SAuthorizationComponent
  },
  {
    path: 'login',
    component: SAuthorizationComponent
  },
  {
    path: 'profile',
    canActivate: [PilotGuard],
    component: ProfileComponent
  },
  {
    path: 'my-projects',
    canActivate: [ClientGuard],
    component: MyProjectsComponent
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
          isPilotPage: true
        }
      },
      {
        path: 'client',
        component: ProjectComponent,
        canActivate: [ClientGuard],
        data: {
          isClientPage: true
        }
      },
      {
        path: 'add',
        canActivate: [ClientGuard],
        component: ProjectAddComponent
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
          isClientPage: true
        }
      },
      {
        path: 'pilot',
        component: DashboardComponent,
        canActivate: [PilotGuard],
        data: {
          isPilotPage: true,
          className: 'pilot-dashboard'
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
          isPilotPage: true
        }
      },
      {
        path: 'client',
        component: AccountComponent,
        canActivate: [ClientGuard],
        data: {
          isClientPage: true
        }
      }
    ]
  },
  {
    path: 'transactions',
    canActivate: [AuthGuard],
    component: TransactionsComponent
  },
  {
    path: 'search',
    canActivate: [PilotGuard],
    component: SearchComponent
  }
];
