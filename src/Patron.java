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
	static Calendar today = GregorianCalendar.getInstance();;

	public Patron(String id, String name)
	{
		this.patronID = id;
		this.setName(name);
		this.copiesOut = new ArrayList<Copy>();
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

		return "Patron ID: " + this.patronID + ", Patron name: " + this.getName() + ", Patron copies out: "
				+ this.copiesOut.toString();

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

		if (FakeDB.getPatronStore().containsKey(p.getPatronID()))
		{
			return true;
		}
		return false;
	}

	public static boolean hold()
	{
		if (!(today.after(Copy.getdueDate())))
		{
			return true;
		}
		return false;
	}
}
