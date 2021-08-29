import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateAccountLoginAndPasswordComponent } from './update-account-login-and-password.component';

describe('UpdateAccountLoginAndPasswordComponent', () => {
  let component: UpdateAccountLoginAndPasswordComponent;
  let fixture: ComponentFixture<UpdateAccountLoginAndPasswordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateAccountLoginAndPasswordComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateAccountLoginAndPasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
