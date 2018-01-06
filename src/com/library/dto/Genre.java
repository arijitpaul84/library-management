package com.library.dto;

public enum Genre {
	
	SCIENCE(1, "Science"),
	DRAMA(2, "Drama"),
	ACTION(3, "Action"),
	ADVENTURE (4, "Adventure"),
	ROMANCE(5, "Romance"),
	MYSTERY(6, "Mystery"),
	HORROR(7, "Horror");
	
	private final Integer id;
	private final String name;
	
	private Genre(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	
}
