import {BrowserModule} from '@angular/platform-browser';
import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {BrowserXhr, HttpModule} from '@angular/http';
import {RouterModule} from '@angular/router';
import {ToastModule, ToastOptions} from 'ng2-toastr';
import {NguiDatetimePickerModule} from '@ngui/datetime-picker';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {Daterangepicker} from 'ng2-daterangepicker';
import {FileSelectDirective, FileDropDirective} from 'ng2-file-upload';
import {NgProgressBrowserXhr, NgProgressModule} from 'ngx-progressbar';

import {AppComponent} from './app.component';
import {AppComponents} from './components/index';
import {AppContainers} from './containers/index';
import {AppShared} from './shared/index';
import {AppPipes} from './pipes/index';
import {AppResolves} from './resolves/index';
import {AppServices} from './services/index';
import {AppGuards} from './guards/index';
import {entries} from './app.entries';
import {ROUTES} from './app.routes';
import {ToastrGlobalOption} from './services/toastr.service/toastr.global.settings';

@NgModule({
  declarations: [
    AppComponent,
    AppComponents,
    AppContainers,
    AppPipes,
    AppShared,
    FileSelectDirective,
    FileDropDirective,
  ],
  imports: [
    NguiDatetimePickerModule,
    BrowserAnimationsModule,
    Daterangepicker,
    BrowserModule,
    FormsModule,
    HttpModule,
    BrowserAnimationsModule,
    NgProgressModule,
    ToastModule.forRoot(),
    RouterModule.forRoot(ROUTES, {useHash: true}),
  ],
  providers: [
    AppServices,
    AppGuards,
    AppResolves,
    {provide: ToastOptions, useClass: ToastrGlobalOption},
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  bootstrap: [AppComponent],
  entryComponents: [
    entries
  ]
})
export class AppModule {
}
