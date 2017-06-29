import {Component, ViewEncapsulation, Input} from '@angular/core';
import {Router} from '@angular/router';
import {NgProgressService} from 'ngx-progressbar';

import {ProjectService} from '../../../services/project.service/project.service';
import {ModalConfirmationComponent} from '../../modals/modal-confirmation/modal-confirmation.component';
import {ModalService} from '../../../services/modal.service/modal.service';
import {ToastrService} from '../../../services/toastr.service/toastr.service';

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
    private _modalService: ModalService,
    private toastrService: ToastrService,
    private progressbarService: NgProgressService
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
    this.progressbarService.start();
    this.projectService.cancelProject(project.id)
      .subscribe(
        () => {
          this.progressbarService.done();
          this._modalService.pop();
          project.status = 'CANCELLED';
          this.toastrService.showSuccess('Project canceled');
        },
        err => {
          this.progressbarService.done();
          console.log('Cancel project error:', err);

          if (err.status === 500) {
            this.toastrService.showError('Server error. Please, try later.');
          } else {
            if (err.status === 400) {
              const body = err.json();
              if (body && body.validationErrors) {
                body.validationErrors.forEach(item => {
                  this.toastrService.showError(item.field);
                });
              }
            } else {
              this.toastrService.showError('Please check your data');
            }
          }

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
