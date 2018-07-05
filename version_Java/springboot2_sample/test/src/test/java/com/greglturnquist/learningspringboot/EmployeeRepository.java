package com.greglturnquist.learningspringboot;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @author au
 */
public interface EmployeeRepository extends
	ReactiveCrudRepository<Employee, String>,
	ReactiveQueryByExampleExecutor<Employee> {

}
