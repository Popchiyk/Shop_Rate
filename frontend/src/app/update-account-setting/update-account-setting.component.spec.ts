import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateAccountSettingComponent } from './update-account-setting.component';

describe('UpdateAccountSettingComponent', () => {
  let component: UpdateAccountSettingComponent;
  let fixture: ComponentFixture<UpdateAccountSettingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateAccountSettingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateAccountSettingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
