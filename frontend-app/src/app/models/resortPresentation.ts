// resortPresenation.model.ts
export class resortPresentation {
  id: number;
  name: string;
  location: string;
  description: string;
  rating: number;
  photoCover: string;
  // Aggiungi altre proprietà necessarie

  constructor(id: number, name: string, location: string, description: string, rating: number, photoCover: string) {
    this.id = id;
    this.name = name;
    this.location = location;
    this.description = description;
    this.rating = rating;
    this.photoCover = photoCover;
    // Inizializza altre proprietà se necessario
  }
}
