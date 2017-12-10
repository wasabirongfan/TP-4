
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
	

	public static boolean checkCopyOut(Copy c, Patron p) {
		System.out.println("...checking out " + c.getTitle() + " to " + p.getName());
		// check if the copy is currently available before checking it out
		if (c instanceof Copy && c.getOutTo().getName() == null) {
			c.setOutTo(p);
			p.getCopiesOut().add(c);
			c.setDeuDAte("Dec 23 2017");
			// System.out.println(c.toString());
			System.out.println(c.toString());

			return true;
		}

		return false;
	}

	public static boolean checkCopyIn(Copy c, Patron p) {
		System.out.println("...checking in " + c.getTitle());
		// check if the Patron returning the book actually checked it out
		if (c instanceof Copy && c.getOutTo() == p) {
			c.setOutTo(new Patron(null, null));
			p.getCopiesOut().remove(c);
			//System.out.println(this.toString());
			System.out.println(c.toString());

			return true;
		}

		return false;
	}

	
	
	public static boolean verifyCopy(Copy c) {

		if (FakeDB.getCopyStore().containsKey(c.getCopyID())) {
			return true;
		}
		return false;

	}

}
