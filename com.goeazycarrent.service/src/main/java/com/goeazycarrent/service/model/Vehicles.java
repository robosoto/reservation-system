package com.goeazycarrent.service.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity 
public class Vehicles {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer vehicleId;
	
	@Column(name = "make")
	private String make;
	
	@Column(name = "model")
	private String model;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "Location")
	private String location;
	
	@Column(name = "price_per_day")
	private int pricePerDay;

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
	 * @return the make
	 */
	public String getMake() {
		return make;
	}
	/**
	 * @param make the make to set
	 */
	public void setMake(String make) {
		this.make = make;
	}
	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}
	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @return the price per day
	 */
	public int getPricePerDay() {
		return pricePerDay;
	}
	/**
	 * @param pricePerDay the price per day to set
	 */
	public void setPricePerDay(int pricePerDay) {
		this.pricePerDay = pricePerDay;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(location, make, model, pricePerDay, type, vehicleId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicles other = (Vehicles) obj;
		return Objects.equals(location, other.location) && Objects.equals(make, other.make)
				&& Objects.equals(model, other.model) && pricePerDay == other.pricePerDay
				&& Objects.equals(type, other.type) && Objects.equals(vehicleId, other.vehicleId);
	}

}
