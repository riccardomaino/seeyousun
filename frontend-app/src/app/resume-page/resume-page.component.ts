import { Component, ViewChild, ElementRef, AfterViewInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { resortFull } from '../models/resortFull';
import { Location } from '@angular/common';
import {BeachService} from "../services/beach.service";
import {BookDialogSelectComponent} from "../book-dialog-select/book-dialog-select.component";
import {MatDialog} from "@angular/material/dialog";
import { ConfirmationDialogComponent } from "../confirmation-dialog/confirmation-dialog.component";

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
  selector: 'app-resume-page',
  templateUrl: './resume-page.component.html',
  styleUrls: ['./resume-page.component.scss']
})


export class ResumePageComponent implements AfterViewInit {
  @ViewChild('navbar', {static: false}) navbar: ElementRef;
  navbarHeight: number = 0;
  parsedData: DialogData = {} as DialogData;
  selectedOption: string = '2';

  constructor(private route: ActivatedRoute, private location: Location, private service: BeachService,  public dialog: MatDialog, private router: Router) {
    // Initialize the 'navbar' property
    this.navbar = new ElementRef(null);
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      if (params['data']) {
        const decodedData = decodeURIComponent(params['data']);
        this.parsedData = JSON.parse(decodedData);
        console.log(this.parsedData);
      }
    });
  }

  goBack(): void {
    this.location.back();
  }

  ngAfterViewInit(): void {
    // Verifica se navbar è stato inizializzato correttamente
    if (this.navbar && this.navbar.nativeElement) {
      // Calcola l'altezza della navbar dopo che è stata inizializzata
      this.navbarHeight = this.navbar.nativeElement.offsetHeight;
      console.log('Navbar height: ' + this.navbarHeight);
    } else {
      console.error('Navbar non inizializzato correttamente.');
    }
  }

  confirmOrder(): void {

    this.service.createReservation(this.parsedData.resortFull.id, this.parsedData.row-1, this.parsedData.column-1, this.parsedData.persistenceTypeEnum, this.parsedData.numberOfSunbeds, this.formatDate(this.parsedData.date), this.formatDate(this.parsedData.date)).subscribe(
        (response) => {
          console.log(response);
            if(response.statusCode === 200 || response.statusCode === 201 ) {

                const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
                  data: {title: 'Prenotazione completata', message: 'Ti stiamo reindirizzando alla tua pagina profilo!', status: true},
                  height: '400px',
                  width: '400px',
                });
                // Chiudi automaticamente il dialogo dopo 2 secondi
                setTimeout(() => {
                  dialogRef.close();
                  // Imposta un ritardo di 2 secondi prima di reindirizzare
                  setTimeout(() => {
                    this.router.navigate(['/profile-page']);
                  }, 2000);
                }, 2000);
              }

        },
        (error) => {
            console.error(error);
    });
  }

  formatDate(dateString: string): string {
    const parts = dateString.split('/');
    if (parts.length !== 3) {
      // Gestione degli errori nel formato della data
      return 'null';
    }

    // Estrarre i componenti della data
    const day = parts[0];
    const month = parts[1];
    const year = parts[2];

    // Aggiungi lo zero davanti al giorno e al mese se sono inferiori a 10
    const formattedDay = (parseInt(day, 10) < 10) ? `0${day}` : day;
    const formattedMonth = (parseInt(month, 10) < 10) ? `0${month}` : month;

    // Costruire la data nel formato richiesto (AAAA-MM-DD)
    return `${year}-${formattedMonth}-${formattedDay}`;
  }



}
