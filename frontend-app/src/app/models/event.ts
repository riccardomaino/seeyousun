export class Event {
    id: number;
    name: string;
    intialDate: Date;
    finalDate: Date;
    description: string;
    duration: number;
    isFull: boolean;

    constructor(id: number, name: string, intialDate: Date, finalDate: Date, description: string, duration: number, isFull: boolean) {
        this.id = id;
        this.name = name;
        this.intialDate = intialDate;
        this.finalDate = finalDate;
        this.description = description;
        this.duration = duration;
        this.isFull = isFull;
    }
}