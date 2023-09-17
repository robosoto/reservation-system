package com.goeazycarrent.service.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Reservations {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reservationId;
	
	@Column(name = "start_date")
	private Date startEnd;
	
	@Column(name = "end_date")
	private Date endDate;
	
	@Column(name = "customer_id")
	private Integer customerId;
	
	@Column(name = "vehicleId")
	private Integer vehicleId;
	
	@Column(name = "location")
	private String location;
	
	
	/**
	 * @return the reservationId
	 */
	public Integer getReservationId() {
		return reservationId;
	}
	/**
	 * @param reservationId the reservationId to set
	 */
	public void setReservationId(Integer reservationId) {
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
	/**
	 * @return the startEnd
	 */
	public Date getStartEnd() {
		return startEnd;
	}
	/**
	 * @param startEnd the startEnd to set
	 */
	public void setStartEnd(Date startEnd) {
		this.startEnd = startEnd;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	
	
}
