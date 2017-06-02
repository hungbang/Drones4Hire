import {isArray} from 'util';
import {Observable} from 'rxjs';

export function isObject(item) {
  return (item && typeof item === 'object' && !Array.isArray(item) && item !== null);
}

export function mergeDeep(target, source) {
  let output = Object.assign({}, target);
  if (isObject(target) && isObject(source)) {
    Object.keys(source).forEach(key => {
      if (isObject(source[key])) {
        if (!(key in target))
          Object.assign(output, { [key]: source[key] });
        else
          output[key] = mergeDeep(target[key], source[key]);
      } else {
        Object.assign(output, { [key]: source[key] });
      }
    });
  }
  return output;
}

export function toCamelCase(str) {
  return str.split('_').map(function (word, index) {
    if (index == 0) {
      return word.toLowerCase();
    }
    return word.charAt(0).toUpperCase() + word.slice(1).toLowerCase();
  }).join('');
}

export function extend(target, ...args) {
  args.forEach((source) => {
    for (let key in source) {
      if (Object.prototype.hasOwnProperty.call(source, key)) {
        if (isArray(source[key])) {
          target[key] = extend([], source[key]);
        } else if (isObject(source[key])) {
          target[key] = extend({}, source[key]);
        } else {
          target[key] = source[key];
        }
      }
    }
  });

  return target;
}

export function createObservable(data) {
  return Observable.create((obs) => {
    obs.next(data);
    obs.complete();
  });
}
