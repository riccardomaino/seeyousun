import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResumePageComponent } from './resume-page.component';

describe('ResumePageComponent', () => {
  let component: ResumePageComponent;
  let fixture: ComponentFixture<ResumePageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ResumePageComponent]
    });
    fixture = TestBed.createComponent(ResumePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
