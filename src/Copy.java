import java.util.Date;

/**
 * 
 * Starting class for TP-1
 * 
 * Feel free to add, modify, or delete any of the following code: it's just a
 * possible starting implementation
 * 
 * @author eric
 *
 */
public class Copy {
	private String copyID;
	private String title;
	private Patron outTo;
	private String deuDAte;

	public Copy(String copyID, String title) {
		// finish this
		this.copyID = copyID;
		this.setTitle(title);
		this.outTo = new Patron(null, null);
	}

	// generate getters and setters using Eclipse Source menu

	public String toString() {
		// correctly implement this
		// return "A description of this Copy's current state.";
		return "coppID : " + this.copyID + ", Title: " + this.getTitle() + ", Patron: " + outTo.getName()
				+ " Due date: " + this.deuDAte;

	}

	@Override
	public boolean equals(Object o) {
		// finish this: two are equals iff same copy ID

		// return false;
		if (o instanceof Copy) {
			if (this.copyID == ((Copy) o).copyID)
				return true;
		}
		return false;
	}

	public Patron getOutTo() {
		return outTo;
	}

	public void setOutTo(Patron outTo) {
		this.outTo = outTo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCopyID() {
		return copyID;
	}

	public void setCopyID(String copyID) {
		this.copyID = copyID;
	}

	public String getDeuDAte() {
		return deuDAte;
	}

	public void setDeuDAte(String deuDAte) {
		this.deuDAte = deuDAte;
	}

}
