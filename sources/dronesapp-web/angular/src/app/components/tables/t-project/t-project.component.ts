import {Component, ViewEncapsulation, Input} from '@angular/core';
import {Router} from '@angular/router';

import {ProjectService} from '../../../services/project.service/project.service';

@Component({
  selector: 't-project',
  templateUrl: './t-project.component.html',
  styleUrls: ['./t-project.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class TProjectComponent {
  @Input() projects;

  constructor(
    private projectService: ProjectService,
    private router: Router
  ) {
  }

  onCancel(project) {
    if (project.status !== 'NEW') {
      return;
    }
    this.projectService.cancelProject(project.id)
      .subscribe(
        () => {
          const index = this.projects.indexOf(project);
          this.projects.splice(index, 1);
        },
        err => {
          console.log('Cancel project error:', err);
        }
      );
  }

  onNavigate(project) {
    if (project.status !== 'NEW') {
      return;
    }
    this.router.navigate(['/project', 'manage', 'edit', project.id]);
  }

}
