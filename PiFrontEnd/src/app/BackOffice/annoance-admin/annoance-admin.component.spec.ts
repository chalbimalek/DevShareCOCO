import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnnoanceAdminComponent } from './annoance-admin.component';

describe('AnnoanceAdminComponent', () => {
  let component: AnnoanceAdminComponent;
  let fixture: ComponentFixture<AnnoanceAdminComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AnnoanceAdminComponent]
    });
    fixture = TestBed.createComponent(AnnoanceAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
