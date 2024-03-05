
import { Event  } from './event';

export interface apiResponseEventsForUser {
    statusCode: number;
    success: boolean;
    message: string;
    timestamp: string;
    data: Event[];
}