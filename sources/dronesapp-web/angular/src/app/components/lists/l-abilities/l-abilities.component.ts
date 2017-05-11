import { Component, OnInit, ViewEncapsulation, Input } from '@angular/core';

@Component({
  selector: 'l-abilities',
  templateUrl: './l-abilities.component.html',
  styleUrls: ['./l-abilities.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class LAbilitiesComponent implements OnInit {
  @Input() abilities;

  constructor() { }

  ngOnInit() {}

}
