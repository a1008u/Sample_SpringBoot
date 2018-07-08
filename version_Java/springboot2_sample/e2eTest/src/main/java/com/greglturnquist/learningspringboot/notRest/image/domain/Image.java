
package com.greglturnquist.learningspringboot.notRest.image.domain;


import org.springframework.data.annotation.Id;

/**
 * @author au
 */

public class Image {

	@Id
	final private String id;
	final private String name;

	public Image(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
}

