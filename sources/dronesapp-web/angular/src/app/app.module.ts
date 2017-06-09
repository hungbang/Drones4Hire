import {BrowserModule} from '@angular/platform-browser';
import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {RouterModule} from '@angular/router';
import {ROUTES} from './app.routes';

import {FileSelectDirective, FileDropDirective} from 'ng2-file-upload';

import {AppComponent} from './app.component';
import {AppComponents} from './components/index';
import {AppContainers} from './containers/index';
import {AppShared} from './shared/index';
import {AppPipes} from './pipes/index';
import {AppResolves} from './resolves/index';
import {AppServices} from './services/index';
import {AppGuards} from './guards/index';
import {NguiDatetimePickerModule} from '@ngui/datetime-picker';
import {Daterangepicker} from 'ng2-daterangepicker';
import { BProjectFilesComponent } from './components/blocks/b-project-files/b-project-files.component';

@NgModule({
  declarations: [
    AppComponent,
    AppComponents,
    AppContainers,
    AppPipes,
    AppShared,
    FileSelectDirective,
    FileDropDirective,
    BProjectFilesComponent,
  ],
  imports: [
    NguiDatetimePickerModule,
    Daterangepicker,
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(ROUTES, {useHash: true}),
  ],
  providers: [
    AppServices,
    AppGuards,
    AppResolves,
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  bootstrap: [AppComponent],
})
export class AppModule {
}
