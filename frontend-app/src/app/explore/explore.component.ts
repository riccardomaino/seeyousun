import { Component, Inject, OnInit } from '@angular/core';
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
  services: Service[] = [];

  constructor(private beachService: BeachService, public dialog: MatDialog, public router: Router) {}

  ngOnInit(): void {
    this.loadResortPresentation();
    this.loadServices();
  }

  isSelected(event: resortPresentation): void {
    this.router.navigate(['/resort-page'], { queryParams: { id: event.id } });
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(FilterDialogComponent, {
      data: {services: this.services},
      height: '600px',
      width: '600px',
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      //TODO: filter beaches
    });
  }

  //suppliers
  private loadResortPresentation(): void {
    this.beachService.getResortPresentation().subscribe((resortPresentation) => {
      this.resortPresentation = resortPresentation.data.popularResorts;
      console.log(this.resortPresentation)
    });
  }

  private loadServices(): void { 
      this.beachService.getServices().subscribe((response) => {
        this.services = response.data;
        console.log(this.services);
    });
  }





}
