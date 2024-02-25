import { resortFull } from './resortFull';

export interface apiResponseResortFullByID {
    statusCode: number;
    success: boolean;
    message: string;
    timestamp: string;
    data: resortFull;
}