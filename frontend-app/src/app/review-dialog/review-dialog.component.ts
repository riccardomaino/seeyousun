import {Component, Inject} from '@angular/core';
import { BeachService} from "../services/beach.service";
import { FormControl, Validators, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialog} from "@angular/material/dialog";
import {ConfirmationDialogComponent} from "../confirmation-dialog/confirmation-dialog.component";
import {Router} from "@angular/router";

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
  constructor(private service: BeachService,  @Inject(MAT_DIALOG_DATA) public resortID: number, public dialog: MatDialog, public router: Router) { }

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

  onAnnulla() {
    this.dialog.closeAll();
  }

  onSubmit() {
    this.service.createReview(this.title, this.bodyReview, this.currentRating, this.formattedDate, this.resortID).subscribe(
    (response) => {
        console.log(response);
        const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
            data: {title: 'Recensione aggiunta  :)', message:'Il tuo feedback è molto importante per noi.', status: true},
            height: '400px',
            width: '400px',
        });
        // Chiudi automaticamente il dialogo dopo 2 secondi
        setTimeout(() => {
            dialogRef.close();
            // Imposta un ritardo di 2 secondi prima di reindirizzare
            setTimeout(() => {
                this.router.navigate(['/resort-page'], { queryParams: { id: this.resortID } });
            }, 2000);
        }, 2000);
    },
    (error) => {
        console.log(error);
        const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
            data: {title: 'Qualcosa è andato storto :(', message:'Non siamo riusciti ad aggiungere la tua recensione. Riprova più tardi.', status: false},
            height: '400px',
            width: '400px',
        });
        // Chiudi automaticamente il dialogo dopo 2 secondi
        setTimeout(() => {
            dialogRef.close( {});
            // Imposta un ritardo di 2 secondi prima di reindirizzare
            setTimeout(() => {
                //Do nothing
            }, 2000);
        }, 2000);
    }
    );
  }
}
