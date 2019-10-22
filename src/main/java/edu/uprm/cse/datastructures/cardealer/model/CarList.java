package edu.uprm.cse.datastructures.cardealer.model;

import java.util.ArrayList;

import edu.uprm.cse.datastructures.cardealer.util.CircularSortedDoublyLinkedList;
import edu.uprm.cse.datastructures.cardealer.util.SortedList;

public class CarList {
	private static SortedList<Car> cList = new CircularSortedDoublyLinkedList<Car>(new CarComparator());

//	static {
//		//cList.add(new Car(0, "test", "test", "test", 450.00));
//		//cList.add(new Car(1, "test", "test", "test", 200.00));
//	}
	private CarList() {}

	public static SortedList<Car> getInstance(){
		return cList;
	}
	public static void resetCars() {
		cList.clear();
	}

}
