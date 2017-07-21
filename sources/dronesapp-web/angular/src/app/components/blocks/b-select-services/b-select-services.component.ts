import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import {animate, style, transition, trigger} from '@angular/animations';

import {AccountService} from '../../../services/account.service/account.service';
import {CommonService} from '../../../services/common.service/common.service';
import {RequestService} from '../../../services/request.service/request.service';

@Component({
  selector: 'b-select-services',
  templateUrl: './b-select-services.component.html',
  styleUrls: ['./b-select-services.component.styl'],
  encapsulation: ViewEncapsulation.None,
  animations: [
    trigger('slideToggle', [
      transition('void => *', [
        style({ height: '0' }),
        animate('0.5s ease', style({ height: '*' }))
      ]),
      transition('* => void', [
        animate('0.5s ease', style({ height: '0' }))
      ])
    ])
  ]
})
export class BSelectServicesComponent implements OnInit {
  services: any[] = [];

  constructor(
    private accountService: AccountService,
    private commonService: CommonService,
    private requestService: RequestService
  ) { }

  ngOnInit() {
    this.getServices();
  }

  getServices() {
    this.commonService.getServices()
      .map(
        res => {
          // console.log(res);
          return this.formatServices(res);
        }
      )
      .subscribe(
        res => {
          // console.log(res);
          this.services = res;
          this.getAccountServices();
        },
        err => {
          console.log('get services error:', err);
        }
      );
  }


  getAccountServices() {
    this.accountService.getAccountServices()
      .subscribe(
        res => {
          // console.log(res);
          this.setCheckedServices();
        },
        err => {
          console.log('get account services error:', err);
        }
      );
  }

  private setCheckedServices() {
    this.services.forEach(
      service => {
        service.categories.forEach(
          category => {
            if (this.accountService.activeServices.indexOf(category.id) !== -1) {
              category.checked = true;
            }
          }
        );
      }
    );
  }

  private formatServices(services) {
    if (!services.length) {
      return [];
    }

    const transformed = services.reduce(
      (formatted, item) => {
        let pos = null;

        if (formatted.some((el, i) => {
            pos = i;
            return el.name === item.name
          })) {
          formatted[pos].categories.push(this.createCategory(item));
        } else {
          formatted.push({
            name: item.name,
            categories: [this.createCategory(item)]
          });
        }
        return formatted;
      },
      []
    );

    transformed.forEach(el => {
      el.categories.sort((objA: any, objB: any) => {
        return objA.name.toLowerCase() < objB.name.toLowerCase() ? -1 : 1
      })
    });

    return transformed.sort((objA, objB) => {
      return objA.name.toLowerCase() < objB.name.toLowerCase() ? -1 : 1
    });

  }

  private createCategory(service) {
    let category = {};
    category['id'] = service.id;
    category['name'] = service.category.name;
    category['checked'] = service['checked'] ? service['checked'] : false;

    return category;
  }

  toggleItem(item: any) {
    item.active = !item.active;
  }

  toggleCheckbox(service) {
    const pos = this.accountService.activeServices.indexOf(service.id);

    if (pos === -1) {
      this.accountService.activeServices.push(service.id);
      service.checked = true;
    } else {
      this.accountService.activeServices.splice(pos, 1);
      service.checked = false;
    }
  }

  checkCheckboxState(id: number) {
    return this.accountService.activeServices.indexOf(id) !== -1;
  }

  checkSelectedCount(categories) {
    return categories.reduce(
      (count, item) => {
        return item.checked ? count += 1 : count;
      },
      0
    );
  }

}
