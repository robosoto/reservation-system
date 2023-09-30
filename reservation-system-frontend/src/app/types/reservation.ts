export interface Reservation {
    reservationId: number;
    startDate: Date;
    endDate: Date;
    location: string;
    customerId: number;
    vehicleId: number;
    status: string;
}