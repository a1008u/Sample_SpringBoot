package com.greglturnquist.learningspringboot;

/**
 * @author Greg Turnquist
 */
public class Image {

	private int id;
	private String name;

	public Image(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
