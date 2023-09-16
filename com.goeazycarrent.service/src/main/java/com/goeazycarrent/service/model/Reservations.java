package com.goeazycarrent.service.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Reservations {

	@Id
	@Column(name = "id")
	private String reservationId;
	
	@Id
	@Column(name = "pickupDate")
	private Date pickupDate;
	
	@Id
	@Column(name = "DropoffDate")
	private Date DropoffDate;
	
	@Id
	@Column(name = "Customer_id")
	private Integer CustomerId;
	
	@Id
	@Column(name = "vehicleId")
	private Integer vehicleId;
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
	 * @return the pickupDate
	 */
	public Date getPickupDate() {
		return pickupDate;
	}
	/**
	 * @param pickupDate the pickupDate to set
	 */
	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}
	/**
	 * @return the dropoffDate
	 */
	public Date getDropoffDate() {
		return DropoffDate;
	}
	/**
	 * @param dropoffDate the dropoffDate to set
	 */
	public void setDropoffDate(Date dropoffDate) {
		DropoffDate = dropoffDate;
	}
	/**
	 * @return the customerId
	 */
	public Integer getCustomerId() {
		return CustomerId;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Integer customerId) {
		CustomerId = customerId;
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
