package com.example.sample_4_jpa;

import com.example.sample_4_jpa.domain.Customer;
import com.example.sample_4_jpa.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


/**
 * JPA(Java Persistence API)
 * 1.Javaオブジェクトとデータベースに格納されているデータとのマッピング機能
 * 2.データベースへのCRUD処理をカプセル化したAPI
 * 3.Javaオブジェクトを検索するためのクエリ言語(JPQL)
 *
 * 組込データベースを使うと、起動時にエンティティに対応したテーブルの削除や作成がデフォルトで行われる
 */
@SpringBootApplication
public class Sample4JpaApplication implements CommandLineRunner {

	// コンストラクタでDI
	private final CustomerRepository customerRepository;

	@Autowired
	public Sample4JpaApplication(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public void run(String... strings) throws Exception {

		System.out.println("JPAの操作-----------------------------------------------");

		// データ追加
		Customer created = customerRepository.save(new Customer(null, "Hidetoshi", "Dekisugi"));
		System.out.println(created + " is created!");

		// データ表示(JPAで自動生成される)
		System.out.println("データ表示(JPAで自動生成される)-------------------------");
		customerRepository
			.findAll()
			.forEach(customer -> {
			  System.out.println("findAll--------------------------------------");
			  System.out.println("customer(id) = " + customer.getId());
			  System.out.println("customer(firstname)) = " + customer.getFirstName());
			  System.out.println("customer(lastname) = " + customer.getLastName());
			  System.out.println("--------------------------------------findAll");
			});

		// データ表示(＠Queryを用いて生成する)
		System.out.println("データ表示(＠Queryを用いて生成する)-------------------------");
		customerRepository
			.findAllOrderByName()
			.forEach(customer -> {
				System.out.println("findAllOrderByName--------------------------------------");
				System.out.println("customer(id) = " + customer.getId());
				System.out.println("customer(firstname)) = " + customer.getFirstName());
				System.out.println("customer(lastname) = " + customer.getLastName());
				System.out.println("--------------------------------------findAllOrderByName");
			});

		// ページング処理(JPA利用)
		System.out.println(" ページング処理(JPA利用)-------------------------");
		// Pagebleインターフェイスで、取得するページング情報を用意。第１引数：ページ数　第２引数：1ページあたりの件数
		Pageable pageable = new PageRequest(0, 3);
		Page<Customer> page = customerRepository.findAll(pageable);

		System.out.println("1ページのデータ数=" + page.getSize());
		System.out.println("現在のページ=" + page.getNumber());
		System.out.println("全ページ数=" + page.getTotalPages());
		System.out.println("全データ数=" + page.getTotalElements());

		page.getContent()
			.forEach(System.out::println);

		// ページング処理(JPQL利用)
		System.out.println(" ページング処理(JPQL利用)-------------------------");
		Page<Customer> page2 = customerRepository.findAllOrderByName(pageable);

		System.out.println("1ページのデータ数=" + page2.getSize());
		System.out.println("現在のページ=" + page2.getNumber());
		System.out.println("全ページ数=" + page2.getTotalPages());
		System.out.println("全データ数=" + page2.getTotalElements());

		page2.getContent()
				.forEach(System.out::println);
	}

	public static void main(String[] args) {
		SpringApplication.run(Sample4JpaApplication.class, args);
	}
}
