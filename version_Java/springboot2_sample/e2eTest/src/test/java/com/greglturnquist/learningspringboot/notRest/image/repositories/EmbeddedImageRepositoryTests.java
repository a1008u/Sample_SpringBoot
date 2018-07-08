package com.greglturnquist.learningspringboot.notRest.image.repositories;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;

import com.greglturnquist.learningspringboot.notRest.image.domain.Image;
import com.greglturnquist.learningspringboot.notRest.image.repositories.ImageRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author au
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class EmbeddedImageRepositoryTests {

	@Autowired
  ImageRepository repository;

	@Autowired
	MongoOperations operations;

	/**
	 * test用のデータを生成
	 * {@link MongoOperations} during setup.
	 */
	@Before
	public void setUp() {
		operations.dropCollection(Image.class);
		operations.insert(new Image("1", "learning-spring-boot-cover.jpg"));
		operations.insert(new Image("2", "learning-spring-boot-2nd-edition-cover.jpg"));
		operations.insert(new Image("3", "bazinga.png"));
		operations.findAll(Image.class).forEach(image -> { System.out.println(image.toString()); });
	}

	@Test
	public void findAllのテスト() {
		StepVerifier
			.create(repository.findAll())
			.recordWith(ArrayList::new)
			.expectNextCount(3)
			.consumeRecordedWith(results -> {
				assertThat(results).hasSize(3);
				assertThat(results)
					.extracting(Image::getName)
					.contains(
						"learning-spring-boot-cover.jpg",
						"learning-spring-boot-2nd-edition-cover.jpg",
						"bazinga.png");
			})
			.expectComplete()
			.verify();
	}

	@Test
	public void findByNameのテスト() {
		StepVerifier
			.create(repository.findByName("bazinga.png"))
			.expectNextMatches(results -> {
					assertThat(results.getName()).isEqualTo("bazinga.png");
					assertThat(results.getId()).isEqualTo("3");
					return true;
			});
	}

	@Test
	public void deleteのテスト() {
		repository
			.deleteById("3")
			.subscribe();

		StepVerifier
			.create(repository.findAll())
			.recordWith(ArrayList::new)
			.expectNextCount(2)
			.consumeRecordedWith(results -> {
				assertThat(results).hasSize(2);
				assertThat(results)
					.extracting(Image::getName)
					.contains(
							"learning-spring-boot-cover.jpg",
							"learning-spring-boot-2nd-edition-cover.jpg");
			})
			.expectComplete()
			.verify();
	}

	@Test
	public void deleteAllのテスト() {
		StepVerifier
			.create(repository.deleteAll())
			.expectComplete()
			.verify();

		StepVerifier
			.create(repository.findAll())
			.recordWith(ArrayList::new)
			.consumeRecordedWith(results -> {
				assertThat(results.isEmpty());
			})
			.expectComplete()
			.verify();
	}
}
