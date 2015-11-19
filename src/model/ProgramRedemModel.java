package model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ProgramRedemModel {

	private Set<String> cardNums = new HashSet<String>();
	
	private int numOfCore = 0;
	private int numOfSecondary = 0;
	private int numOfOccasional = 0;
	
	public ProgramRedemModel() {
	}

	public Set<String> getCardNums() {
		return cardNums;
	}

	public void setCardNums(Set<String> cardNums) {
		this.cardNums = cardNums;
	}

	public int getNumOfCore() {
		return numOfCore;
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

	public void computeSegGroup(CustomerDatabaseModel customerDB) {
		Iterator<String> iterator = this.cardNums.iterator();
		while(iterator.hasNext()) {
			String cardNum = iterator.next().trim();
			Customer customer = customerDB.getCustomers().get(cardNum);
			if (customer == null) {
				System.out.println("CardNum: " + cardNum + " is not in the customer database.");
				continue;
			} else {
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
	}
	
}
