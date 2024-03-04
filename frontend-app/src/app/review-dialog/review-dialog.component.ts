import {Component, Inject} from '@angular/core';
import { BeachService} from "../services/beach.service";
import { FormControl, Validators, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";

@Component({
  selector: 'app-review-dialog',
  templateUrl: './review-dialog.component.html',
  styleUrls: ['./review-dialog.component.scss']
})
export class ReviewDialogComponent {
  stars: number[] = [1, 2, 3, 4, 5]; // Array per rappresentare le stelle
  currentRating: number = 1; // Valutazione attuale
  title: string = ''; // Titolo della recensione
  bodyReview: string = ''; // Corpo della recensione
  formattedDate: string = ''; // Data formattata
  constructor(private service: BeachService,  @Inject(MAT_DIALOG_DATA) public resortID: number) { }

  ngOnInit() {
    // Ottieni la data corrente
    const currentDate: Date = new Date();

    // Ottieni il giorno, il mese e l'anno dalla data corrente
        const day: number = currentDate.getDate();
        const month: number = currentDate.getMonth() + 1; // Gennaio è 0
        const year: number = currentDate.getFullYear();

    // Aggiungi lo zero davanti al giorno e al mese se sono inferiori a 10
        const formattedDay: string = day < 10 ? '0' + day : day.toString();
        const formattedMonth: string = month < 10 ? '0' + month : month.toString();

    // Costruisci la stringa della data nel formato desiderato
        this.formattedDate = `${year}-${formattedMonth}-${formattedDay}`;



  }

  setRating(rating: number) {
    // Imposta la valutazione attuale sulla stella cliccata
    this.currentRating = rating;
  }

  onSubmit() {
    this.service.createReview(this.title, this.bodyReview, this.currentRating, this.formattedDate, this.resortID).subscribe(
    (response) => {
        console.log(response);
    },
    (error) => {
        console.error(error);
    }
    );
  }
}
