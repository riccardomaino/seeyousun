export class reservation {
    resortId: number;
    date: string;
    numberOfSunbeds: number;
    persistenceTypeEnum: string;
    userUid: string;
    reservationId: number;
    dailyReservationId: number;
    reservedUmbrellaLine: number;
    reservedUmbrellaColumn: number;

    constructor(resortId: number, date: string, numberOfSunbeds: number, persistenceTypeEnum: string, userUid: string, reservationId: number, dailyReservationId: number, reservedUmbrellaLine: number, reservedUmbrellaColumn: number) {
        this.resortId = resortId;
        this.date = date;
        this.numberOfSunbeds = numberOfSunbeds;
        this.persistenceTypeEnum = persistenceTypeEnum;
        this.userUid = userUid;
        this.reservationId = reservationId;
        this.dailyReservationId = dailyReservationId;
        this.reservedUmbrellaLine = reservedUmbrellaLine;
        this.reservedUmbrellaColumn = reservedUmbrellaColumn;
    }
}