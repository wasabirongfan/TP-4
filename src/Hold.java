
public class Hold {
	
	private String  description;
	private String overdueFine;
	
	
	public Hold(String description, String overdueFine) {
		super();
		this.description = description;
		this.overdueFine = overdueFine;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getOverdueFine() {
		return overdueFine;
	}


	public void setOverdueFine(String overdueFine) {
		this.overdueFine = overdueFine;
	}
	
	

}
