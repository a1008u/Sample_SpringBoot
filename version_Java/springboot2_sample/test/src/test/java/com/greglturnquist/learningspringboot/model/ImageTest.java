package com.greglturnquist.learningspringboot.model;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class ImageTest {
    @Test
    public void imagesManagedByLombokShouldWork() {
        Image image = new Image("id", "file-name.jpg");
        assertThat(image.getId()).isEqualTo("id");
        assertThat(image.getName()).isEqualTo("file-name.jpg");
    }
}
