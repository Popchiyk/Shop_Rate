import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeadingforadmComponent } from './headingforadm.component';

describe('HeadingforadmComponent', () => {
  let component: HeadingforadmComponent;
  let fixture: ComponentFixture<HeadingforadmComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HeadingforadmComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HeadingforadmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
