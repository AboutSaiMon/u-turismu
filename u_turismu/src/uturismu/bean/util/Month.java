package uturismu.bean.util;

import uturismu.bean.UTurismuBean;

public class Month implements UTurismuBean {

	private static final long serialVersionUID = 2042673331148175488L;
	private String label;
	private Integer value;

	public Month() {
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
