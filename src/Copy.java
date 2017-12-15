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
	private static Calendar dueDate = new GregorianCalendar() ;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
	static int DUE_DATE_NUM_MONTHS = 3;

	public Copy(String copyID, String title)
	{
		this.copyID = copyID;
		this.setTitle(title);
		this.outTo = new Patron("", "");
		//this.dueDate = new GregorianCalendar();
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
		FakeDB.getCopyStore().get(this.copyID).outTo = p; //update db
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
		
		if ( verifyCopy(c.getCopyID()) && (c instanceof Copy ) && (c.getOutTo().getName()).equals(""))
		{

			FakeDB.getCopyStore().get(c.getCopyID()).setOutTo(p);
			FakeDB.getPatronStore().get(p.getPatronID()).getCopiesOut().add(c);
			Calendar calendar = new GregorianCalendar();
			calendar.add(Calendar.MONTH, DUE_DATE_NUM_MONTHS);
			FakeDB.getCopyStore().get(c.getCopyID()).setdueDate(calendar);
			StdOut.println("..?????.checkout successfull....");

			return true;
		}
		return false;
	}

	public static boolean checkCopyIn(Copy c, Patron p)
	{
		System.out.println("...Starting checking in " + c.getTitle());

//		if (p.hasHold())
//			return false;

		if ( verifyCopy(c.getCopyID()) && (c instanceof Copy) && c.getOutTo().equals(p))
		{

			FakeDB.getCopyStore().get(c.getCopyID()).setOutTo(new Patron("", ""));
			FakeDB.getPatronStore().get(p.getPatronID()).getCopiesOut().remove(c);
			StdOut.print("...Checkin successful...");
			return true;
		}
		StdOut.println("...Checkin fails...");
		return false;
	}

	public static boolean verifyCopy(String cId)
	{
		//StdOut.println("----" + c);
		if (FakeDB.getCopyStore().containsKey(cId))
		{
			//StdOut.println("***" + c.getCopyID());
			StdOut.println("truuu......");
			return true;
		}
		StdOut.println("Invalid copy!!!");
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
