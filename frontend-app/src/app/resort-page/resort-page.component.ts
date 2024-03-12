import { Component, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { resortPresentation } from '../models/resortPresentation';
import { resortFull } from '../models/resortFull';
import { BeachService } from '../services/beach.service';
import { MatDialog } from '@angular/material/dialog';
import { BookDialogComponent } from '../book-dialog/book-dialog.component';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import { Event } from '../models/event';
import { ReviewDialogComponent } from '../review-dialog/review-dialog.component';
import {ProgressSpinnerMode, MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {ConfirmationDialogComponent} from "../confirmation-dialog/confirmation-dialog.component";
@Component({
  selector: 'app-resort-page',
  templateUrl: './resort-page.component.html',
  styleUrls: ['./resort-page.component.scss'],
  encapsulation: ViewEncapsulation.None  // Disabilita l'incapsulamento delle viste
})
export class ResortPageComponent {
  id: number = 0;
  resortPresentation: resortPresentation[] = [];
  resortFull: resortFull = {} as resortFull;
  people = 1; //people
  selectedDate: Date = new Date(); //today's date
  isUserLoggedIn: boolean = false;
  isLoading: boolean = true;
  isLoadingEvents: boolean = false;

  constructor(private route: ActivatedRoute, public dialog: MatDialog, private service: BeachService, public auth: AngularFireAuth, public router: Router) {}

  ngOnInit() {
    setTimeout(() => {
      // Dopo aver caricato i dati, imposta isLoading su false
      this.isLoading = false;
    }, 1000); // Simula un ritardo di 2 secondi per il caricamento dei dati

    this.loadResortPresentation();
    this.checkUserLoggedIn().subscribe(isLoggedIn => {
      this.isUserLoggedIn = isLoggedIn;
    });

  }

    increment(): void {
      if (this.people < 5) {
        this.people++;
      }
    }

    decrement(): void {
      if (this.people > 0) {
        this.people--;
      }
    }

    setDate(event: MatDatepickerInputEvent<Date>) {
      this.selectedDate = event.value as Date;
    }
    
    loadResortPresentation(): void {
      this.route.queryParams.subscribe(params => {
        this.id = params['id'];
        console.log(this.id);
        this.loadResortFull();
      });
    }

    loadResortFull(): void {
      this.service.getResortFull(this.id).subscribe((resortFull) => {
        this.resortFull = resortFull.data;
        console.log(this.resortFull);
      });
    }

    openDialog(): void {
      if(!this.isUserLoggedIn) {
        this.router.navigate(['/login-page']);
      } else {
        const dialogRef = this.dialog.open(BookDialogComponent, {
          data: {resort: this.resortFull, people: this.people, selectedDate: this.selectedDate },
          height: '500px',
          width: '800px',
        });
    
        dialogRef.afterClosed().subscribe(result => {
          console.log(result);
          //TODO: filter beaches
        });
      }
    }

    checkUserLoggedIn(): Observable<boolean> {
      return new Observable<boolean>(observer => {
        this.auth.authState.subscribe(user => {
          if (user) {
            // User is logged in
            observer.next(true);
          } else {
            // User is not logged in
            observer.next(false);
          }
          observer.complete();
        });
      });
    }
    
    enrollToEvent(event: Event): void {
      if(!this.isUserLoggedIn) {
        this.router.navigate(['/login-page']);
      } else {
        this.isLoadingEvents = true;
        setTimeout(() => {
          // Dopo aver caricato i dati, imposta isLoading su false
          this.isLoading = false;
        }, 1000); // Simula un ritardo di 2 secondi per il caricamento dei dati
        this.service.enrollToEvent(event.id).subscribe(
            (res) => {
              const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
                data: {title: 'Ci siamo! :)', message:'Ti sei iscritto all\'evento con successo.', status: true},
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
            },
            (error) => {
              console.error("Errore durante l'abbonamento all'evento:", error.error.message);
              const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
                data: {title: 'Ops! :(', message: error.error.message, status: false},
                height: '400px',
                width: '400px',
              });
              // Chiudi automaticamente il dialogo dopo 2 secondi
              setTimeout(() => {
                dialogRef.close();
              }, 2000);
            }
        );
      }
    }

    getStarArray(rating: number): any[] {
      return new Array(rating);
    }

    formatDate(dateString: string): string {
      const options: Intl.DateTimeFormatOptions = { day: 'numeric', month: 'long', year: 'numeric' };
      const date = new Date(dateString);
      return date.toLocaleDateString('it-IT', options);
    }

    onTextareaInput(event: any): void {
      if(this.isUserLoggedIn) {
        const dialogRef = this.dialog.open(ReviewDialogComponent, {
          data: this.resortFull.id,
          height: '350px',
          width: '600px',
        });
        dialogRef.afterClosed().subscribe(() => {
          this.loadResortFull(); // Aggiorna la pagina per visualizzare la nuova recensione
          // Rimuove il focus dall'elemento textarea
          const textarea = document.getElementById('yourTextareaId') as HTMLTextAreaElement;
          if (textarea) {
            textarea.blur();
          }
        });
      } else {
        this.router.navigate(['/login-page']);
      }


    }
    
    


  }
