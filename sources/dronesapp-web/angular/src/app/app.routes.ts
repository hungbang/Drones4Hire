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

export const ROUTES: Routes = [
  {
    path: '',
    component: IndexComponent
  },
  {
    path: 'profile',
    component: ProfileComponent
  },
  {
    path: 'my-projects',
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
        data: {
          isPilotPage: true
        }
      },
      {
        path: 'client',
        component: ProjectComponent,
        data: {
          isClientPage: true
        }
      },
      {
        path: 'add',
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
        data: {
          isClientPage: true
        }
      },
      {
        path: 'pilot',
        component: DashboardComponent,
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
        data: {
          isPilotPage: true
        }
      },
      {
        path: 'client',
        component: AccountComponent,
        data: {
          isClientPage: true
        }
      }
    ]
  },
  {
    path: 'transactions',
    component: TransactionsComponent
  },
  {
    path: 'search',
    component: SearchComponent
  }
];
