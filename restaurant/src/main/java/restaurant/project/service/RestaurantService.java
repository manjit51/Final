package restaurant.project.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import restaurant.project.controller.model.RestaurantData;
import restaurant.project.controller.model.RestaurantData.EmployeeData;
import restaurant.project.controller.model.RestaurantPatronData.PatronData;
import restaurant.project.dao.EmployeeDao;
import restaurant.project.dao.PatronDao;
import restaurant.project.dao.RestaurantDao;
import restaurant.project.entity.Employee;
import restaurant.project.entity.Patron;
import restaurant.project.entity.Restaurant;

@Service
public class RestaurantService {
	
	@Autowired
	private RestaurantDao restaurantDao;
	
	@Autowired
	private PatronDao patronDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Transactional(readOnly = false)
	public RestaurantData saveRestaurant(RestaurantData restaurantData) {
		Restaurant restaurant = restaurantData.toRestaurant();
		return new RestaurantData(restaurantDao.save(restaurant));
	}
	
	@Transactional(readOnly = true)
	public RestaurantData retrieveRestaurantById(Long restaurantId) {
		Restaurant restaurant = findRestaurantById(restaurantId);
		return new RestaurantData(restaurant);
	}
	private Restaurant findRestaurantById(Long restaurantId) {
		return restaurantDao.findById(restaurantId)
				.orElseThrow(() -> new NoSuchElementException(
						"Restaurant with ID=" + restaurantId + " was not found"));
	}
	
	@Transactional(readOnly = true)
	public List<RestaurantData> retrieveAllRestaurants() {
		List<Restaurant> allRestaurants = restaurantDao.findAll();
		
		List<RestaurantData> result = new LinkedList<>();
		for(Restaurant restaurant : allRestaurants) {
			RestaurantData rd = new RestaurantData(restaurant);
			
			rd.getEmployees().clear();
			rd.getPatrons().clear();
			
			result.add(rd);
		}
		return result;
	}
	
	@Transactional(readOnly = false)
	public PatronData savePatron(Long restaurantId, PatronData patron) {
		Restaurant newRestaurant = findRestaurantById(restaurantId);
		Patron newPatron = findOrCreatePatron(restaurantId, patron.getPatronId());
		copyPatronFields(newPatron, patron);
		newPatron.getRestaurants().add(newRestaurant);
		newRestaurant.getPatrons().add(newPatron);
		patronDao.save(newPatron);
		Patron savePatron = patronDao.save(newPatron);
		return new PatronData(savePatron);
	}

	private Patron findOrCreatePatron(Long restaurantId, Long patronId) {
		if(patronId == null) {
			return new Patron();
		} else {
			return findPatronById(restaurantId, patronId);
		}
	}

	private Patron findPatronById(Long restaurantId, Long patronId) {
		Patron pat = patronDao.findById(patronId)
				.orElseThrow(() -> new NoSuchElementException (
						"Patron with Id = " + patronId + "does not exist"));
		Object check[] = pat.getRestaurants().toArray();
		boolean checkFlag = false;
		for(int i = 0; i < check.length; i++) {
			if(check[i].equals(restaurantId)) {
				checkFlag = true;
			}
		}
		
		if(checkFlag) {
			return pat;
		} else {
			throw new IllegalArgumentException("Patron/Restaurant Id Mismatch");
		}
	}

	private void copyPatronFields(Patron newPatron, PatronData patron) {
		newPatron.setPatronFirstName(patron.getPatronFirstName());
		newPatron.setPatronLastName(patron.getPatronLastName());
		newPatron.setPatronFoodPreference(patron.getPatronFoodPreference());
	}
	
	@Transactional(readOnly = false)
	public EmployeeData saveEmployee(Long restaurantId, EmployeeData employee) {
		Restaurant newRestaurant = findRestaurantById(restaurantId);
		Employee newEmployee = findOrCreateEmployee(restaurantId, employee.getEmployeeId());
		copyEmployeeFields(newEmployee, employee);
		newEmployee.setRestaurant(newRestaurant);
		newRestaurant.getEmployees().add(newEmployee);
		Employee saveEmployee = employeeDao.save(newEmployee);
		return new EmployeeData(saveEmployee);
	}

	private void copyEmployeeFields(Employee newEmployee, EmployeeData employee) {
		newEmployee.setEmployeeId(employee.getEmployeeId());
		newEmployee.setEmployeeFirstName(employee.getEmployeeFirstName());
		newEmployee.setEmployeeLastName(employee.getEmployeeLastName());
		newEmployee.setEmployeeJobTitle(employee.getEmployeeJobTitle());
		
	}

	private Employee findOrCreateEmployee(Long restaurantId, Long employeeId) {
		if(employeeId == null) {
			return new Employee();
		}
		return findEmployeeById(restaurantId, employeeId);
	}

	private Employee findEmployeeById(Long restaurantId, Long employeeId) {
		Employee emp = employeeDao.findById(employeeId)
				.orElseThrow(() -> new NoSuchElementException (
						"Employee with ID=" + employeeId + " does not exist"));
		if(emp.getRestaurant().getRestaurantId().equals(restaurantId)) {
			return emp;
		} else {
			throw new IllegalArgumentException("Employee/Restaurant ID Mismatch");
		}
	}

	public void deleteRestaurantId(Long restaurantId) {
		Restaurant restaurant = findRestaurantById(restaurantId);
		restaurantDao.delete(restaurant);
	}

}
