import {ToastOptions} from 'ng2-toastr';

export class ToastrGlobalOption extends ToastOptions {
  animate = 'flyRight';
  newestOnTop = true;
  positionClass = 'toast-bottom-right';
  showCloseButton = false;
}
