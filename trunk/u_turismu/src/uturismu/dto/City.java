package uturismu.dto;

import java.io.Serializable;
import java.util.Set;

public class City implements Serializable {

	private static final long serialVersionUID = -5177619158328836473L;
	private Long id;
	private String name;
	private String province;
	private Set<Event> events;

}
