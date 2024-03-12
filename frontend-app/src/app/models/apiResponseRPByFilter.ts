import { resortPresentation } from './resortPresentation';

export interface apiResponseRPByFilter {
  statusCode: number;
  success: boolean;
  message: string;
  timestamp: string;
  data: resortPresentation[];
}