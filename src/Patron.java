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

	public Patron(String id, String name)
	{
		this.patronID = id;
		this.setName(name);
		this.copiesOut = new ArrayList<Copy>();
	}

	private Calendar GregorianCalendar(int year, int month, int day)
	{
		return null;
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
				+ ", Patron copies out: " + this.copiesOut.toString();

	}
	

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public static boolean verifyPatron(Patron p)
	{

		String pId = p.getPatronID();
		String pName = p.getName().toString();

		if (FakeDB.getPatronStore().containsKey(pId) && (FakeDB.getPatronStore().get(pId).getName().equals(pName)))
		{
			return true;
		}
		return false;
	}

	public boolean hasHold()
	{
		Calendar today = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");

		// loop throug patron's books and check if patron has any holds(copies
		// past due)
		for (int i = 0; i < this.getCopiesOut().size(); i++)
		{
			copiesOut.get(i).getdueDate();

			if (today.after(copiesOut.get(i).getdueDate()))
			{ // check if today is after duedate
				return true;
			}

		}

		return false;
	}
}
