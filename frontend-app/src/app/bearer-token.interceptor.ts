import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptorFn,
  HttpHandlerFn,
  HttpInterceptor
} from '@angular/common/http';
import { inject } from "@angular/core";
import { AngularFireAuth } from "@angular/fire/compat/auth";
import { from, lastValueFrom, Observable } from "rxjs";
import { environment } from "../environments/environment";

// needs to add this function because getting the token is async
const addBearerToken = async (
  req: HttpRequest<any>,
  next: HttpHandlerFn,
): Promise<HttpEvent<any>> => {
  const angularFireAuth = inject(AngularFireAuth);
  const firebaseUser = await angularFireAuth.currentUser;
  const token = await firebaseUser?.getIdToken();
  console.log(token);
  if (token) {
    req = req.clone({
      setHeaders: { Authorization: `Bearer ${token}` },
    });
  }
  return lastValueFrom(next(req));
};
export const bearerTokenInterceptor: HttpInterceptorFn = (req, next) => {
  // Aggiungi il token solo alle richieste che soddisfano determinate condizioni
  if (shouldAddToken(req)) {
    return from(addBearerToken(req, next));
  } else {
    return next(req);
  }
};

const shouldAddToken = (req: HttpRequest<any>): boolean => {
  // Personalizza le condizioni qui per determinare se aggiungere o meno il token
  // Esempio: aggiungi il token solo alle richieste che vanno al backend
  
  // Puoi aggiungere altre condizioni qui
  // Ad esempio, aggiungi il token solo alle richieste di tipo POST
  if (req.method === 'POST' && req.url === `${environment.backendUrl}/events/subscription`) {
    return true;
  }

  if (req.method === 'POST' && req.url === `${environment.backendUrl}/resort-reservations/book`) {
    return true;
  }

  // Aggiungi il token solo alle richieste di tipo GET verso una specifica URL
  if (req.method === 'GET' && req.url === `${environment.backendUrl}/resort-reservations/reservation-for-user`) {
    return true;
  }

  if (req.method === 'POST' && req.url === `${environment.backendUrl}/reviews`) {
    return true;
  }



  // Se nessuna condizione soddisfatta, non aggiungere il token
  return false;
};
