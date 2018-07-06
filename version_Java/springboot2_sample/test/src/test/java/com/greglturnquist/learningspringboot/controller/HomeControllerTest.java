package com.greglturnquist.learningspringboot.controller;

import com.greglturnquist.learningspringboot.model.Image;
import com.greglturnquist.learningspringboot.service.ImageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebFluxTest(controllers = HomeController.class)
@Import({ThymeleafAutoConfiguration.class})
public class HomeControllerTest {

    // mockを利用するため
    @Autowired
    WebTestClient webClient;

    @MockBean
    ImageService imageService;

    @Test // 正常系
    public void baseRouteShouldListAllImages() {
        // given(mockの動作を定義)
        Image alphaImage = new Image("1", "alpha.png");
        Image bravoImage = new Image("2", "bravo.png");
        given(imageService.findAllImages())
                .willReturn(Flux.just(alphaImage, bravoImage));

        // when
        // exchange() :: ResponseSpecオブジェクトが返ってくる(httpステータスなど)
        // BodyをStringに変換して返す
        EntityExchangeResult<String> result =
                webClient
                    .get().uri("/")
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody(String.class).returnResult();

        // then
        // 想定しているメソッドが呼ばれているか確認 + レンダリングされたHTMLページの主要部分の一部を検査します
        verify(imageService).findAllImages();
        verifyNoMoreInteractions(imageService);
        assertThat(result.getResponseBody())
                .contains("<title>Learning Spring Boot: Spring-a-Gram</title>")
                .contains("<a href=\"/images/alpha.png/raw\">")
                .contains("<a href=\"/images/bravo.png/raw\">");
    }

    @Test // 正常系
    public void fetchingImageShouldWork() {

        given(imageService.findOneImage(any()))
            .willReturn(Mono.just(new ByteArrayResource("data".getBytes())));

        webClient
            .get().uri("/images/alpha.png/raw")
            .exchange()
            .expectStatus().isOk()
            .expectBody(String.class).isEqualTo("data");

        verify(imageService).findOneImage("alpha.png");
        verifyNoMoreInteractions(imageService);
    }

    @Test // 異常系
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

}
