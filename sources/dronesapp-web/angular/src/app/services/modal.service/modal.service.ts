import { Injectable } from '@angular/core';
import {AppService} from '../app.service/app.service';

@Injectable()
export class ModalService {
  local: {
    isModalVisible: boolean
    currentModal: null|string
    modalTitle: string
  };
  constructor(
    public _appService: AppService
  ) {
    this.local = {
      isModalVisible: false,
      currentModal: '',
      modalTitle: ''
    };
  }

  stopProp(e) {
    e.stopPropagation();
  }

  toggleModal(state = !this.local.isModalVisible, currentModal = null, modalTitle = '') {
    this.local.isModalVisible = state;
    if (this.local.isModalVisible) {
      document.body.classList.add('_modal-open');
      document.body.style.marginRight = this._appService.detectScrollBarWidth();
    }
    this.local.modalTitle = modalTitle;
    setTimeout(() => {
      this.local.currentModal = currentModal;
      if (!this.local.isModalVisible) {
        document.body.classList.remove('_modal-open');
        document.body.style.marginRight = '0';
      }
    }, currentModal ? 0 : 200); // 200: modal fade time
  }

  set modalTitle(title: string) {
    this.local.modalTitle = title;
  }
}
