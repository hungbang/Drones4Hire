import { Injectable } from '@angular/core';

@Injectable()
export class ModalService {
  modals = [];

  constructor() {}

  push(modal) {
    this.modals.push(modal);
  }

  pop() {
    this.modals.pop();
  }

  clear() {
    this.modals = [];
  }
}
