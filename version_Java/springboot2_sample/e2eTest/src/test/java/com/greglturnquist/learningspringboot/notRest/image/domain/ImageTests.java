package com.greglturnquist.learningspringboot.notRest.image.domain;

import com.greglturnquist.learningspringboot.notRest.image.domain.Image;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author au
 */
public class ImageTests {

	@Test
	public void imagesManagedByLombokShouldWork() {
		Image image = new Image("id", "file-name.jpg");

		assertThat(image.getId()).isEqualTo("id");
		assertThat(image.getName()).isEqualTo("file-name.jpg");
	}

}

