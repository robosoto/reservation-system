package com.goeazycarrent.service.dto;

public class ReservationRequestDto {
	
	private String reservationId;
	private String pickupDate;
	private String dropoffDate;
	private String location;
	private Integer customerId;
	private Integer vehicleId;
	
	    
	/**
	 * @return the pickupDate
	 */
	public String getPickupDate() {
		return pickupDate;
	}
	/**
	 * @param pickupDate the pickupDate to set
	 */
	public void setPickupDate(String pickupDate) {
		this.pickupDate = pickupDate;
	}
	/**
	 * @return the dropoffDate
	 */
	public String getDropoffDate() {
		return dropoffDate;
	}
	/**
	 * @param dropoffDate the dropoffDate to set
	 */
	public void setDropoffDate(String dropoffDate) {
		this.dropoffDate = dropoffDate;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the reservationId
	 */
	public String getReservationId() {
		return reservationId;
	}
	/**
	 * @param reservationId the reservationId to set
	 */
	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}
	/**
	 * @return the customerId
	 */
	public Integer getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	/**
	 * @return the vehicleId
	 */
	public Integer getVehicleId() {
		return vehicleId;
	}
	/**
	 * @param vehicleId the vehicleId to set
	 */
	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	

}
