import {
  Component, ComponentFactoryResolver, ElementRef, HostBinding, HostListener, Input, ReflectiveInjector, ViewChild,
  ViewContainerRef,
  ViewEncapsulation
} from '@angular/core';
import {ModalService} from '../../../services/modal.service/modal.service';
import {animate, style, transition, trigger} from "@angular/animations";

@Component({
  selector: 'modal-default',
  encapsulation: ViewEncapsulation.None,
  templateUrl: 'modal-default.component.html',
  styleUrls: ['modal-default.component.styl'],
  animations: [
    trigger('fadeIn', [
      transition('void => *', [
        style({opacity: 0}),
        animate('0.2s ease', style({opacity: 1}))
      ]),
      transition('* => void', [
        animate('0.2s ease', style({opacity: 0}))
      ])
    ])
  ]
})
export class ModalDefaultComponent {
  private currentComponent: any = null;
  public modal;
  @ViewChild('modalContentRef', {read: ViewContainerRef}) modalContentRef: ViewContainerRef;

  @Input() set modalContent(data: { component: any, values: any, type: string }) {
    if (!data) {
      return;
    }

    this.modal = data;

    setTimeout(() => {
      let values = Object.keys(data.values || {}).map((valueKey) => {
        return {provide: valueKey, useValue: data.values[valueKey]};
      });
      let resolvedValues = ReflectiveInjector.resolve(values);
      let injector = ReflectiveInjector.fromResolvedProviders(resolvedValues, this.modalContentRef.parentInjector);
      let factory = this._cfr.resolveComponentFactory(data.component);
      let component = factory.create(injector);

      values.forEach((value) => {
        component.instance[value.provide] = value.useValue;
      });

      this.modalContentRef.insert(component.hostView);

      if (this.currentComponent) {
        this.currentComponent.destroy();
      }

      this.currentComponent = component;
    }, 0);
  }
  constructor(
    public modalService: ModalService,
    private _cfr: ComponentFactoryResolver,
    private el: ElementRef
  ) { }

  @HostListener('click', ['$event'])
  onClick($event: MouseEvent) {
    $event.stopPropagation();
    let targetElement = $event.target;

    if (targetElement === this.el.nativeElement.firstElementChild) {
      this.hideModal();
    }
  }

  private hideModal() {
    this.modalService.pop();
    this.currentComponent = null;
  }

  @HostBinding('@fadeIn') fadeIn;
}
