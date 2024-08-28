package restaurant.project.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import restaurant.project.entity.Employee;
import restaurant.project.entity.Patron;
import restaurant.project.entity.Restaurant;

@Data
@NoArgsConstructor
public class RestaurantData {
	
	private Long restaurantId;
	
	private String restaurantName;
	private String street;
	private String city;
	private String state;
	private String zip;
	
	
	private Set<EmployeeData> employees = new HashSet<>();
	
	
	private Set<PatronData> patrons = new HashSet<>();
	
	public RestaurantData(Restaurant restaurant) {
		this.restaurantId = restaurant.getRestaurantId();
		this.restaurantName = restaurant.getRestaurantName();
		this.street = restaurant.getStreet();
		this.city = restaurant.getCity();
		this.state = restaurant.getState();
		this.zip = restaurant.getZip();
		
		for(Employee employee : restaurant.getEmployees()) {
			this.employees.add(new EmployeeData(employee));
		}
		
		for(Patron patron : restaurant.getPatrons()) {
			this.patrons.add(new PatronData(patron));
		}
		
	}
	
	public RestaurantData(Long restaurantId, 
			String restaurantName, String street, String city, String state, String zip) {
		this.restaurantId = restaurantId;
		
		this.restaurantName = restaurantName;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	public Restaurant toRestaurant() {
		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantId(restaurantId);
		restaurant.setRestaurantName(restaurantName);
		restaurant.setStreet(street);
		restaurant.setCity(city);
		restaurant.setState(state);
		restaurant.setZip(zip);
		
		for(EmployeeData employeeData : employees) {
			restaurant.getEmployees().add(employeeData.toEmployee());
		}
		
		for(PatronData patronData : patrons) {
			restaurant.getPatrons().add(patronData.toPatron());
		}
		
		return restaurant;
	}
	
	@Data
	@NoArgsConstructor
	public static class EmployeeData {
		private Long employeeId;
		
		private String employeeFirstName;
		
		private String employeeLastName;
		
		private String employeeJobTitle;
		
		public EmployeeData(Employee employee) {
			this.employeeId = employee.getEmployeeId();
			this.employeeFirstName = employee.getEmployeeFirstName();
			this.employeeLastName = employee.getEmployeeLastName();
			this.employeeJobTitle = employee.getEmployeeJobTitle();
		}
		
		public Employee toEmployee() {
			Employee employee = new Employee();
			employee.setEmployeeId(employeeId);
			employee.setEmployeeFirstName(employeeFirstName);
			employee.setEmployeeLastName(employeeLastName);
			employee.setEmployeeJobTitle(employeeJobTitle);
			
			return employee;
		}
	}
	
	@Data
	@NoArgsConstructor
	static class PatronData {
		private Long patronId;
		private String patronFirstName;
		private String patronLastName;
		private String patronFoodPreference;
		
		PatronData(Patron patron) {
			this.patronId = patron.getPatronId();
			this.patronFirstName = patron.getPatronFirstName();
			this.patronLastName = patron.getPatronLastName();
			this.patronFoodPreference = patron.getPatronFoodPreference();
		}
		
		Patron toPatron() {
			Patron patron = new Patron();
			patron.setPatronId(patronId);
			patron.setPatronFirstName(patronFirstName);
			patron.setPatronLastName(patronLastName);
			patron.setPatronFoodPreference(patronFoodPreference);
			
			return patron;
		}
	}
}
