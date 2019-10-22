package edu.uprm.cse.datastructures.cardealer.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
// Car class pojo
@XmlRootElement (name = "car")
public class Car {
	@XmlElement
	private long carId;
	@XmlElement
	private String carBrand;
	@XmlElement
	private String carModel;
	@XmlElement
	private String carModelOption;
	@XmlElement
	private double carPrice;

	public long getCarId() {
		return carId;
	}
	public String getCarBrand() {
		return carBrand;
	}
	public String getCarModel() {
		return carModel;
	}
	public String getCarModelOption() {
		return carModelOption;
	}
	public double getCarPrice() {
		return carPrice;
	}
	
	public Car() {
		
	}
	public Car(long carId, String carBrand, String carModel, String carModelOption, double carPrice) {
		super();
		this.carId = carId;
		this.carBrand = carBrand;
		this.carModel = carModel;
		this.carModelOption = carModelOption;
		this.carPrice = carPrice;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carBrand == null) ? 0 : carBrand.hashCode());
		result = prime * result + (int) (carId ^ (carId >>> 32));
		result = prime * result + ((carModel == null) ? 0 : carModel.hashCode());
		result = prime * result + ((carModelOption == null) ? 0 : carModelOption.hashCode());
		long temp;
		temp = Double.doubleToLongBits(carPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (carBrand == null) {
			if (other.carBrand != null)
				return false;
		} else if (!carBrand.equals(other.carBrand))
			return false;
		if (carId != other.carId)
			return false;
		if (carModel == null) {
			if (other.carModel != null)
				return false;
		} else if (!carModel.equals(other.carModel))
			return false;
		if (carModelOption == null) {
			if (other.carModelOption != null)
				return false;
		} else if (!carModelOption.equals(other.carModelOption))
			return false;
		if (Double.doubleToLongBits(carPrice) != Double.doubleToLongBits(other.carPrice))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Car [carId=" + carId + ", carBrand=" + carBrand + ", carModel=" + carModel + ", carModelOption="
				+ carModelOption + ", carPrice=" + carPrice + "]";
	}
	
}
