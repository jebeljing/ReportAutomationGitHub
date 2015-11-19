package model;

import java.util.Map;

public class CustomerDatabaseModel {

	private Map<String, Customer> customers;
	private int numOfCore = 0;
	private int numOfSecondary = 0;
	private int numOfOccasional = 0;
	
	
	public CustomerDatabaseModel(Map<String, Customer> customers, int numOfCore,
			int numOfSecondary, int numOfOccasional) {
		super();
		this.customers = customers;
		this.numOfCore = numOfCore;
		this.numOfSecondary = numOfSecondary;
		this.numOfOccasional = numOfOccasional;
	}
	
	public CustomerDatabaseModel() {}

	public Map<String, Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Map<String, Customer> customers) {
		this.customers = customers;
		getSegGroupCustomer();
	}

	public int getNumOfCore() {
		return numOfCore;
	}
	
	private void getSegGroupCustomer() {
		for (Customer customer : customers.values()) {
			String segGroup = customer.getSegGroup().trim();
			if (segGroup.equals("Core")) {
				this.numOfCore++;
			} else if (segGroup.equals("Secondary")) {
				this.numOfSecondary++;
			} else if (segGroup.equals("Occasional")) {
				this.numOfOccasional++;
			}
		}
	}

	public void setNumOfCore(int numOfCore) {
		this.numOfCore = numOfCore;
	}

	public int getNumOfSecondary() {
		return numOfSecondary;
	}

	public void setNumOfSecondary(int numOfSecondary) {
		this.numOfSecondary = numOfSecondary;
	}

	public int getNumOfOccasional() {
		return numOfOccasional;
	}

	public void setNumOfOccasional(int numOfOccasional) {
		this.numOfOccasional = numOfOccasional;
	}
	
}
