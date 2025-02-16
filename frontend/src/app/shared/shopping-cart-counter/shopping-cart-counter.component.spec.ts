import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShoppingCartCounterComponent } from './shopping-cart-counter.component';

describe('ShoppingCartCounterComponent', () => {
  let component: ShoppingCartCounterComponent;
  let fixture: ComponentFixture<ShoppingCartCounterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShoppingCartCounterComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShoppingCartCounterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
