export class reservation {
    resortId: number;
    resortName: string;
    date: string;
    numberOfSunbeds: number;
    persistenceTypeEnum: string;
    userUid: string;
    reservationId: number;
    dailyReservationId: number;
    reservedUmbrellaLine: number;
    reservedUmbrellaColumn: number;

    constructor(resortId: number, resortName: string, date: string, numberOfSunbeds: number, persistenceTypeEnum: string, userUid: string, reservationId: number, dailyReservationId: number, reservedUmbrellaLine: number, reservedUmbrellaColumn: number) {
        this.resortId = resortId;
        this.resortName = resortName;
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