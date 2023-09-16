package com.goeazycarrent.service.dto;

import java.util.Date;

public class ReservationRequestDto {
	
	private Date pickupDate;
	private Date dropoffDate;
	private String location;
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
		return dropoffDate;
	}
	/**
	 * @param dropoffDate the dropoffDate to set
	 */
	public void setDropoffDate(Date dropoffDate) {
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

}
