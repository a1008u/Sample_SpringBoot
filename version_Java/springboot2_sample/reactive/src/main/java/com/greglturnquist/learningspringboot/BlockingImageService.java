package com.greglturnquist.learningspringboot;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.codec.multipart.FilePart;


/**
 * imageServiceを同期処理に変更
 */
public class BlockingImageService {
    private final ImageService imageService;

    public BlockingImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    /**
     *
     * @return
     */
    public List<Image> findAllImages() {
        return imageService
                .findAllImages()
                .collectList()
                .block(Duration.ofSeconds(10));
    }

    /**
     *
     * @param filename
     * @return
     */
    public Resource findOneImage(String filename) {
        return imageService
                .findOneImage(filename)
                .block(Duration.ofSeconds(30));
    }

    /**
     *
     * @param files
     */
    public void createImage(List<FilePart> files) {
        imageService
            .createImage(Flux.fromIterable(files))
            .block(Duration.ofMinutes(1));
    }

    /**
     *
     * @param filename
     */
    public void deleteImage(String filename){
        imageService

            .deleteImage(filename)
            .block(Duration.ofMinutes(1));
    }

}
