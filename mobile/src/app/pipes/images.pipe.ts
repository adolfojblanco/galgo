import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'images',
  standalone: true
})
export class ImagesPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    return null;
  }

}
