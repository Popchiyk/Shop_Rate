import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FooterforadminComponent } from './footerforadmin.component';

describe('FooterforadminComponent', () => {
  let component: FooterforadminComponent;
  let fixture: ComponentFixture<FooterforadminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FooterforadminComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FooterforadminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
