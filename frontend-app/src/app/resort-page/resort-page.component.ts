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
  count = 0;
  selectedDate: Date = new Date(); //today's date
  isUserLoggedIn: boolean = false;

  constructor(private route: ActivatedRoute, public dialog: MatDialog, private service: BeachService, public auth: AngularFireAuth, public router: Router) {}

  ngOnInit() {
    this.loadResortPresentation();
    this.checkUserLoggedIn().subscribe(isLoggedIn => {
      this.isUserLoggedIn = isLoggedIn;
    });

  }

    increment(): void {
      if (this.count < 5) {
        this.count++;
      }
    }

    decrement(): void {
      if (this.count > 0) {
        this.count--;
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
          data: {resort: this.resortFull, people: this.count, selectedDate: this.selectedDate },
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
        console.log(event);
        this.service.enrollToEvent(event.id).subscribe((res) => {
          console.log(res);
        });
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
          data: {resort: this.resortFull.id},
          height: '300px',
          width: '600px',
        });
      } else {
        this.router.navigate(['/login-page']);
      }


    }
    
    


  }
