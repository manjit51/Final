package restaurant.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import restaurant.project.entity.Patron;

public interface PatronDao extends JpaRepository<Patron, Long> {

}
