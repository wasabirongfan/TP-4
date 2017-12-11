import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
	static int DUE_DATE_NUM_MONTHS = 3; //number of months used to set the due date of a copy as from when the copy was checked out

	public Copy(String copyID, String title)
	{
		this.copyID = copyID;
		this.setTitle(title);
		this.outTo = new Patron(null, null);
	}

	public String toString()
	{

		return "Copy Information \n\tcoppID : " + this.copyID + ", Title: " + this.getTitle() + ", Patron: " + outTo.getName()
				+ ", Due date: " + this.dueDate.getTime() ;

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

		//this.dueDate = new GregorianCalendar(2018, 1, 1);
	}

	public static boolean checkCopyOut(Copy c, Patron p)
	{
		// check if the copy is currently available before checking it out
		if (c instanceof Copy && c.getOutTo().getName() == null)
		{
			c.setOutTo(p);
			p.getCopiesOut().add(c);
			//c.getdueDate();
			Calendar calendar = new GregorianCalendar();//default is current date and time
			calendar.add(Calendar.MONTH, DUE_DATE_NUM_MONTHS);//
			c.setdueDate(calendar);
			
			return true;
		}

		return false;
	}

	public static boolean checkCopyIn(Copy c, Patron p)
	{
		System.out.println("...Starting checking in " + c.getTitle());

		// check if the Patron returning the book actually checked it out
		if (c instanceof Copy && c.getOutTo() == p)
		{
			c.setOutTo(new Patron(null, null));
			p.getCopiesOut().remove(c);
			System.out.println(c.toString());

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
	
	
	public boolean isOverdue() {
		Calendar today = new GregorianCalendar();
			if (today.after(this.getdueDate())) { // check if today is after duedate
				return true;
			}
		return false;
	}

}
