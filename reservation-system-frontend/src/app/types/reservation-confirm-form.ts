export interface ReservationConfirmForm {
    email:string |null| undefined;
    reservationId:string |null| undefined;
    pickupDate: string |null| undefined;
    dropoffDate: string | null|undefined;
    location: string |null| undefined;
    customerId: number;
    vehicleId: number;
}