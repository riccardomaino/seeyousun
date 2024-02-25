import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookDialogSelectComponent } from './book-dialog-select.component';

describe('BookDialogSelectComponent', () => {
  let component: BookDialogSelectComponent;
  let fixture: ComponentFixture<BookDialogSelectComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BookDialogSelectComponent]
    });
    fixture = TestBed.createComponent(BookDialogSelectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
