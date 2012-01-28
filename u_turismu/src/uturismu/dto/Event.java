package uturismu.dto;

import java.io.Serializable;

import uturismu.dto.util.EventType;

public class Event implements Serializable {

	private static final long serialVersionUID = 3918383512779462245L;
	private Long id;
	private String name;
	private EventType type;
	private String description;
	private City city;

}