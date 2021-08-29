import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllResponceComponent } from './all-responce.component';

describe('AllResponceComponent', () => {
  let component: AllResponceComponent;
  let fixture: ComponentFixture<AllResponceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllResponceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllResponceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
