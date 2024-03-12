import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { resortFull } from '../models/resortFull';
import {MatSelectChange} from "@angular/material/select";

interface DialogData {
  umbrella: number;
  date: string;
  row: number;
  column: number;
  people: number;
  numberOfSunbeds: number;
  persistenceTypeEnum: string;
  resortFull: resortFull;
  status: string;
}

@Component({
  selector: 'app-book-dialog-select',
  templateUrl: './book-dialog-select.component.html',
  styleUrls: ['./book-dialog-select.component.scss'],
})
export class BookDialogSelectComponent {
  options: any[] = [{
    optionName: 'FULL_DAY',
    value : 'Giornata intera 30€'
  }, {
    optionName: 'HALF_DAY_MORNING',
    value : 'Mattina 15€'
  }, {
    optionName: 'HALF_DAY_AFTERNOON',
    value : 'Pomeriggio 15€'
  }];

  filteredOptions: any [] = [];
  selectedValue: string = '';

  constructor(public dialogRef: MatDialogRef<BookDialogSelectComponent>,public router: Router, @Inject(MAT_DIALOG_DATA) public data: DialogData ) { }
  count = 1; // Default lettini value


  ngOnInit(): void {
    if(this.data.status === 'HALF_DAY_MORNING') {
      this.filteredOptions = this.options.filter(option => option.optionName !== 'HALF_DAY_MORNING' && option.optionName !== 'FULL_DAY');
    } else if (this.data.status === 'HALF_DAY_AFTERNOON') {
      this.filteredOptions = this.options.filter(option => option.optionName !== 'HALF_DAY_AFTERNOON' && option.optionName !== 'FULL_DAY');
    } else {
      this.filteredOptions = this.options.slice();
    }
    // Default value
    this.data.row = this.data.row + 1;
    this.data.column = this.data.column + 1;
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
    this.data.numberOfSunbeds = this.count;
    this.data.persistenceTypeEnum = this.selectedValue;
    this.dialogRef.close();
    const dataString = JSON.stringify(this.data);
    const encodedData = encodeURIComponent(dataString);

    // Route alla pagina di pagamento
    this.router.navigate(['/resume-page'], { queryParams: { data: encodedData } });
  }
}
