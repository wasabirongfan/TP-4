import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

public class Copy_Test {

	Patron p = new Patron("P2", "Eric");
	Copy c = new Copy("C3", "Fun with Classes");

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
	Calendar today = new GregorianCalendar();
	
//	@Before
//	public void initialize()
//	{
//		c.checkCopyOut(c, p);
//	}
	
	@Test
	public void test_copyAvailable()
	{
		Patron p1 = new Patron("P1", "Eric");
		Copy c = new Copy("C1", "Fun with Objects");
		assertTrue(c instanceof Copy && c.getOutTo().getName() == "");
	}
	
	@Test
	public void test_verifyCopy()
	{
		Copy c1 = new Copy("C1", "Fun with Objects");
		Copy c2 = new Copy("F3", "Have fun anywhere");
		Copy c3 = new Copy("C3", "More fun with Obejcts");
		c3.setCopyID("C4");

		assertTrue("C1 exists", Copy.verifyCopy(c1.getCopyID()));
		assertFalse("F3 does not exists", Copy.verifyCopy(c2.getCopyID()));
		assertTrue("C4 does not exist", Copy.verifyCopy(c3.getCopyID()));
	}
	
	@Test
	public void test_checkCopyOut()
	{

		Patron p1 = new Patron("P1", "Eric");
		Patron p2 = new Patron("P2", "Raymond47");

		Copy c = new Copy("C1", "Fun with Objects");
		Copy c1 = FakeDB.getCopy(c.getCopyID());
		assertTrue("copy C1 checked out to P1", Copy.checkCopyOut(c1, p1));
		assertFalse("copy C1 checked out to P1", Copy.checkCopyOut(c1, p2));

	}
	

	@Test
	public void test_checkCopyIn()
	{

		Patron p1 = new Patron("P1", "Eric");

		Copy c3 = new Copy("C3", "Fun with classes");
		Copy c1 = FakeDB.getCopy(c.getCopyID());
		Copy c22 = new Copy("C2", "More Fun with Object");

		Copy c2 = FakeDB.getCopy(c22.getCopyID()); //

		Patron p2 = new Patron("P2", "Fan47");
		Copy.checkCopyOut(c2, p2);

		assertFalse("copy C1 checked in to P1", Copy.checkCopyIn(c3, p1));
		assertTrue("copy C2 checked in to P2", Copy.checkCopyIn(c2, p2));
	}
	
	@Test
	public void test_notOverdue()
	{

		assertFalse("the copy is not overdue", c.isOverdue());
	}
	
	@Test
	public void test_overdue()
	{
		Calendar date = new GregorianCalendar();
		date.add(date.YEAR, -1);
		c.setdueDate(date);
		assertTrue("the copy is overdue", c.isOverdue());
	}

}
