package edu.uprm.cse.datastructures.cardealer;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.uprm.cse.datastructures.cardealer.model.Car;
import edu.uprm.cse.datastructures.cardealer.model.CarComparator;
import edu.uprm.cse.datastructures.cardealer.model.CarList;
import edu.uprm.cse.datastructures.cardealer.util.CircularSortedDoublyLinkedList;
import edu.uprm.cse.datastructures.cardealer.util.SortedList;
import jersey.repackaged.com.google.common.base.Predicate;

@Path("/cars")
public class CarManager {

	private final SortedList<Car> cList = CarList.getInstance();

	//only for trouble shooting
	//	@GET
	//	@Path("/test")
	//	@Produces(MediaType.APPLICATION_JSON)
	//	public Car tester() {
	//		return cList.first();
	//	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Car[] getAllCars() {
		//converts cList to Array
		Car[] cars = new Car[cList.size()];
		int c = 0;
		for(Car x: cList) {
			cars[c] = x;
			c++;
		}
		return cars;
	}    

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Car getCustomer(@PathParam("id") long id){
		for(Car x: cList) {
			if(x.getCarId() == id)
				return x;
		}
		throw new NotFoundException(new JsonError("Error", "Customer " + id + " not found"));
	}  

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCustomer(Car car){
		cList.add(car);
		return Response.status(201).build();
	}   

	@DELETE
	@Path("/remove/{id}")
	public void deleteCustomer(@PathParam("id") long id){
		Car car = null;
		for(Car x: cList) {
			if(x.getCarId() == id)
				car = x;
		}
		if(!cList.remove(car)) {
			throw new NotFoundException(new JsonError("Error", "Customer " + id + " not found"));
		}
	}
}      



