import { Component } from '@angular/core';

@Component({
  selector: 'app-review-dialog',
  templateUrl: './review-dialog.component.html',
  styleUrls: ['./review-dialog.component.scss']
})
export class ReviewDialogComponent {
  stars: number[] = [1, 2, 3, 4, 5]; // Array per rappresentare le stelle
  currentRating: number = 0; // Valutazione attuale

  constructor() { }

  setRating(rating: number) {
    // Imposta la valutazione attuale sulla stella cliccata
    this.currentRating = rating;
  }
}
