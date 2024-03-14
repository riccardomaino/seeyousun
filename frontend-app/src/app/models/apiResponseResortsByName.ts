import { resortPresentation } from './resortPresentation';

export interface apiResponseResortsByName {
    statusCode: number;
    success: boolean;
    message: string;
    timestamp: string;
    data:  resortPresentation[];
}