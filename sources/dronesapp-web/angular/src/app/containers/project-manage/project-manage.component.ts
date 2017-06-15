import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {ProjectModel} from "../../services/project.service/project.interface";

@Component({
  selector: 'project-manage',
  templateUrl: './project-manage.component.html',
  styleUrls: ['./project-manage.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class ProjectManageComponent implements OnInit {
  formData: ProjectModel = null;

  constructor() {
    this.formData = {
      attachments: [],
      confirmationValid: true,
      finishDate: null,
      startDate: null,
      status: null,
      postProductionRequired: null,
      paidOptions: [],
      budget: {
        confirmationValid: null,
        currency: '',
        id: null,
        max: null,
        min: null,
        order: null,
        title: ''
      },
      duration: {
        id: null,
        max: null,
        min: null,
        order: null,
        title: ''
      },
      service: {
        id: null,
        name: '',
        category: {
          order: null,
          id: null,
          name: '',
        }
      },
      title: '',
      summary: '',
      imageURL: '',
      location: {
        country: {
          id: null,
          name: ''
        },
        address: '',
        city: '',
        postcode: null,
      }
    };
  }

  ngOnInit() {

  }

}
