import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WriteresponceComponent } from './writeresponce.component';

describe('WriteresponceComponent', () => {
  let component: WriteresponceComponent;
  let fixture: ComponentFixture<WriteresponceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WriteresponceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WriteresponceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
