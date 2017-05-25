import {Injectable} from '@angular/core';
import {ActivatedRoute, NavigationEnd, Router} from '@angular/router';

@Injectable()
export class AppService {
  local: any;

  constructor(private _router: Router,
              private _activatedRouter: ActivatedRoute) {
    this.local = {
      isUserPilot: false,
      isUserClient: false
    };
    this._router.events
      .subscribe((event) => {
        if (event instanceof NavigationEnd) {
          let currentRoute = this._activatedRouter.snapshot;
          while (currentRoute.children.length) {
            currentRoute = currentRoute.children[0];
          }
          if (currentRoute.data.isPilotPage) {
            this.isUserPilot = true;
          } else if (currentRoute.data.isClientPage) {
            this.isUserClient = true;
          }
        }
      });
  }

  set isUserPilot(value) {
    this.local.isUserPilot = value;
  }

  set isUserClient(value) {
    this.local.isUserClient = value;
  }

  get isUserPilot() {
    return this.local.isUserPilot;
  }

  get isUserClient() {
    return this.local.isUserClient;
  }

  detectScrollBarWidth() {
    const div = document.createElement('div');
    div.style.overflowY = 'scroll';
    div.style.width = '50px';
    div.style.height = '50px';
    div.style.visibility = 'hidden';
    document.body.appendChild(div);
    const scrollWidth = div.offsetWidth - div.clientWidth;
    document.body.removeChild(div);
    return scrollWidth + 'px';
  }

  private isObject(item) {
    return (item && typeof item === 'object' && !Array.isArray(item) && item !== null);
  }

  public mergeDeep(target, source) {
    let output = Object.assign({}, target);
    if (this.isObject(target) && this.isObject(source)) {
      Object.keys(source).forEach(key => {
        if (this.isObject(source[key])) {
          if (!(key in target))
            Object.assign(output, { [key]: source[key] });
          else
            output[key] = this.mergeDeep(target[key], source[key]);
        } else {
          Object.assign(output, { [key]: source[key] });
        }
      });
    }
    return output;
  }

  public toCamelCase(str) {
    return str.split('_').map(function (word, index) {
      if (index == 0) {
        return word.toLowerCase();
      }
      return word.charAt(0).toUpperCase() + word.slice(1).toLowerCase();
    }).join('');
  }
}
