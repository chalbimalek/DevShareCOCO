import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditAnnoanceComponent } from './edit-annoance.component';

describe('EditAnnoanceComponent', () => {
  let component: EditAnnoanceComponent;
  let fixture: ComponentFixture<EditAnnoanceComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditAnnoanceComponent]
    });
    fixture = TestBed.createComponent(EditAnnoanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
