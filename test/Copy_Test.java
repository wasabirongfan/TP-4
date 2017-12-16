import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class Copy_Test
{

	// Patron p = new Patron("P2", "Eric");
	// Copy c = new Copy("C3", "Fun with Classes");

	// SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
	// Calendar today = new GregorianCalendar();

	@Test
	public void test_copyAvailable()
	{
		// Valid Patron
		Patron p1 = new Patron("P1", "Eric");

		// Valid Copy
		Copy c1 = new Copy("C1", "Fun with Objects");
		Copy c3 = new Copy("C3", "More fun with Obejcts");

		c1.setOutTo(p1);
		assertTrue(c1 instanceof Copy);

		c1.setCopyID("F3");
		assertTrue(c1.getCopyID().equals("F3"));
	}

	@Test
	public void test_verifyCopy()
	{
		// Valid Copy
		Copy c1 = new Copy("C1", "Fun with Objects");
		Copy c3 = new Copy("C3", "More fun with Obejcts");

		// Invalid Copy
		Copy c2 = new Copy("F3", "Have fun anywhere");

		assertTrue("C1 exists", Copy.verifyCopy(c1.getCopyID()));
		assertFalse("F3 does not exists", Copy.verifyCopy(c2.getCopyID()));

	}

	@Test
	public void test_checkCopyOut()
	{

		// Valid Patron
		Patron p1 = new Patron("P1", "Eric");
		Patron p2 = new Patron("P2", "Raymond47");

		// Valid Copy
		Copy c1 = new Copy("C1", "Fun with Objects");

		assertTrue("C1 checked out to P1", Copy.checkCopyOut(c1, p1));
		assertTrue("C1 checked out to P1", Copy.checkCopyOut(c1, p2));

	}

	@Test
	public void test_checkCopyIn()
	{

		// Valid Patron
		Patron p1 = new Patron("P1", "Eric");
		Patron patron = FakeDB.getPatronStore().get(p1.getPatronID());

		// Valid Copy
		Copy c1 = new Copy("C3", "Fun with Classes");
		Copy copy = FakeDB.getCopyStore().get(c1.getCopyID());

		Copy.checkCopyOut(copy, patron);

		assertTrue("C3 checked in to P1 runs", Copy.checkCopyIn(copy, patron));
		assertFalse("C1 checked in to P1 fails", Copy.checkCopyIn(c1, p1));

	}

	@Test
	public void test_notOverdue()
	{
		// Valid Copy
		Copy c5 = new Copy("C5", "Fun with Classes5");
		Calendar date = new GregorianCalendar().getInstance();
		c5.setdueDate(date);
		assertFalse("the copy is not overdue", c5.isOverdue());
	}

	@Test
	public void test_overdue()
	{
		// Valid Copy
		Copy c1 = new Copy("C1", "Fun with Object");

		Calendar date = new GregorianCalendar();
		date.add(date.YEAR, -1);
		c1.setdueDate(date);
		assertTrue("the copy is overdue", c1.isOverdue());
	}

	@Test
	public void test_update_duedate_after_checkin()
	{
		// Valid Patron
		Patron p1 = new Patron("P1", "Eric");
		Patron patron = FakeDB.getPatronStore().get(p1.getPatronID());

		// Valid Copy
		Copy c1 = new Copy("C3", "Fun with Classes");
		Copy copy = FakeDB.getCopyStore().get(c1.getCopyID());

		Copy.checkCopyOut(copy, patron);
		Copy.checkCopyIn(copy, patron);

		assertTrue(FakeDB.getCopyStore().get(copy.getCopyID()).getdueDate().equals(copy.getdueDate()));

	}

}
