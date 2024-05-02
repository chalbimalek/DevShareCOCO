import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCollocationComponent } from './add-collocation.component';

describe('AddCollocationComponent', () => {
  let component: AddCollocationComponent;
  let fixture: ComponentFixture<AddCollocationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddCollocationComponent]
    });
    fixture = TestBed.createComponent(AddCollocationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
