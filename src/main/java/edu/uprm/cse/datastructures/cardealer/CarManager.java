package edu.uprm.cse.datastructures.cardealer;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.uprm.cse.datastructures.cardealer.model.Car;
import edu.uprm.cse.datastructures.cardealer.model.CarComparator;
import edu.uprm.cse.datastructures.cardealer.util.CircularSortedDoublyLinkedList;
import edu.uprm.cse.datastructures.cardealer.util.SortedList;

@Path("Car")
public class CarManager {

	private final SortedList<Car> cList = new CircularSortedDoublyLinkedList<Car>(new CarComparator());
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Car[] getAllCars() {
		cList.add(new Car(1, "test", "test", "test", 200.99));
		return cList.toArray(Car.class);
	}    

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Car getCustomer(@PathParam("id") long id){
		for(Car x: cList) {
			if(x.getCarId() == id)
				return x;
		}
		String error = "Error404 Car" + id + " Not found";
		throw new NotFoundException(error);
	}  

}

