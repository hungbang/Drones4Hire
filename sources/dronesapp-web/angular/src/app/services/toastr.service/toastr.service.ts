import {Injectable} from '@angular/core';
import {ToastsManager} from 'ng2-toastr';

@Injectable()
export class ToastrService {
  container: any;

  constructor(
    public toastr: ToastsManager
  ) {
  }

  showSuccess(message: string, title: string = 'Success!', settings?: any) {
    this.toastr.success(message, title, settings);
  }

  showError(message: string, title: string = 'Error!', settings: any = {}) {
    this.toastr.error(message, title, {...{showCloseButton: true, dismiss: 'click'}, ...settings});

  }

  showWarning(message: string, title: string = 'Alert!', settings: any = {}) {
    this.toastr.warning(message, title, settings);

  }

  showInfo(message: string, title: string = '', settings: any = {}) {
    this.toastr.info(message, title, settings);
  }

}
