package edu.uprm.cse.datastructures.cardealer.model;

import java.util.Comparator;

public class CarComparator implements Comparator<Car>{

	public CarComparator() {

	}

	@Override
	public int compare(Car arg0, Car arg1) {
		String Car1 = arg0.getCarBrand();
		String Car2 = arg1.getCarBrand();
		if(Car1.compareTo(Car2) == 0) {
			Car1 = arg0.getCarModel();
			Car2 = arg1.getCarModel();

			if(Car1.compareTo(Car2) == 0) {
				Car1 = arg0.getCarModelOption();
				Car2 = arg1.getCarModelOption();

				if(Car1.compareTo(Car2) == 0) {
					Double Car11 = arg0.getCarPrice();
					Double Car22 = arg1.getCarPrice();

					if(Car11.equals(Car22))
						return 0;
					else
						return Car11.compareTo(Car22);
				}
				else {
					return Car1.compareTo(Car2);
				}
			}
			else {
				return	Car1.compareTo(Car2);
			}
		}
		else {
			return	Car1.compareTo(Car2);
		}
	}

}
