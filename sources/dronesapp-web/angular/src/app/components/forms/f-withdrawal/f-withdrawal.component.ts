import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'f-withdrawal',
  templateUrl: './f-withdrawal.component.html',
  styleUrls: ['./f-withdrawal.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FWithdrawalComponent implements OnInit {
  amount: string = '';
  description: string = '';
  submitted: boolean = false;
  @Input() balance: number = 0;

  constructor() { }

  ngOnInit() {
  }

  sendRequest(e, form) {
    e.preventDefault();

    this.submitted = true;

    if (form.invalid || !this.isCorrectValue) {
      return;
    }

    console.log('requested withdrawal amount:', parseFloat(this.amount)); // TODO: connect to API
    form.resetForm();
    this.submitted = false;
  }

  get isCorrectValue() {
    const value = parseFloat(this.amount); // TODO: use replacement on enter instead parsing?
    return isFinite(value) && value > 0 && value <= this.balance;
  }

}
