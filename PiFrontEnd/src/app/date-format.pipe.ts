import { DatePipe } from '@angular/common';
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dateFormat'
})
export class DateFormatPipe implements PipeTransform {

  transform(value: any, format: string = 'dd/MM/yyyy'): any {
    console.log('Value:', value);
    const datePipe = new DatePipe('en-US');
    const formattedValue = datePipe.transform(value, format);
    console.log('Formatted value:', formattedValue);
    return formattedValue;
  }

}
