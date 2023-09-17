export interface Reservation {
    id: number;
    startDate: Date;
    endDate: Date;
    location: string;
    customerId: number;
    vehicleId: number;
}