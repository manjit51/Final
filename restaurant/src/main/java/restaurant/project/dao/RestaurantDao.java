package restaurant.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import restaurant.project.entity.Restaurant;

public interface RestaurantDao extends JpaRepository<Restaurant, Long> {

}
