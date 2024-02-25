import { resortPresentation } from './resortPresentation';

export interface apiResponseResortPresentation {
    statusCode: number;
    success: boolean;
    message: string;
    timestamp: string;
    data: {
        popularResorts: resortPresentation[];
        pageNr: number;
        pageSize: number;
        totalElements: number;
        totalPages: number;
        last: boolean;
    } 
}