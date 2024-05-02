import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnnonceCollocationComponent } from './annonce-collocation.component';

describe('AnnonceCollocationComponent', () => {
  let component: AnnonceCollocationComponent;
  let fixture: ComponentFixture<AnnonceCollocationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnnonceCollocationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnnonceCollocationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
