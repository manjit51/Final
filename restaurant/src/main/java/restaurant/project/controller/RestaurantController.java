package restaurant.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import restaurant.project.controller.model.RestaurantData;
import restaurant.project.controller.model.RestaurantData.EmployeeData;
import restaurant.project.controller.model.RestaurantPatronData.PatronData;
import restaurant.project.service.RestaurantService;

@RestController
@RequestMapping("/restaurant")
@Slf4j
public class RestaurantController {
	@Autowired
	private RestaurantService restaurantService;
	
	@PostMapping("/Restaurant")
	@ResponseStatus(code = HttpStatus.CREATED)
	public RestaurantData createRestaurant(@RequestBody RestaurantData restaurantData) {
		log.info("Creating restaurant {}", restaurantData);
		return restaurantService.saveRestaurant(restaurantData);
	}
	
	@GetMapping("/Restaurant/{restaurantId}")
	public RestaurantData retrieveRestaurant(@PathVariable Long restaurantId) {
		log.info("Retrieving restaurant with ID={}", restaurantId);
		
		return restaurantService.retrieveRestaurantById(restaurantId);
	}
	
	@GetMapping("/Restaurant") 
	public List<RestaurantData> retrieveAllRestaurants() {
		log.info("Retrieving all restaurants");
		return restaurantService.retrieveAllRestaurants();
	}
	
	@PutMapping("/Restaurant/{restaurantId}")
	public RestaurantData updateRestaurant(@PathVariable Long restaurantId, 
			@RequestBody RestaurantData restaurantData) {
		log.info("Updating restuarant with ID={}", restaurantId);
		restaurantData.setRestaurantId(restaurantId);
		return restaurantService.saveRestaurant(restaurantData);
	}
	
	@DeleteMapping("/Restaurant/{restaurantId}")
	public Map<String, String> deleteRestaurantById(@PathVariable Long restaurantId) {
		log.info("Deleting Restaurant with Id = " + restaurantId);
		restaurantService.deleteRestaurantId(restaurantId);
		return Map.of("Message", "Restaurant with Id = " + restaurantId + " deleted");
	}
	
	
	@PostMapping("/Restaurant/{restaurantId}/patron")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PatronData insertPatron(@PathVariable Long restaurantId, 
			@RequestBody PatronData patron) {
		log.info("Inserting patron into Restaurant Id = " + restaurantId + " with parameters {}", patron);
		return restaurantService.savePatron(restaurantId, patron);
	}
	
	@PostMapping("/Restaurant/{restaurantId}/employee")
	@ResponseStatus(code = HttpStatus.CREATED)
	public EmployeeData insertEmployee(@PathVariable Long restaurantId, 
			@RequestBody EmployeeData employee) {
		log.info("Inserting employee into Restaurant Id " + restaurantId + " with parameters {}", employee);
		return restaurantService.saveEmployee(restaurantId, employee);
	}
	
	
	
}
