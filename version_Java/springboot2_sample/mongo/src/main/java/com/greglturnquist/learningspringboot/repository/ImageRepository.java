package com.greglturnquist.learningspringboot.repository;

import com.greglturnquist.learningspringboot.model.Image;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

/**
 * @author au
 */
public interface ImageRepository extends ReactiveCrudRepository<Image, String> {

	Mono<Image> findByName(String name);
}

