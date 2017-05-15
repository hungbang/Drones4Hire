import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'nameFilter'
})
export class NameFilterPipe implements PipeTransform {

  transform(value: any, name: string): any {
    if (name) {
      return value.filter((item) => item.name.toLowerCase().indexOf(name.toLowerCase()) >= 0);
    }
    return value;
  }
}
