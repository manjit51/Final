package restaurant.project.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import restaurant.project.RestaurantApplication;
import restaurant.project.controller.model.RestaurantData;


@SpringBootTest(webEnvironment = WebEnvironment.NONE,
	classes = RestaurantApplication.class)

@ActiveProfiles("test")
@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"})
class RestaurantControllerTest extends RestaurantServiceTestSupport{

	@Test
	void testInserRestaurant() {
		// Given: A restaurant request
		RestaurantData request = buildInsertRestaurant(1);
		RestaurantData expected = buildInsertRestaurant(1);
		
		// When: the restaurant is added to the restaurant table
		
		RestaurantData actual = insertRestaurant(request);
		
		// Then: the restaurant returned is what is expected
		assertThat(actual).isEqualTo(expected);
		
		// And: there is one row in the restaurant table
		assertThat(rowsInRestaurantTable()).isOne();
		
	}
	@Test
	void testRetrieveRestaurant() {
		// Given a Restaurant
		RestaurantData restaurant = insertRestaurant(buildInsertRestaurant(1));
		RestaurantData expected = buildInsertRestaurant(1);
		
		// When the restaurant is retrieved by restaurant id
		RestaurantData actual = retrieveRestaurant(restaurant.getRestaurantId());
		
		
		// Then the restaurant returned is what is expected
		
		assertThat(actual).isEqualTo(expected);
		
		// And the correct restaurant id is returned
	}
}
