import { BrowserModule } from '@angular/platform-browser';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { AppComponents } from './components/index';
import { AppContainers } from './containers/index';
import { AppShared } from './shared/index';
import { AppPipes } from './pipes/index';
import { RouterModule } from '@angular/router';
import { ROUTES } from './app.routes';
import { Services } from './services/index';
import { AppResolves } from './resolves/index';
import { AppDirectives } from './directives/index';

@NgModule({
  declarations: [
    AppComponent,
    AppComponents,
    AppContainers,
    AppDirectives,
    AppPipes,
    AppShared
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(ROUTES)
  ],
  providers: [
    Services,
    AppResolves
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  bootstrap: [AppComponent]
})
export class AppModule {}
