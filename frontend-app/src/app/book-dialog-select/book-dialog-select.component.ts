import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { resortFull } from '../models/resortFull';

interface DialogData {
  umbrella: number;
  date: string;
  row: number;
  column: number;
  people: number;
  numberOfSunbeds: number;
  persistenceTypeEnum: string;
  resortFull: resortFull;
}

@Component({
  selector: 'app-book-dialog-select',
  templateUrl: './book-dialog-select.component.html',
  styleUrls: ['./book-dialog-select.component.scss'],
})
export class BookDialogSelectComponent {
  constructor(public dialogRef: MatDialogRef<BookDialogSelectComponent>,public router: Router, @Inject(MAT_DIALOG_DATA) public data: DialogData ) { }
  count = 1; // Default lettini value


  ngOnInit(): void {
    
  }

  incrementSB(): void {
    if(this.count < 3)
      this.count++;
  }

  decrementSB(): void {
    if (this.count > 1)
      this.count--;
  }

  onSubmit(): void {
    // Chiudi il MatDialog
    this.data.numberOfSunbeds = this.count;
    this.data.persistenceTypeEnum = 'FULL_DAY';
    this.dialogRef.close();
    const dataString = JSON.stringify(this.data);
    const encodedData = encodeURIComponent(dataString);

    // Route alla pagina di pagamento
    this.router.navigate(['/resume-page'], { queryParams: { data: encodedData } });
  }
}
