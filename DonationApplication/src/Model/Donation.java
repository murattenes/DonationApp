package Model;

public class Donation {
	private Long number;
	private int category;
	private String subCategory;
	private String param1;
	private String param2;
	private String condition;
	private int quantity;
	private int status;
	private int donor;
	private int recipient;
	
	public Donation() {
		
	}
	public Donation(Long number, int category, String subCategory, String param1, String param2, String condition,
			int quantity, int status, int donor, int recipient) {
		super();
		this.number = number;
		this.category = category;
		this.subCategory = subCategory;
		this.param1 = param1;
		this.param2 = param2;
		this.condition = condition;
		this.quantity = quantity;
		this.status = status;
		this.donor = donor;
		this.recipient = recipient;
	}


	public Long getNumber() {
		return number;
	}


	public void setNumber(Long number) {
		this.number = number;
	}


	public int getCategory() {
		return category;
	}


	public void setCategory(int category) {
		this.category = category;
	}


	public String getSubCategory() {
		return subCategory;
	}


	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}


	public String getParam1() {
		return param1;
	}


	public void setParam1(String param1) {
		this.param1 = param1;
	}


	public String getParam2() {
		return param2;
	}


	public void setParam2(String param2) {
		this.param2 = param2;
	}


	public String getCondition() {
		return condition;
	}


	public void setCondition(String condition) {
		this.condition = condition;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public int getDonor() {
		return donor;
	}


	public void setDonor(int donor) {
		this.donor = donor;
	}


	public int getRecipient() {
		return recipient;
	}


	public void setRecipient(int recipient) {
		this.recipient = recipient;
	}
	
	
	

}
