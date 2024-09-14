import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'usCurrency',
  standalone: true
})
export class UsCurrencyPipe implements PipeTransform {

  transform(price: number): string {

    let currency: string = price.toFixed(2).toString();

    let formatedCurrency: string = `US$ ${currency}`;

    return formatedCurrency;
  }

}
