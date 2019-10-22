package edu.uprm.cse.datastructures.cardealer;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
import jersey.repackaged.com.google.common.base.Optional;
import jersey.repackaged.com.google.common.base.Predicate;

@Path("/cars")
public class CarManager {

	private final SortedList<Car> cList = CarList.getInstance();

	//only for trouble shooting
	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public Car tester() {
		return cList.first();
	}

	@GET
	@Path("")
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
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Car getCar(@PathParam("id") long id){
		for(Car x: cList) {
			if(x.getCarId() == id)
				return x;
		}
		Response.status(Response.Status.NOT_FOUND).build();
		throw new NotFoundException(new JsonError("Error", "Customer " + id + " not found"));
	}  

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCar(Car car){
		cList.add(car);
		return Response.status(201).build();
	}   

	@DELETE
	@Path("/{id}/delete")
	public Response deleteCar(@PathParam("id") long id){
		Car car = null;
		for(Car x: cList) {
			if(x.getCarId() == id)
				car = x;
		}
		if(cList.remove(car)) {
			return Response.status(Response.Status.OK).build();
		}
		else {
			throw new NotFoundException(new JsonError("Error", "Customer " + id + " not found"));
		}
	}
	//Update method not delevop yet
	@PUT
	@Path("{id}/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCar(Car car){
		boolean found = false;
		found = cList.remove(this.getCar(car.getCarId()));
		if (found) {
			cList.add(car);
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();      
		}
	}                                  

}      



