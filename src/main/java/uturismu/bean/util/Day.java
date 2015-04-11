package uturismu.bean.util;

import uturismu.bean.UTurismuBean;

public class Day implements UTurismuBean {

	private static final long serialVersionUID = -5967193259856227371L;
	private String label;
	private Integer value;

	public Day() {
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}
