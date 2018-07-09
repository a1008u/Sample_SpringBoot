
package com.greglturnquist.learningspringboot.e2e;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import com.greglturnquist.learningspringboot.ImageRepository;
import com.greglturnquist.learningspringboot.model.Image;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author au
 */
// tag::1[]
@RunWith(SpringRunner.class)
@DataMongoTest
public class EmbeddedImageRepositoryTests {

	@Autowired
	ImageRepository repository;

	@Autowired
	MongoOperations operations;

	/**
	 * To avoid {@code block()} calls, use blocking {@link MongoOperations} during setup.
	 */
	@Before
	public void setUp() {
		operations.dropCollection(Image.class);
		operations.insert(new Image("1", "learning-spring-boot-cover.jpg"));
		operations.insert(new Image("2", "learning-spring-boot-2nd-edition-cover.jpg"));
		operations.insert(new Image("3", "bazinga.png"));

		operations
			.findAll(Image.class)
			.forEach(image -> { System.out.println(image.toString()); });
	}

	@Test
	public void findAllShouldWork() {
		List<Image> images = repository.findAll().collectList().block();

		assertThat(images).hasSize(3);
		assertThat(images)
			.extracting(Image::getName)
			.contains(
				"learning-spring-boot-cover.jpg",
				"learning-spring-boot-2nd-edition-cover.jpg",
				"bazinga.png");
	}

	@Test
	public void findByNameShouldWork() {
		Image image = repository.findByName("bazinga.png").block();
		assertThat(image.getName()).isEqualTo("bazinga.png");
		assertThat(image.getId()).isEqualTo("3");
	}

}
