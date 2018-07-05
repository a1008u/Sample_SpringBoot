
package com.greglturnquist.learningspringboot.repository;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.*;
import static org.springframework.data.mongodb.core.query.Criteria.*;

import java.util.UUID;

import com.greglturnquist.learningspringboot.Employee;
import com.greglturnquist.learningspringboot.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author au
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = QueryTests.TestConfig.class)
@DataMongoTest
public class QueryTests {

	@Autowired
	EmployeeRepository repository;

	@Autowired
	ReactiveMongoOperations operations;

	@Autowired
	MongoOperations blockingOperations;

	@Before
	public void setUp() {
		blockingOperations.dropCollection(Employee.class);

		Employee e1 = new Employee();
		e1.setId(UUID.randomUUID().toString());
		e1.setFirstName("Bilbo");
		e1.setLastName("Baggins");
		e1.setRole("burglar");
		blockingOperations.insert(e1);

		Employee e2 = new Employee();
		e2.setId(UUID.randomUUID().toString());
		e2.setFirstName("Frodo");
		e2.setLastName("Baggins");
		e2.setRole("ring bearer");
		blockingOperations.insert(e2);
	}

	@Test
	public void testSingle() {
		Employee e = new Employee();
		e.setFirstName("Bilbo");
		Example<Employee> example = Example.of(e);
		Mono<Employee> singleEmployee = repository.findOne(example);

		StepVerifier
			.create(singleEmployee)
			.expectNextMatches(employee -> {
				assertThat(employee).hasNoNullFieldsOrProperties();
				assertThat(employee.getFirstName()).isEqualTo("Bilbo");
				assertThat(employee.getLastName()).isEqualTo("Baggins");
				assertThat(employee.getRole()).isEqualTo("burglar");
				return true;
			})
			.expectComplete()
			.verify();
	}

	@Test
	public void testMultiple() {
		Employee e = new Employee();
		e.setLastName("baggins");

		ExampleMatcher matcher =
				ExampleMatcher
					.matching()
					.withIgnoreCase()
					.withMatcher("lastName", startsWith())
					.withIncludeNullValues();

		Example<Employee> example = Example.of(e, matcher);

		Flux<Employee> multipleEmployees = repository.findAll(example);

		StepVerifier
			.create(multipleEmployees.collectList())
			.expectNextMatches(employees -> {
				assertThat(employees).hasSize(2);
				assertThat(employees).extracting("firstName")
					.contains("Frodo", "Bilbo");
				return true;
			})
			.expectComplete()
			.verify();
	}

	@Test
	public void testSingleWithTemplate() {
		Employee e = new Employee();
		e.setFirstName("Bilbo");
		Example<Employee> example = Example.of(e);
		Mono<Employee> singleEmployee = operations.findOne(new Query(byExample(example)), Employee.class);

		StepVerifier
			.create(singleEmployee)
			.expectNextMatches(employee -> {
				assertThat(employee).hasNoNullFieldsOrProperties();
				assertThat(employee.getFirstName()).isEqualTo("Bilbo");
				assertThat(employee.getLastName()).isEqualTo("Baggins");
				assertThat(employee.getRole()).isEqualTo("burglar");
				return true;
			})
			.expectComplete()
			.verify();
	}

	@Test
	public void testMultipleWithTemplate() {
		Employee e = new Employee();
		e.setLastName("baggins");

		ExampleMatcher matcher =
				ExampleMatcher
					.matching()
					.withIgnoreCase()
					.withMatcher("lastName", startsWith())
					.withIncludeNullValues();

		Example<Employee> example = Example.of(e, matcher);

		Flux<Employee> multipleEmployees = operations.find(new Query(byExample(example)), Employee.class);

		StepVerifier
			.create(multipleEmployees.collectList())
			.expectNextMatches(employees -> {
				assertThat(employees).hasSize(2);
				assertThat(employees).extracting("firstName")
					.contains("Frodo", "Bilbo");
				return true;
			})
			.expectComplete()
			.verify();
	}

	@Configuration
	@EnableReactiveMongoRepositories(basePackageClasses = EmployeeRepository.class)
	static class TestConfig {

	}
}
