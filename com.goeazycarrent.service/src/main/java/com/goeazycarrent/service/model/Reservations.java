package com.goeazycarrent.service.model;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Reservations {

	@Id
	@Column(name = "id")
	private String reservationId;
	
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
	
	@Column(name = "status")
	private String status;
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(customerId, endDate, location, reservationId, startEnd, status, vehicleId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservations other = (Reservations) obj;
		return Objects.equals(customerId, other.customerId) && Objects.equals(endDate, other.endDate)
				&& Objects.equals(location, other.location) && Objects.equals(reservationId, other.reservationId)
				&& Objects.equals(startEnd, other.startEnd) && Objects.equals(status, other.status)
				&& Objects.equals(vehicleId, other.vehicleId);
	}
	
	
}
