
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

import java.util.ArrayList;

public class Patron {
	private String name;
	private String patronID;
	private ArrayList<Copy> copiesOut;

	public Patron(String id, String name) {
		// finish this
		this.patronID = id;
		this.setName(name);
		this.copiesOut = new ArrayList<Copy>();
	}

	public String getPatronID() {
		return patronID;
	}

	public void setPatronID(String patronID) {
		this.patronID = patronID;
	}

	public ArrayList<Copy> getCopiesOut() {
		return copiesOut;
	}

	public void setCopiesOut(ArrayList<Copy> copiesOut) {
		this.copiesOut = copiesOut;
	}

	@Override
	public boolean equals(Object o) {
		// finish this: two are equals iff same patron ID
		if (o instanceof Patron) {
			if (((Patron) o).patronID == this.patronID)
				return true;
		}
		return false;
	}

	public String toString() {
		// finish this: return basic Patron info as String

		// return "A description of this Patron's current state.";
		return "Patron ID: " + this.patronID + ", Patron name: " + this.getName() + ", Patron copies out: "
				+ this.copiesOut.toString();

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
