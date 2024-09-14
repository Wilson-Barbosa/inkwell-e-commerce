import { UsCurrencyPipe } from './us-currency.pipe';

describe('UsCurrencyPipe', () => {
  it('create an instance', () => {
    const pipe = new UsCurrencyPipe();
    expect(pipe).toBeTruthy();
  });
});
