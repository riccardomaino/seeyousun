import { Component, Inject } from '@angular/core';
import { BeachService } from '../services/beach.service';
import { BeachSpec } from '../models/beachSpec';
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
import { resortFull } from '../models/resortFull';
import { Router } from '@angular/router';
import { BookDialogSelectComponent } from '../book-dialog-select/book-dialog-select.component';

@Component({
  selector: 'app-book-dialog',
  templateUrl: './book-dialog.component.html',
  styleUrls: ['./book-dialog.component.scss']
})
export class BookDialogComponent {
  beachSpecification: BeachSpec = {} as BeachSpec;
  matrix: Array<Array<{ row: number; column: number; status: string; selected: boolean }>> = [];
  

  constructor(public dialogRefN: MatDialogRef<BookDialogSelectComponent>, private beachService: BeachService,  public dialog: MatDialog, @Inject(MAT_DIALOG_DATA) public data: { resort: resortFull, people: number, selectedDate: Date }) {}

  ngOnInit(): void {
    this.loadReservationInformation();
  }

  setDate(event: MatDatepickerInputEvent<Date>) {
    this.data.selectedDate = event.value as Date;
  }

  private loadReservationInformation(): void {
    this.beachService.getReservationInformation(2, '2024-01-18').subscribe((reservations) => {
      this.beachSpecification = reservations.data;
      this.buildMatrix();
    });
  }

  private buildMatrix(): void {
    for (let i = 0; i < this.beachSpecification.dimension.totalUmbrellaLine; i++) {
      let row = [];
      for (let j = 0; j < this.beachSpecification.dimension.totalUmbrellaColumn; j++) {
        if (this.beachSpecification.reservedUmbrella.some((reservedUmbrella) => reservedUmbrella.reservedUmbrellaLine === i && reservedUmbrella.reservedUmbrellaColumn === j)) {
          row.push({ row: i, column: j, status: 'reserved', selected: false });
        } else {
          row.push({ row: i, column: j, status: 'free', selected: false });
        }
      }
      this.matrix.push(row);
    }
  }

  selectUmbrella(row: number, column: number, status: string): void {
    if (status === 'free') {
      console.log(row, column);
      // Cambia lo stato dell'ombrellone solo se è libero
      this.matrix[row][column].selected = !this.matrix[row][column].selected;
      console.log(this.matrix[row][column].selected); 
      this.openDialog(row * this.matrix[0].length + column +1, this.data.selectedDate.getDate() + '/' + (this.data.selectedDate.getMonth() + 1) + '/' + this.data.selectedDate.getFullYear(), row, column);
    }
  }

  openDialog(umbrella: number, date: string, row: number, column: number): void {
    const dialogRef = this.dialog.open(BookDialogSelectComponent, {
      data: {umbrella: umbrella, date: date, row: row, column: column, people: this.data.people, resortFull: this.data.resort},
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