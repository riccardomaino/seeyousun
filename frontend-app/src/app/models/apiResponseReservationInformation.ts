import { BeachSpec } from "./beachSpec"; 

export interface apiResponseReservationInformation {
    statusCode: number;
    success: boolean;
    message: string;
    timestamp: string;
    data: BeachSpec;
}