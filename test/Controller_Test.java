import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class Controller_Test
{

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
		assertFalse("copy C1 checked to P1", Copy.checkCopyIn(c1, p1));

	}

	@Test
	public void test_notOverdue()
	{
		Calendar today = new GregorianCalendar(2017, 12, 10);
		assertTrue("the copy is not overdue", Patron.hold());
	}

	@Test
	public void test_overdue()
	{
		Calendar today = new GregorianCalendar(2018, 1, 10);
		assertTrue("the copy is overdue", Patron.hold());
	}

}
