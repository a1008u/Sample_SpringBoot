package com.example.sample_4_jdbc;

import com.example.sample_4_jdbc.domain.Customer;
import com.example.sample_4_jdbc.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

@SpringBootApplication
public class Sample4JdbcApplication implements CommandLineRunner {

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	CustomerRepository customerRepository;

	/**
	 * applcation.propertiesを用いて、JDBCの設定を行う
	 * @param strings
	 * @throws Exception
	 */
	@Override
	public void run(String... strings) throws Exception {
		String sql = "SELECT id, first_name, last_name FROM customers WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", 1);
		Customer result = jdbcTemplate.queryForObject(sql
			, param
			, (rs, rowNum) -> new Customer(rs.getInt("id")
											, rs.getString("first_name")
											, rs.getString("last_name"))
		);

		System.out.println("result(id) = " + result.getId());
        System.out.println("result(firstname) = " + result.getFirstName());
        System.out.println("result(lastname) = " + result.getLastName());

		System.out.println("====================================");

		// データ追加（idは自動生成するため、ここではnullableを可とする。）
		Customer created = customerRepository.save(new Customer(null, "Hidetoshi", "Dekisugi"));
		System.out.println(created + " is created!");

		// データ表示
		customerRepository
            .findAll()
            .forEach(customer -> {
                System.out.println("--------------------------------------");
                System.out.println("customer(id) = " + customer.getId());
                System.out.println("customer(firstname)) = " + customer.getFirstName());
                System.out.println("customer(lastname) = " + customer.getLastName());
                System.out.println("--------------------------------------");
        });

	}

	public static void main(String[] args)  {
		SpringApplication.run(Sample4JdbcApplication.class, args);
	}
}
