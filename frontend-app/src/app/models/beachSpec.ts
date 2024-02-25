export class BeachSpec {
    dimension: {
        totalUmbrellaLine: number;
        totalUmbrellaColumn: number;
    };
    priceList: {
        umbrellaPrice: number[];
        sunbedPrice: number;
    };
    reservedUmbrella: reservedUmbrella[];

    constructor(totalUmbrellaLine: number, totalUmbrellaColumn: number, priceList: { umbrellaPrice: number[]; sunbedPrice: number; }, reservedUmbrella: reservedUmbrella[]) {
        this.dimension = {
            totalUmbrellaLine: totalUmbrellaLine,
            totalUmbrellaColumn: totalUmbrellaColumn
        };
        this.priceList = priceList;
        this.reservedUmbrella = reservedUmbrella;
    }
}



export class reservedUmbrella {
    reservedUmbrellaLine: number;
    reservedUmbrellaColumn: number;
    persistenceTypeEnum: string;

    constructor(reservedUmbrellaLine: number, reservedUmbrellaColumn: number, persistenceTypeEnum: string) {
        this.reservedUmbrellaLine = reservedUmbrellaLine;
        this.reservedUmbrellaColumn = reservedUmbrellaColumn;
        this.persistenceTypeEnum = persistenceTypeEnum;
        
    }
}

