import { Component, OnInit, ViewEncapsulation } from '@angular/core';

import {AccountService} from '../../../services/account.service/account.service';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'b-project-files',
  templateUrl: './b-project-files.component.html',
  styleUrls: ['./b-project-files.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class BProjectFilesComponent implements OnInit {
  files: Array<any> = [];


  constructor(
    public accountService: AccountService,
    private _route: ActivatedRoute
  ) { }

  ngOnInit() {
    console.log(this._route.snapshot.parent.data['project']);
  }

  deleteFile(id) {
    // TODO: connect API
  }

}
