package restaurant.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import restaurant.project.entity.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {

}
