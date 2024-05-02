import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsannoanceComponent } from './detailsannoance.component';

describe('DetailsannoanceComponent', () => {
  let component: DetailsannoanceComponent;
  let fixture: ComponentFixture<DetailsannoanceComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DetailsannoanceComponent]
    });
    fixture = TestBed.createComponent(DetailsannoanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
