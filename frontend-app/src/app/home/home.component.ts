import {ChangeDetectorRef, Component, HostListener} from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { Router } from '@angular/router';
import { trigger, state, style, animate, transition } from '@angular/animations';
import {BeachService} from "../services/beach.service";
import {MatDialog} from "@angular/material/dialog";
import {FilterDialogComponent} from "../filter-dialog/filter-dialog.component";
import {Service} from "../models/service";
import { resortPresentation } from '../models/resortPresentation';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  providers: [MatDatepickerModule, MatNativeDateModule, MatFormFieldModule, MatInputModule]
})
export class HomeComponent {

  resortPresentation: resortPresentation[] = [];
  resortPresentationBackup: resortPresentation[] = [];
  isSearching: boolean = false;
  textHeader: string = 'Popolari in Italia';
  services: Service[] = [];
  error404Flag: boolean = false;
  errorMessage: string = 'Errore 404: Nessun resort trovato';
  isScrolled: boolean = false;
  constructor(private beachService: BeachService, public dialog: MatDialog, public router: Router, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.loadResortPresentation();
    this.loadServices();
  }



  @HostListener('window:scroll', ['$event'])
  checkScroll() {
    // Verifica se lo scroll è avvenuto, ad esempio controllando la posizione dello scroll verticale
    if (window.pageYOffset > 0) {
      this.isScrolled = true;
    } else {
      this.isScrolled = false;
    }
  }
  navigateToExplore() {
    this.router.navigate(['/explore']);
  }


  isSelected(event: resortPresentation): void {
    this.router.navigate(['/resort-page'], { queryParams: { id: event.id } });
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(FilterDialogComponent, {
      data: { services: this.services },
      height: '550px',
      width: '600px',
    });

    dialogRef.afterClosed().subscribe(result => {
      if(!result) return;
      this.textHeader = 'Risultati della ricerca';
      this.isSearching = true;
      this.beachService.getResortPresentationByFilter(result.location, result.services).subscribe(
          (resortPresentation) => {
            console.log(resortPresentation);
            this.errorMessage = resortPresentation.message;
            this.resortPresentation = resortPresentation.data;
            console.log(this.resortPresentation);
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
      this.resortPresentationBackup = this.resortPresentation;
    });
  }

  private loadServices(): void {
    this.beachService.getServices().subscribe((response) => {
      this.services = response.data;
    });
  }

  public resortByName(resortName: string): void {
    this.beachService.getResortByName(resortName).subscribe(  (resortPresentation) => {
        this.resortPresentation = resortPresentation.data;
        console.log(this.resortPresentation);
        this.textHeader = 'Risultati della ricerca';
        this.isSearching = true;
    });
  }
}
