import {ChangeDetectorRef, Component, Inject, OnInit} from '@angular/core';
import { BeachService } from '../services/beach.service';
import { MatDialogModule } from '@angular/material/dialog';
import { MatChipsModule } from '@angular/material/chips';
import { resortPresentation } from '../models/resortPresentation';
import { Service } from '../models/service';
import { NgFor } from '@angular/common';
import { apiResponseService } from '../models/apiResponseService';
import {
  MatDialog,
  MAT_DIALOG_DATA,
  MatDialogRef,
  MatDialogTitle,
  MatDialogContent,
  MatDialogActions,
  MatDialogClose,
} from '@angular/material/dialog';
import { FilterDialogComponent } from '../filter-dialog/filter-dialog.component';
import { Router } from '@angular/router';


@Component({
  selector: 'app-explore',
  templateUrl: './explore.component.html',
  styleUrls: ['./explore.component.scss'],
})
export class ExploreComponent {

  resortPresentation: resortPresentation[] = [];
  resortPresentationBackup: resortPresentation[] = [];
  isSearching: boolean = false;
  textHeader: string = 'Popolari in Italia';
  services: Service[] = [];
  error404Flag: boolean = false;
  errorMessage: string = 'Errore 404: Nessun resort trovato';


  constructor(private beachService: BeachService, public dialog: MatDialog, public router: Router, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.loadResortPresentation();
    this.loadServices();
  }

  isSelected(event: resortPresentation): void {
    this.router.navigate(['/resort-page'], { queryParams: { id: event.id } });
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(FilterDialogComponent, {
      data: { services: this.services },
      height: '600px',
      width: '600px',
    });

    dialogRef.afterClosed().subscribe(result => {
      this.textHeader = 'Risultati della ricerca';
      this.isSearching = true;
      this.resortPresentationBackup = this.resortPresentation;
      this.beachService.getResortPresentationByFilter(result.location, result.services).subscribe(
          (resortPresentation) => {
            console.log(this.resortPresentation);
            this.errorMessage = resortPresentation.message;
            this.resortPresentation = resortPresentation.data;
          },
          (error) => {
            console.log('Errore durante la chiamata al servizio:', error);
            if (error.status === 404) {
              console.log('Errore 404: Nessun resort trovato');
              this.error404Flag = true;
            } else {
              console.log('Errore sconosciuto:', error);
            }
          }
      );
    });
  }

  backToPopular(): void {
    this.textHeader = 'Popolari in Italia';
    this.isSearching = false;
    console.log(this.resortPresentationBackup);
    this.resortPresentation = this.resortPresentationBackup;
    this.error404Flag = false;
  }


  //suppliers
  private loadResortPresentation(): void {
    this.beachService.getResortPresentation().subscribe((resortPresentation) => {
      this.resortPresentation = resortPresentation.data.popularResorts;
    });
  }

  private loadServices(): void { 
      this.beachService.getServices().subscribe((response) => {
        this.services = response.data;
    });
  }





}
