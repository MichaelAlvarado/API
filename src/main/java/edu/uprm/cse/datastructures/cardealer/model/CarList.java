package edu.uprm.cse.datastructures.cardealer.model;

import java.util.ArrayList;

import edu.uprm.cse.datastructures.cardealer.util.CircularSortedDoublyLinkedList;
import edu.uprm.cse.datastructures.cardealer.util.SortedList;

public class CarList {
private static SortedList<Car> cList = new CircularSortedDoublyLinkedList<Car>(new CarComparator());
private static final ArrayList<Car> AList = new ArrayList<Car>();

static {
	AList.add(new Car(0,"test", "test", "test", 2000.00));

	
	cList.add(new Car(0, "test", "test", "test", 450.00));
	cList.add(new Car(1, "test", "test", "test", 200.00));
}
private CarList() {}
public static SortedList<Car> getInstance(){
	return cList;
}
public static ArrayList<Car> getAInstance(){
	return AList;
}
public void resetCars() {
	this.cList =  new CircularSortedDoublyLinkedList<Car>(new CarComparator());
	
}

}
