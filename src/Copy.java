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
	private static Calendar dueDate;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
	static int DUE_DATE_NUM_MONTHS = 3;

	public Copy(String copyID, String title)
	{
		this.copyID = copyID;
		this.setTitle(title);
		this.outTo = new Patron("", "");
		this.dueDate = new GregorianCalendar();
	}

	public String toString()
	{

		return "Copy Information:: \n\tcoppID : " + this.copyID + ", Title: " + this.getTitle() + ", Patron: "
				+ outTo.getName().toString() + ", Due date: " + this.dueDate.getTime().toString();

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

	public void setOutTo(Patron outTo)
	{
		this.outTo = outTo;
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

	public static boolean checkCopyOut(Copy c, Patron p)
	{
		if (c instanceof Copy && c.getOutTo().getName() == "")
		{

			FakeDB.getCopyStore().get(c.getCopyID()).setOutTo(p);
			;
			FakeDB.getPatronStore().get(p.getPatronID()).getCopiesOut().add(c);
			;
			Calendar calendar = new GregorianCalendar();
			calendar.add(Calendar.MONTH, DUE_DATE_NUM_MONTHS);//
			FakeDB.getCopyStore().get(c.getCopyID()).setdueDate(calendar);

			return true;
		}

		return false;
	}

	public static boolean checkCopyIn(Copy c, Patron p)
	{
		System.out.println("...Starting checking in " + c.getTitle());

		if (c instanceof Copy && c.getOutTo() == p)
		{

			FakeDB.getCopyStore().get(c.getCopyID()).setOutTo(new Patron("", ""));
			FakeDB.getPatronStore().get(p.getPatronID()).getCopiesOut().remove(c);

			return true;
		}

		return false;
	}

	public static boolean verifyCopy(Copy c)
	{

		if (FakeDB.getCopyStore().containsKey(c.getCopyID()))
		{
			return true;
		}
		return false;

	}

	public boolean isOverdue()
	{
		Calendar today = new GregorianCalendar();
		if (today.after(this.getdueDate()))
		{
			return true;
		}
		return false;
	}

}
