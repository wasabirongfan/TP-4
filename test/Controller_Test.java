import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

public class Controller_Test
{

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");

	Calendar today = new GregorianCalendar();
	Copy c = new Copy("C3", "Fun with Classes");
	Patron p = new Patron("P1", "Fan47");

	@Before
	public void initialize()
	{
		c.checkCopyOut(c, p);
	}

	@Test
	public void test_copyAvailable()
	{
		Patron p1 = new Patron("P1", "Eric");
		Copy c = new Copy("C1", "Fun with Objects");
		assertTrue(c instanceof Copy && c.getOutTo().getName() == null);
	}

	@Test
	public void test_verifyPatron()
	{
		Patron p1 = new Patron("P1", "Eric");
		Patron p2 = new Patron("P2", "Rong");

		assertTrue("P1 exists", Patron.verifyPatron(p1));
		assertFalse("P2 does not exists", Patron.verifyPatron(p2));

	}

	@Test
	public void test_checkCopyOut()
	{

		Patron p1 = new Patron("P1", "Eric");
		Patron p2 = new Patron("P2", "Rong");

		Copy c = new Copy("C1", "Fun with Objects");
		Copy c1 = FakeDB.getCopy(c.getCopyID());
		assertTrue("copy C1 checked to P1", Copy.checkCopyOut(c1, p1));
		assertFalse("copy C1 checked to P1", Copy.checkCopyOut(c1, p2));

	}

	@Test
	public void test_checkCopyIn()
	{

		Patron p1 = new Patron("P1", "Eric");

		Copy c = new Copy("C1", "Fun with Objects");
		Copy c1 = FakeDB.getCopy(c.getCopyID());

		Patron p2 = new Patron("P2", "Fan47");
		Copy.checkCopyOut(c1, p2);

		assertFalse("copy C1 checked to P1", Copy.checkCopyIn(c1, p1));
		assertTrue("copy C1 checked to P1", Copy.checkCopyIn(c1, p2));

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

	@Test
	public void test_does_not_hasHold()
	{
		Calendar today = new GregorianCalendar();
		Copy c = new Copy("C3", "Fun with Classes");
		Patron p = new Patron("P1", "Fan47");
		c.checkCopyOut(c, p);
		assertFalse("P1 does not have holds", p.hasHold());
		StdOut.println("done testing does not have holds");
	}

	@Test
	public void test_hasHold()
	{
		Copy c = new Copy("C3", "Fun with Classes");
		Patron p = new Patron("P1", "Fan47");
		c.checkCopyOut(c, p);

		Calendar date = new GregorianCalendar();

		date.add(date.YEAR, -10);
		Copy c_test = p.getCopiesOut().get(0);
		p.getCopiesOut().get(0).setdueDate(date);

		assertTrue("the copy is overdue", p.hasHold());

	}

}
