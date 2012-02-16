package uturismu.bean;

public class TourOperatorBean extends AccountBean {

	private static final long serialVersionUID = 3385587383095665523L;
	private String vatNumber;
	private String name;
	private String holderName;

	public TourOperatorBean() {
	}

	public String getVatNumber() {
		return vatNumber;
	}

	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

}
