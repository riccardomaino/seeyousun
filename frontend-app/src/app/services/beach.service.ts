// beach.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { resortPresentation } from '../models/resortPresentation';
import { Service } from '../models/service';
import { resortFull } from '../models/resortFull';
import { apiResponseService } from '../models/apiResponseService';
import { apiResponseResortPresentation } from '../models/apiResponseResortPresentation';
import { apiResponseResortFullByID } from '../models/apiResponseResortFullByID';
import { apiResponseReservationInformation } from '../models/apiResponseReservationInformation';
import { environment } from 'src/environments/environment';
import {apiResponseReservationFUsr} from "../models/apiResponseReservationFUsr";
import {apiResponseEventsForUser} from "../models/apiResponseEventsForUser";

@Injectable({
  providedIn: 'root',
})
export class BeachService {
  private baseUrl = `${environment.backendUrl}/`; 
  
  constructor(private http: HttpClient) {}

  getResortPresentation(): Observable<apiResponseResortPresentation> {
    return this.http.get<apiResponseResortPresentation>(this.baseUrl + 'resorts/popular?pageNr=0&pageSize=10');
  }

  getResortFull(id: number): Observable<apiResponseResortFullByID> {
    return this.http.get<apiResponseResortFullByID>(this.baseUrl + "resorts/" + id);
  }

  getServices(): Observable<apiResponseService> {
    return this.http.get<apiResponseService>(this.baseUrl + 'resorts/services');
  }

  getReservationInformation(id: number, date: string): Observable<apiResponseReservationInformation> {
    return this.http.get<apiResponseReservationInformation>(this.baseUrl + 'resorts/information/' + id + '?date=' + date);
  }

  getReservationForUser(): Observable<apiResponseReservationFUsr> {
    console.log('getReservationForUser');
    return this.http.get<apiResponseReservationFUsr>(this.baseUrl + 'resort-reservations/reservation-for-user');
  }

  getEventsForUser(): Observable<apiResponseEventsForUser> {
    console.log('getReservationForUser');
    return this.http.get<apiResponseEventsForUser>(this.baseUrl + 'events/events-for-user');
  }

  getResortPresentationByFilter(location: string, services: string[]): Observable<apiResponseResortPresentation> {
    let url = `${this.baseUrl}resorts/by-location-and-services?`;

    if (location) {
      url += `location=${location}`;
    }

    if (services && services.length > 0) {
      if (location) {
        url += '&';
      }
      url += `services=${services.join(',')}`;
    }

    return this.http.get<apiResponseResortPresentation>(url);
  }

  enrollToEvent(id: number): Observable<any> {
    return this.http.post(this.baseUrl + 'events/subscription', { eventId: id });
  }

  createReservation(resortId: number, reservedUmbrellaRow: number, reservedUmbrellaColumn: number, persistenceTypeEnum: string, numberOfSunbeds: number, initialDate: string, finalDate: string,): Observable<any> {
    console.log({resortId: resortId, reservedUmbrellaLine: reservedUmbrellaRow, reservedUmbrellaColumn: reservedUmbrellaColumn, persistenceTypeEnum: persistenceTypeEnum, numberOfSunbeds: numberOfSunbeds, initialDate: initialDate, finalDate: finalDate});
    return this.http.post(this.baseUrl + 'resort-reservations/book', {resortId: resortId, reservedUmbrellaLine: reservedUmbrellaRow, reservedUmbrellaColumn: reservedUmbrellaColumn, persistenceTypeEnum: persistenceTypeEnum, numberOfSunbeds: numberOfSunbeds, initialDate: initialDate, finalDate: finalDate});
  }

  createReview(title: string, bodyReview: string, rating: number, date: string, resortId: number): Observable<any> {
    console.log({title: title, bodyReview: bodyReview, rating: rating, date: date, resortId: resortId});
    return this.http.post(this.baseUrl + 'reviews', {title: title, bodyReview: bodyReview, rating: rating, date: date, resortId: resortId});
  }







}
