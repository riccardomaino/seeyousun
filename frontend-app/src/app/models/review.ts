export class Review {
    id: number;
    username: string;
    title: string;
    bodyReview: string;
    rating: number;
    date: string;

    // Aggiungi altre proprietà necessarie

    constructor(id: number, username: string, title: string, bodyReview: string, rating: number, date: string) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.bodyReview = bodyReview;
        this.rating = rating;
        this.date = date;
    }
}