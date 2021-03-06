import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author Raymond Mbah & Rong Fan
 *
 */
public class Patron
{
	private String name;
	private String patronID;
	private ArrayList<Copy> copiesOut;
	private static Calendar today = new GregorianCalendar();
	private boolean hasHolds;

	public Patron(String id, String name)
	{
		this.patronID = id;
		this.setName(name);
		this.copiesOut = new ArrayList<Copy>();
		this.hasHolds = false;
	}

	public boolean getHasHolds()
	{
		return this.hasHolds;
	}

	public void setHasHolds(boolean b)
	{
		this.hasHolds = b;
	}

	public String getPatronID()
	{
		return patronID;
	}

	public void setPatronID(String patronID)
	{
		this.patronID = patronID;
	}

	public ArrayList<Copy> getCopiesOut()
	{
		return copiesOut;
	}

	public void setCopiesOut(ArrayList<Copy> copiesOut)
	{
		this.copiesOut = copiesOut;
	}

	@Override
	public boolean equals(Object o)
	{
		if (o instanceof Patron)
		{
			if (((Patron) o).patronID == this.patronID)
				return true;
		}
		return false;
	}

	public String toString()
	{

		return "\nPatron Inormation::\n\tPatron ID: " + this.patronID + ", Patron name: " + this.getName()
				+ " has holds: " + this.hasHolds + ", Patron copies out: " + this.copiesOut.toString();

	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	/** Verify if Patron is in the database */
	public static boolean verifyPatron(String pId)
	{

		if (FakeDB.getPatronStore().containsKey(pId))
		{
			// StdOut.println("Valid patron");
			return true;
		}
		StdOut.println("\n======================================================================");
		StdOut.println("...Patron does not exist in Database...");
		return false;
	}

	/** Verify if Patron has holds */
	public boolean processHolds()
	{
		Calendar today = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");

		for (int i = 0; i < this.getCopiesOut().size(); i++)
		{

			copiesOut.get(i).getdueDate();

			if (today.after(copiesOut.get(i).getdueDate()))
			{
				StdOut.print(this.getName() + " has hold(s)");
				this.hasHolds = true;
				return true;
			}

		}
		this.hasHolds = false;
		return false;
	}

	/** Add Patron to the Database */
	public static boolean addPatron(String id, String name)
	{
		Patron newPatron = new Patron(id, name);
		if (Patron.verifyPatron(id))
		{
			StdOut.print("\n======================================================================\n");
			StdOut.println("...Patron with ID = " + id + " already exists");
			return false;
		}
		FakeDB.getPatronStore().put(id, newPatron);
		return true;
	}

	/** Remove Patron from the Database */
	public static boolean removePatron(String id)
	{

		if (Patron.verifyPatron(id))
		{
			FakeDB.getPatronStore().remove(id, FakeDB.getPatron(id));
			return true;
		}
		StdOut.print("\n======================================================================\n");
		StdOut.println("...Error: Patron with ID = " + id + " can not be verified");
		return false;
	}
}
