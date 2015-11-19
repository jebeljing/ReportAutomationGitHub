package model;

public class Customer {

	private String card_num;
	private String household;
	private String segment;
	private String segGroup;
	
	public Customer() {}

	public Customer(String card_num, String household, String segment,
			String segGroup) {
		super();
		this.card_num = card_num;
		this.household = household;
		this.segment = segment;
		this.segGroup = segGroup;
	}

	public String getCard_num() {
		return card_num;
	}

	public void setCard_num(String card_num) {
		this.card_num = card_num;
	}

	public String getHousehold() {
		return household;
	}

	public void setHousehold(String household) {
		this.household = household;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getSegGroup() {
		return segGroup;
	}

	public void setSegGroup(String segGroup) {
		this.segGroup = segGroup;
	}

	@Override
	public String toString() {
		return "Customer [card_num=" + card_num + ", household=" + household
				+ ", segment=" + segment + ", segGroup=" + segGroup + "]";
	};
	
	
}
