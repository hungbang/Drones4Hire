import { TestBed, async } from '@angular/core/testing';

import { AppComponent } from './app.component';
import { NoIndexComponent } from './shared/no-index/no-index.component';
import { HeaderComponent } from './components/sections/s-header/s-header.component';
import { FooterComponent } from './components/sections/s-footer/s-footer.component';
import { RouterModule } from '@angular/router';
import { MSiteComponent } from './components/menus/m-site/m-site.component';
import { MFooterComponent } from './components/menus/m-footer/m-footer.component';
import { LSocialComponent } from './components/lists/l-social/l-social.component';
import { ROUTES } from './app.routes';
import { IndexComponent } from './containers/index/index.component';
import { SAuthorizationComponent } from './components/sections/s-authorization/s-authorization.component';

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        AppComponent,
        NoIndexComponent,
        HeaderComponent,
        FooterComponent,
        MSiteComponent,
        LSocialComponent,
        MFooterComponent,
        IndexComponent,
        SAuthorizationComponent
      ],
      imports: [
        RouterModule.forRoot(ROUTES)
      ]
    }).compileComponents();
  }));

  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
});
