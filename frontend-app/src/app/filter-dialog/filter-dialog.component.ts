import { Component, Inject } from '@angular/core';
import { Service } from '../models/service';
import {
  MatDialog,
  MAT_DIALOG_DATA,
  MatDialogRef,
  MatDialogTitle,
  MatDialogContent,
  MatDialogActions,
  MatDialogClose,
} from '@angular/material/dialog';
import {MatChipsModule} from '@angular/material/chips';


@Component({
  selector: 'filter-dialog',
  templateUrl: './filter-dialog.component.html',
  styleUrls: ['./filter-dialog.component.scss'],
})
export class FilterDialogComponent {
  services: Service[];
  selectedServices: string[] = []; // Array per mantenere traccia delle chips selezionate
  constructor(
    public dialogRef: MatDialogRef<FilterDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { services: Service[] }
  ) {
    this.services = data.services;
  }

  toggleSelection(service: Service) {
    const index = this.selectedServices.indexOf(service.name);
    if (index === -1) {
      this.selectedServices.push(service.name); // Aggiungi alla selezione se non è presente
    } else {
      this.selectedServices.splice(index, 1); // Rimuovi dalla selezione se presente
    }
    console.log(this.selectedServices);
  }

  isServiceSelected(service: Service): boolean {
    return this.selectedServices.includes(service.name);
  }
  
  closeDialog() {
    // Chiudi il dialogo e passa l'array di selezionati
    this.dialogRef.close(this.selectedServices);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
