import { Component, Inject } from '@angular/core';
import { BeachService } from '../app/services/beach.service';
import { BeachSpec } from '../app/models/beachSpec';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import {
  MatDialog,
  MAT_DIALOG_DATA,
  MatDialogRef,
  MatDialogTitle,
  MatDialogContent,
  MatDialogActions,
  MatDialogClose,
} from '@angular/material/dialog';
import { resortFull } from '../app/models/resortFull';
import { Router } from '@angular/router';
import { BookDialogSelectComponent } from '../app/book-dialog-select/book-dialog-select.component';
import {catchError, throwError} from "rxjs";
import {formatDate} from "@angular/common";

@Component({
  selector: 'app-book-dialog',
  templateUrl: '../app/book-dialog/book-dialog.component.html',
  styleUrls: ['../app/book-dialog/book-dialog.component.scss']
})
export class BookDialogComponent {
  beachSpecification: BeachSpec = {} as BeachSpec;
  matrix: Array<Array<{ row: number; column: number; status: string; selected: boolean }>> = [];
  showError = false;

  constructor(public dialogRefN: MatDialogRef<BookDialogSelectComponent>, private beachService: BeachService,  public dialog: MatDialog, @Inject(MAT_DIALOG_DATA) public data: { resort: resortFull, people: number, selectedDate: Date }) {}

  ngOnInit(): void {
    this.loadReservationInformation();
  }

  setDate(event: MatDatepickerInputEvent<Date>) {
    this.loadReservationInformation();
    this.data.selectedDate = event.value as Date;
  }

  private loadReservationInformation(): void {
    this.beachService.getReservationInformation(this.data.resort.id, this.formatDate(this.data.selectedDate))
        .pipe(
            catchError(error => {
              if (error.status === 404) {
                // Gestisci l'errore 404 qui, ad esempio mostrando un messaggio di errore o eseguendo un'altra azione
                console.error('Errore 404: Risorsa non trovata');
                // Esempio: Mostra un messaggio di errore sulla pagina
                this.showError = true;
                // Esempio: Puoi anche lanciare un nuovo errore per gestirlo in un altro punto del codice
                return throwError('Errore 404: Risorsa non trovata');
              } else {
                // In caso di altri errori, propagali
                return throwError(error);
              }
            })
        )
        .subscribe((reservations) => {
          this.beachSpecification = reservations.data;
          this.buildMatrix();
        });
  }

  private formatDate(date: Date): string {
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0'); // Aggiungi lo zero davanti se il mese è inferiore a 10
    const day = date.getDate().toString().padStart(2, '0'); // Aggiungi lo zero davanti se il giorno è inferiore a 10
    return `${year}-${month}-${day}`;
  }

  private buildMatrix(): void {
    let currIndex = 0;
    this.matrix = [];
    for (let i = 0; i < this.beachSpecification.dimension.totalUmbrellaLine; i++) {
      let row = [];
      for (let j = 0; j < this.beachSpecification.dimension.totalUmbrellaColumn; j++) {
        currIndex=this.beachSpecification.reservedUmbrella.findIndex((reservedUmbrella) => reservedUmbrella.reservedUmbrellaLine === i && reservedUmbrella.reservedUmbrellaColumn === j);
        if (currIndex !== -1) {
          switch (this.beachSpecification.reservedUmbrella[currIndex].persistenceTypeEnum) {
            case 'FULL_DAY':
              row.push({ row: i, column: j, status: 'FULL_DAY', selected: false });
              break;
            case 'HALF_DAY_MORNING':
              row.push({ row: i, column: j, status: 'HALF_DAY_MORNING', selected: false });
              break;
            case 'HALF_DAY_AFTERNOON':
              row.push({ row: i, column: j, status: 'HALF_DAY_AFTERNOON', selected: false });
              break;
            default:
              break;
          }
        } else {
          row.push({ row: i, column: j, status: 'FREE', selected: false });
        }
      }
      this.matrix.push(row);
    }
  }

  selectUmbrella(row: number, column: number, status: string): void {
    if (status === 'FREE') {
      console.log(row, column);
      // Cambia lo stato dell'ombrellone solo se è libero
      this.matrix[row][column].selected = !this.matrix[row][column].selected;
      console.log(this.matrix[row][column].selected); 
      this.openDialog(row * this.matrix[0].length + column +1, this.data.selectedDate.getDate() + '/' + (this.data.selectedDate.getMonth() + 1) + '/' + this.data.selectedDate.getFullYear(), row, column, status);
    } else if (status === 'HALF_DAY_MORNING') {
      console.log(row, column);
      // Cambia lo stato dell'ombrellone solo se è libero
      this.matrix[row][column].selected = !this.matrix[row][column].selected;
      console.log(this.matrix[row][column].selected);
      this.openDialog(row * this.matrix[0].length + column +1, this.data.selectedDate.getDate() + '/' + (this.data.selectedDate.getMonth() + 1) + '/' + this.data.selectedDate.getFullYear(), row, column, status);
    }
  }

  openDialog(umbrella: number, date: string, row: number, column: number, status: string): void {
    const dialogRef = this.dialog.open(BookDialogSelectComponent, {
      data: {umbrella: umbrella, date: date, row: row, column: column, people: this.data.people, resortFull: this.data.resort, status: status},
      height: '362px',
      width: '350px',
    });
    dialogRef.afterClosed().subscribe((result) => {
      this.dialogRefN.close();
    });
  }

  changeColor(column: any): void {
    if (column.status !== 'reserved') {
      // Modifica l'SVG dell'ombrellone selezionato
      column.selected = !column.selected;
      if (column.selected) {
        // Cambia il colore di riempimento quando l'ombrellone viene selezionato
        column.fillColor = '#4C1B51';
      } else {
        // Ripristina il colore di riempimento predefinito quando l'ombrellone viene deselezionato
        column.fillColor = ''; // oppure '#<colore predefinito>'
      }
    }
  }

  onMouseEnter(column: any): void {
    if (column.status === 'selected') {
      // Modifica l'SVG quando il mouse entra
      // Ad esempio, cambiando il colore di riempimento
      column.fillColor = '#4C1B51';
    }
  }
  
  onMouseLeave(column: any): void {
    if (column.status === 'selected') {
      // Ripristina l'SVG al suo stato originale quando il mouse esce
      // Ad esempio, ripristinando il colore di riempimento
      column.fillColor = '#D24C00'; // Colore di riempimento originale
    }
  }

}