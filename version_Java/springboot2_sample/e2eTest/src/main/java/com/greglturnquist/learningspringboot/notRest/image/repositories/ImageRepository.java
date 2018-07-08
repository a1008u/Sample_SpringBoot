package com.greglturnquist.learningspringboot.notRest.image.repositories;

import com.greglturnquist.learningspringboot.notRest.image.domain.Image;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

/**
 * @author au
 */
public interface ImageRepository extends ReactiveCrudRepository<Image, String> {

	Mono<Image> findByName(String name);
}

