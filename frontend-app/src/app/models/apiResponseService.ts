import { Service } from './service';
export interface apiResponseService {
    statusCode: number;
    success: boolean;
    message: string;
    timestamp: string;
    data: Service[]; 
}