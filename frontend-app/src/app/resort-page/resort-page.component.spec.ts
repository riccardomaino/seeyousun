import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResortPageComponent } from './resort-page.component';

describe('ResortPageComponent', () => {
  let component: ResortPageComponent;
  let fixture: ComponentFixture<ResortPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ResortPageComponent]
    });
    fixture = TestBed.createComponent(ResortPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
