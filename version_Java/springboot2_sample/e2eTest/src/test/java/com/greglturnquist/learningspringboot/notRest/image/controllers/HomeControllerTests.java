package com.greglturnquist.learningspringboot.notRest.image.controllers;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.io.IOException;

import com.greglturnquist.learningspringboot.notRest.image.domain.Image;
import com.greglturnquist.learningspringboot.notRest.image.services.ImageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * @author au
 */
// tag::1[]
@RunWith(SpringRunner.class)
@WebFluxTest(controllers = HomeController.class)
@Import({ThymeleafAutoConfiguration.class})
public class HomeControllerTests {

	@Autowired
	WebTestClient webClient;

	@MockBean
  ImageService imageService;

	@Test
	public void baseRouteShouldListAllImages() {
		// given
		Image alphaImage = new Image("1", "alpha.png");
		Image bravoImage = new Image("2", "bravo.png");
		given(imageService.findAllImages()).willReturn(Flux.just(alphaImage, bravoImage));

		// when
		EntityExchangeResult<String> result = webClient
			.get().uri("/")
			.exchange()
			.expectStatus().isOk()
			.expectBody(String.class).returnResult();

		// then
		verify(imageService).findAllImages();
		verifyNoMoreInteractions(imageService);
		assertThat(result.getResponseBody())
			.contains(
				"<title>Learning Spring Boot: Spring-a-Gram</title>")
			.contains("<a href=\"/images/alpha.png/raw\">")
			.contains("<a href=\"/images/bravo.png/raw\">");
	}

	@Test
	public void fetchingImageShouldWork() {
		given(imageService.findOneImage(any())).willReturn(Mono.just(new ByteArrayResource("data".getBytes())));

		webClient
			.get().uri("/images/alpha.png/raw")
			.exchange()
			.expectStatus().isOk()
			.expectBody(String.class).isEqualTo("data");

		verify(imageService).findOneImage("alpha.png");
		verifyNoMoreInteractions(imageService);
	}

	@Test
	public void fetchingNullImageShouldFail() throws IOException {
		Resource resource = mock(Resource.class);
		given(resource.getInputStream()).willThrow(new IOException("Bad file"));
		given(imageService.findOneImage(any())).willReturn(Mono.just(resource));

		webClient
			.get().uri("/images/alpha.png/raw")
			.exchange()
			.expectStatus().isBadRequest()
			.expectBody(String.class)
			.isEqualTo("Couldn't find alpha.png => Bad file");

		verify(imageService).findOneImage("alpha.png");
		verifyNoMoreInteractions(imageService);
	}

	@Test
	public void deleteImageShouldWork() {
		Image alphaImage = new Image("1", "alpha.png");
		given(imageService.deleteImage(any())).willReturn(Mono.empty());

		webClient
			.delete().uri("/images/alpha.png")
			.exchange()
			.expectStatus().isSeeOther()
			.expectHeader().valueEquals(HttpHeaders.LOCATION, "/");

		verify(imageService).deleteImage("alpha.png");
		verifyNoMoreInteractions(imageService);
	}

}
