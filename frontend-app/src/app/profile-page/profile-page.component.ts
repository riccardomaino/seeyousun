import { Component } from '@angular/core';
import { AngularFireAuth } from "@angular/fire/compat/auth";
import { Router } from '@angular/router';
import {BeachService} from "../services/beach.service";
import { reservation } from '../models/reservation';
import { Event } from '../models/event';

//create interface for reservation
interface reservationsFinal {
  resName: string;
  date: string;
  umbrella: number;
  numberOfSunbeds: number;
  isInProgress: boolean;
  isPassed: boolean;
  parking: boolean;
}

interface eventFinal {
  date: string;
  hour: string;
  name: string;
  description: string;
  isInProgress: boolean;
  isPassed: boolean;
  duration: number;
}

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.scss']
})
export class ProfilePageComponent {
  constructor(public auth: AngularFireAuth, public router: Router, public service: BeachService) {}
  isUserLogged: boolean = false;
  userName: string = '';
  userMail: string = '';
  reservations: reservation[] = [];
  events: Event[] = [];
  finalEvents: eventFinal[] = [];
  finalReservations: reservationsFinal[] = [];

  ngOnInit() {
    this.auth.authState.subscribe(user => {
      this.isUserLogged = !!user;
      this.userName = user?.displayName ?? '';
      this.userMail = user?.email ?? '';
      this.loadUserData();
    });
  }

  loadUserData() {
    if (this.isUserLogged) {
      this.service.getReservationForUser().subscribe(
          (reservation) => {
            console.log('reservation', reservation);
            this.reservations = reservation.data;
            this.modifyReservation(this.reservations);
          },
          (error) => {
            console.error('Si è verificato un errore durante il recupero dei dati:', error);
            // Puoi aggiungere qui la logica per gestire l'errore, ad esempio visualizzando un messaggio all'utente
          }
      );

      this.service.getEventsForUser().subscribe(
            (events) => {
                console.log('events', events);
                this.events = events.data;
                this.modifyEvents(this.events);
            },
            (error) => {
                console.error('Si è verificato un errore durante il recupero dei dati:', error);
                // Puoi aggiungere qui la logica per gestire l'errore, ad esempio visualizzando un messaggio all'utente
            }
        );
    }
  }

  modifyEvents(events: Event[]) {
    for (let event of events) {
      // Converti la stringa di data in un oggetto Date
      const initialDateTime = new Date(event.initialDateTime);

      // Ottieni la data e l'ora attuali
      const currentDateTime = new Date();

      // Verifica se l'evento è in corso o passato
      const isInProgress = initialDateTime >= currentDateTime ;
      const isPassed = !isInProgress;

      // Ottieni le parti di anno, mese e giorno dalla data
      const year = initialDateTime.getFullYear().toString().slice(-2);
      const month = ('0' + (initialDateTime.getMonth() + 1)).slice(-2);
      const day = ('0' + initialDateTime.getDate()).slice(-2);

      // Ottieni l'ora e i minuti dalla data
      const hour = ('0' + initialDateTime.getHours()).slice(-2);
      const minutes = ('0' + initialDateTime.getMinutes()).slice(-2);

      // Costruzione dell'oggetto EventFinal
      const eventFinal: eventFinal = {
        date: `${day}/${month}/${year}`,
        hour: `${hour}:${minutes}`,
        name: event.name,
        description: event.description,
        isInProgress: isInProgress,
        isPassed: isPassed,
        duration: event.duration
      };

      // Aggiungi l'evento finalizzato alla tua struttura dati finale
      this.finalEvents.push(eventFinal);
    }
  }



// Funzione per formattare la data nel formato dd/MM/YY
  formatDate(dateString: string): string {
    const [year, month, day] = dateString.split('-');
    return `${day}/${month}/${year.slice(2)}`;
  }

  modifyReservation(reservations: reservation[]) {
    const currentDate = new Date();

    reservations.forEach(reservation => {
      const reservationDate = new Date(reservation.date);

      const isInProgress = reservationDate >= currentDate;
      const isPassed = !isInProgress;

      // Formatta la data in dd/MM/yyyy
      const formattedDate = `${reservationDate.getDate()}/${reservationDate.getMonth() + 1}/${reservationDate.getFullYear()}`;

      // Calcola il numero dell'ombrellone
      //const umbrellaNumber = reservation.reservedUmbrellaLine * (numeroMassimoDiColonne) + reservation.reservedUmbrellaColumn + 1;
      const umbrellaNumber = 1;

      this.finalReservations.push({
        resName: "frescura", // Per ora lasciamo sempre "frescura" come nome
        date: formattedDate,
        umbrella: umbrellaNumber,
        numberOfSunbeds: reservation.numberOfSunbeds,
        isInProgress: isInProgress,
        isPassed: isPassed,
        parking: false // Per ora impostiamo sempre a false
      });
    });
  }



logOut() {
      this.auth.signOut();
      this.router.navigate(['']);
    }
}
