// resortFull
import { Review } from './review';
import { Event } from './event';
import { Service } from './service';


export class resortFull {
id: number;
name: string;
location: string;
description: string;
rating: number;
phoneNumbers: string;
timeTables: string;
photoCover: string;
photos: string[];
services: Service[];
reviews: Review[];
events: Event[];

    constructor(id: number, name: string, location: string, description: string, rating: number, phoneNumbers: string, timeTables: string, photoCover: string, photos: string[], services: Service[], reviews: Review[], events: Event[]) {
    this.id = id;
    this.name = name;
    this.location = location;
    this.description = description;
    this.rating = rating;
    this.phoneNumbers = phoneNumbers;
    this.timeTables = timeTables;
    this.photoCover = photoCover;
    this.photos = photos;
    this.services = services;
    this.reviews = reviews;
    this.events = events;
    }
}