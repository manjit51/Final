package restaurant.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import restaurant.project.controller.model.RestaurantData;
import restaurant.project.entity.Restaurant;

public class RestaurantServiceTestSupport {
	
	private static final String RESTAURANT_TABLE = "restaurant";
	
	// @formatter:off
	private RestaurantData insertAddress1 = new RestaurantData(
				1L,
				"Sandy Beach Drinks",
				
				"Wave Front Ave",
				
				"San Diego",
				
				"California",
				
				"16485"
			);
	private RestaurantData insertAddress2 = new RestaurantData(
			2L,
			"Sandy Beach Eatery",
			"Horizon End Lane",
			"Los Angelas",
			"California",
			"16348"
		);
	// @formatter:on
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private RestaurantController restaurantController;
	
	protected RestaurantData buildInsertRestaurant(int which) {
		return which == 1 ? insertAddress1 : insertAddress2;
	}
	
	protected RestaurantData insertRestaurant(RestaurantData restaurantData) {
		Restaurant restaurant = restaurantData.toRestaurant();
		RestaurantData clone = new RestaurantData(restaurant);
		
		clone.setRestaurantId(null);
		return restaurantController.createRestaurant(clone);
	}

	protected int rowsInRestaurantTable() {
		// TODO Auto-generated method stub
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, RESTAURANT_TABLE);
	}
	
	protected RestaurantData retrieveRestaurant(Long restaurantId) {
		// TODO Auto-generated method stub
		return restaurantController.retrieveRestaurant(restaurantId);
	}
}
