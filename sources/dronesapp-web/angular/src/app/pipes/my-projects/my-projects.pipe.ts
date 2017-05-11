import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'myProjects'
})
export class MyProjectsPipe implements PipeTransform {

  transform(projects: any, args: string): any {
    if (args === 'inProcess') {
      return projects.filter((item) => item.isInProcess);
    } else if (args === 'openForBidding') {
      return projects;
    } else if (args === 'pastProjects') {
      return projects;
    } else if (args === 'projectsFilterByName') {

    }
    return projects;
  }

}
