
import { reservation } from './reservation';

export interface apiResponseReservationFUsr {
    statusCode: number;
    success: boolean;
    message: string;
    timestamp: string;
    data: reservation[];
}