import { Component, OnInit, Input} from '@angular/core';

@Component({
  selector: 'm-footer',
  templateUrl: './m-footer.component.html',
  styleUrls: ['./m-footer.component.styl']
})
export class MFooterComponent implements OnInit {

  @Input() isNav: boolean ;
  @Input() isSupport: boolean ;
  @Input() isTouch: boolean ;

  constructor() { }

  ngOnInit() {
  }

}
