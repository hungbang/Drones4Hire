import {Component, ViewEncapsulation, Input} from '@angular/core';
import {Router} from '@angular/router';

import {ProjectService} from '../../../services/project.service/project.service';
import {ModalConfirmationComponent} from "../../modals/modal-confirmation/modal-confirmation.component";
import {ModalService} from "../../../services/modal.service/modal.service";

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
    private router: Router,
    private _modalService: ModalService
  ) {
  }

  onCancel(project) {
    if (project.status !== 'NEW') {
      return;
    }

    this._modalService.push({
      component: ModalConfirmationComponent,
      type: 'ModalConfirmationComponent',
      values: {
        title: '',
        message: 'Do you really want to release payments?',
        confirm_btn_text: 'Yes',
        cancel_btn_text: 'No',
        confirm: (e) => this._cancel(e, project)
      }
    });
  }

  private _cancel(isAccepted, project) {
    if (!isAccepted) {
      this._modalService.pop();
      return;
    }

    this.projectService.cancelProject(project.id)
      .subscribe(
        () => {
          this._modalService.pop();
          project.status = 'CANCELLED';
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
