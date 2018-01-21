package com.example.sample_layering;

import com.example.sample_layering.domain.Customer;
import com.example.sample_layering.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*
 * layerについて
 * コンポーネントスキャンの対象となるアノテーションは下記の5点となります。
 * 1.@Controller
 * 2.@RestController
 * 3.@Service
 * 4.@Repository
 * 5.@Component
 *
 * 説明
 * 1.2は、Web MVCフレームワークのコントローラに相当します。
 * 3.5は、機能的な違いはありません。layerでの責務を明確にするために利用する。
 * 4は、Domain-Driven-Design(DDD)で定義されている「ドメインオブジェクトの保存、取得、検索」の操作をカプセル化し、コレクションオブジェクトのように振る舞う役割を担う
 *
 * 処理の流れ
 * Controller <-> Service <-> Repository
 *
 */

@SpringBootApplication
public class SampleLayeringApplication implements CommandLineRunner {
	@Autowired
	CustomerService customerService;

	@Override
	public void run(String... strings) throws Exception {
		// データ追加
		customerService.save(new Customer(1, "Nobita", "Nobi"));
		customerService.save(new Customer(2, "Takeshi", "Goda"));
		customerService.save(new Customer(3, "Suneo", "Honekawa"));

		// データ表示
		customerService.findAll()
				.forEach(System.out::println);
	}

	public static void main(String[] args) {
		SpringApplication.run(SampleLayeringApplication.class, args);
	}
}
