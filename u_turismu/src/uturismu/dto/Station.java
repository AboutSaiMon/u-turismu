package uturismu.dto;

import java.io.Serializable;
import java.util.Set;

public abstract class Station implements Serializable {

	private static final long serialVersionUID = 259910721548074249L;
	private Long id;
	private String name;
	private Address address;
	private Set<Transport> transports;

}
