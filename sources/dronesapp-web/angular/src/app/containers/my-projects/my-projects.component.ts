import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {ModalInformationComponent} from "../../components/modals/modal-information/modal-information.component";
import {ModalService} from "../../services/modal.service/modal.service";
import {Router} from "@angular/router";

@Component({
  selector: 'my-projects',
  templateUrl: './my-projects.component.html',
  styleUrls: ['./my-projects.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class MyProjectsComponent implements OnInit {

  public tabs = [
    { link: 'bidding', text: 'Open for bidding', visibility: true, icon: 'box-search' },
    { link: 'progress', text: 'Work in progress', visibility: true, icon: 'box-settings' },
    { link: 'past', text: 'Past projects', visibility: true, icon: 'box-download' },
  ];

  constructor(
    private _modalService: ModalService,
    private _router: Router,
  ) { }

  ngOnInit() {
  }

  goToPostAProjectIfAccessExist(e) {
    e.preventDefault();

    if (!this._modalService) { // TODO: do we still need this modal
      this._modalService.push({
        component: ModalInformationComponent,
        type: 'ModalInformationComponent',
        values: {
          title: 'Post a project',
          message: 'Please complete your billing information on Payment info, to get the possibility to post your project.'
        }
      });

      return;
    }

    this._router.navigate(['/project/manage/add']);
  }


}
