import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrashionComponent } from './registrashion.component';

describe('RegistrashionComponent', () => {
  let component: RegistrashionComponent;
  let fixture: ComponentFixture<RegistrashionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistrashionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrashionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
