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
  umbrellaLine: number;
  umbrellaColumn: number;
  numberOfSunbeds: number;
  isInProgress: boolean;
  isPassed: boolean;
  parking: boolean;
}

interface eventFinal {
  id: number;
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

      // Taglia il nome dell'evento se supera i 18 caratteri e aggiungi i puntini di sospensione
      let eventName = event.name.length > 18 ? event.name.slice(0, 15) + '...' : event.name;

      // Costruzione dell'oggetto EventFinal
      const eventFinal: eventFinal = {
        id: event.id,
        date: `${day}/${month}/${year}`,
        hour: `${hour}:${minutes}`,
        name: eventName, // Nome modificato se supera i 18 caratteri
        description: event.description,
        isInProgress: isInProgress,
        isPassed: isPassed,
        duration: event.duration
      };

      // Aggiungi l'evento finalizzato alla tua struttura dati finale
      this.finalEvents.push(eventFinal);
    }
  }

  sendEmail() {
    window.location.href = 'mailto:ettore.calvi@edu.unito.it,emanuele.rovaretto@edu.unito.it,riccardo.maino@edu.unito.it?subject=Richiesta%20di%20assistenza&body=Body%20goes%20here';
  }


// Funzione per formattare la data nel formato dd/MM/YY
  formatDate(dateString: string): string {
    const [year, month, day] = dateString.split('-');
    return `${day}/${month}/${year.slice(2)}`;
  }

  modifyReservation(reservations: reservation[]) {
    console.log('reservations', reservations);
    const currentDate = new Date();

    reservations.forEach(reservation => {
      const reservationDate = new Date(reservation.date);

      const isInProgress = reservationDate >= currentDate;
      const isPassed = !isInProgress;

      // Formatta la data in dd/MM/yyyy
      const formattedDate = `${reservationDate.getDate()}/${reservationDate.getMonth() + 1}/${reservationDate.getFullYear()}`;

      // Taglia il nome del resort se è più lungo di 18 caratteri e aggiungi "..."
      let resortName = reservation.resortName;
      if (resortName.length > 18) {
        resortName = resortName.substring(0, 15) + '...';
      }

      // Calcola il numero dell'ombrellone
      //const umbrellaNumber = reservation.reservedUmbrellaLine * (numeroMassimoDiColonne) + reservation.reservedUmbrellaColumn + 1;
      const umbrellaNumber = 1;

      this.finalReservations.push({
        resName: resortName,
        date: formattedDate,
        umbrellaLine: reservation.reservedUmbrellaLine + 1,
        umbrellaColumn: reservation.reservedUmbrellaColumn + 1,
        numberOfSunbeds: reservation.numberOfSunbeds,
        isInProgress: isInProgress,
        isPassed: isPassed,
        parking: false // Per ora impostiamo sempre a false
      });
    });
  }


  unsubscribeEvent(id: number) {
    this.service.unsubscribeToEvent(id).subscribe(
        (response) => {
          console.log(response);
          if (response.statusCode === 200 || response.statusCode === 201) {
            // Rimuovi l'evento dalla lista finale
            this.finalEvents = this.finalEvents.filter(event => event.id !== id);
          }
        },
        (error) => {
          console.error(error);
        }
    );

  }



logOut() {
      this.auth.signOut();
      this.router.navigate(['']);
    }
}
