import {NoIndexComponent} from './no-index/no-index.component';
import {SvgUseComponent} from './svg-use/svg-use.component';
import {RouterClassDirective} from './router-class/router-class.directive';
import {EqualValidator} from './equal-validator/equal-validator.directive';
import {ScrollTopDirective} from "./scroll-top/scroll-top.directive";

export const AppShared = [
  NoIndexComponent,
  SvgUseComponent,
  RouterClassDirective,
  ScrollTopDirective,
  EqualValidator
];
