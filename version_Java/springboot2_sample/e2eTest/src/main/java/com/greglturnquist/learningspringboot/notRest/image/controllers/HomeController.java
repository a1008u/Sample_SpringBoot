package com.greglturnquist.learningspringboot.notRest.image.controllers;

import java.io.IOException;

import com.greglturnquist.learningspringboot.notRest.image.services.ImageService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author au
 */
@Controller
public class HomeController {

	private static final String BASE_PATH = "/images";
	private static final String FILENAME = "{filename:.+}";

	private final ImageService imageService;

	public HomeController(ImageService imageService) {
		this.imageService = imageService;
	}

	@GetMapping("/")
	public Mono<String> index(Model model) {
		model.addAttribute("images",
			imageService.findAllImages());
		return Mono.just("index");
	}

	@GetMapping(value = BASE_PATH + "/" + FILENAME + "/raw",
		produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public Mono<ResponseEntity<?>> oneRawImage(
		@PathVariable String filename) {
		// tag::try-catch[]
		return imageService.findOneImage(filename)
			.map(resource -> {
				try {
					return ResponseEntity.ok()
						.contentLength(resource.contentLength())
						.body(new InputStreamResource(
							resource.getInputStream()));
				} catch (IOException e) {
					return ResponseEntity.badRequest()
						.body("Couldn't find " + filename +
							" => " + e.getMessage());
				}
			});
		// end::try-catch[]
	}

	@PostMapping(value = BASE_PATH)
	public Mono<String> createFile(@RequestPart(name = "file")
										   Flux<FilePart> files) {
		return imageService.createImage(files)
			.then(Mono.just("redirect:/"));
	}

	@DeleteMapping(BASE_PATH + "/" + FILENAME)
	public Mono<String> deleteFile(@PathVariable String filename) {
		return imageService.deleteImage(filename)
			.then(Mono.just("redirect:/"));
	}

}
