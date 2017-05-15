import { MyProjectsPipe } from './my-projects/my-projects.pipe';
import { NameFilterPipe } from './name-filter/name-filter.pipe';
import { OrderByPipe } from './order-by/order-by.pipe';

export const AppPipes = [
  MyProjectsPipe,
  NameFilterPipe,
  OrderByPipe
];
