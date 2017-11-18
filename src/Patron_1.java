
public class Patron_1 {
	private String patronName;
	private String patronId;
	
	Patron_1(String newName, String newId){
		this.patronName = newName;
		this.patronId = newId;
		
	}

	public String getPatronName() {
		return patronName;
	}

	public void setPatronName(String patronName) {
		this.patronName = patronName;
	}

	public String getPatronId() {
		return patronId;
	}

	public void setPatronId(String patronId) {
		this.patronId = patronId;
	}

}
