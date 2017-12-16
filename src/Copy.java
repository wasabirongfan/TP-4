import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author Raymond Mbah & Rong Fan
 *
 */

public class Copy
{
	private String copyID;
	private String title;
	private Patron outTo;
	private static Calendar dueDate = new GregorianCalendar();

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
	static int DUE_DATE_NUM_MONTHS = 3;

	public Copy(String copyID, String title)
	{
		this.copyID = copyID;
		this.setTitle(title);
		this.outTo = new Patron("", "");
	}

	public String toString()
	{

		return "\nCopy ID: " + this.copyID + ", Title: " + this.getTitle() + ", Patron: " + outTo.getName().toString()
				+ ", Due date: " + this.dueDate.getTime().toString();

	}

	@Override
	public boolean equals(Object o)
	{
		if (o instanceof Copy)
		{
			if (this.copyID == ((Copy) o).copyID)
				return true;
		}
		return false;
	}

	public Patron getOutTo()
	{
		return outTo;
	}

	public void setOutTo(Patron p)
	{

		this.outTo = p;
		FakeDB.getCopyStore().get(this.copyID).outTo = p;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getCopyID()
	{
		return copyID;
	}

	public void setCopyID(String copyID)
	{
		this.copyID = copyID;
	}

	public static Calendar getdueDate()
	{
		return dueDate;
	}

	public void setdueDate(Calendar date)
	{
		this.dueDate = date;
	}

	/** Check Out Copy */
	public static boolean checkCopyOut(Copy c, Patron p)
	{

		if (verifyCopy(c.getCopyID()) && (c instanceof Copy) && (c.getOutTo().getName()).equals(""))
		{

			FakeDB.getCopyStore().get(c.getCopyID()).setOutTo(p);
			FakeDB.getPatronStore().get(p.getPatronID()).getCopiesOut().add(c);
			Calendar calendar = new GregorianCalendar();
			calendar.add(Calendar.MONTH, DUE_DATE_NUM_MONTHS);
			FakeDB.getCopyStore().get(c.getCopyID()).setdueDate(calendar);

			return true;
		}
		StdOut.println("\n======================================================================");
		StdOut.println("...Checkout fails (copy is already checked out)...");
		return false;
	}

	/** Check In Copy */
	public static boolean checkCopyIn(Copy c, Patron p)
	{
		if (verifyCopy(c.getCopyID()) && (c instanceof Copy) && c.getOutTo().equals(p))
		{

			FakeDB.getCopyStore().get(c.getCopyID()).setOutTo(new Patron("", ""));
			FakeDB.getPatronStore().get(p.getPatronID()).getCopiesOut().remove(c);
			while (FakeDB.getCopyStore().get(c.getOutTo().equals(p) == true) != null)
			{
				FakeDB.getCopyStore().get(c.getCopyID()).setdueDate(new GregorianCalendar());
			}
			// FakeDB.getCopyStore().get(c.getCopyID()).setdueDate(new
			// GregorianCalendar().getInstance());
			StdOut.print("...Checkin successful...");
			return true;
		}
		StdOut.println("\n======================================================================");
		StdOut.println("...Checkin fails (copy is not checked out)...");
		return false;
	}

	/** Verify if Copy is in the database */
	public static boolean verifyCopy(String cId)
	{
		if (FakeDB.getCopyStore().containsKey(cId))
		{
			return true;
		}
		StdOut.println("\n======================================================================");
		StdOut.println("Copy does not exist in Database");
		return false;

	}

	/** Verify if Copy is overdue */
	public boolean isOverdue()
	{
		Calendar today = new GregorianCalendar();
		if (today.after(this.getdueDate()))
		{
			return true;
		}
		return false;
	}

	/** Add Copy */
	public static boolean addCopy(String id, String title2)
	{
		Copy newCopy = new Copy(id, title2);
		if (FakeDB.getCopyStore().containsKey(id))
		{
			StdOut.println("Copy with ID = " + id + " already exists");
			return false;
		}
		FakeDB.getCopyStore().put(id, newCopy);
		return true;
	}

	/** Remove Copy */
	public static boolean removeCopy(String id)
	{

		if (Copy.verifyCopy(id))
		{
			FakeDB.getCopyStore().remove(id, FakeDB.getCopy(id));
			return true;
		}
		StdOut.println("Error: copy with ID = " + id + " can not be verified");
		return false;
	}

}
