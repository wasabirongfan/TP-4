import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Controller_Test
{
	@Test
	public void test_initialize()
	{
		Worker worker = new Worker("Fan");
		assertTrue("Valid worker, initialize method runs", Controller.initialize(worker.getName()));
		assertFalse("Invalid worker, initialize method fails", Controller.initialize("Mbah"));
	}

	@Test
	public void test_showmenu()
	{
		assertTrue("Menu shows", Controller.showMenu());
	}

	@Test
	public void test_startcheckout_successful()
	{
		// Valid Patron
		Patron p3 = new Patron("P1", "Fan47");
		Patron patron = FakeDB.getPatronStore().get(p3.getPatronID());

		// Valid Copy
		Copy c5 = new Copy("C5", "Fun with Classes5");
		Copy copy = FakeDB.getCopyStore().get(c5.getCopyID());

		assertTrue("Valid patron and copy, startcheckout runs",
				Controller.StartCheckout(patron.getPatronID(), copy.getCopyID()));

	}

	@Test
	public void test_startcheckout_fails()
	{
		// Invalid Patron
		Patron p2 = new Patron("P1", "Rong");
		Patron patron1 = FakeDB.getPatronStore().get(p2.getPatronID());

		// Invalid Copy
		Copy f2 = new Copy("FF3", "Fun with Objects");

		assertFalse("F2 is invalid, startcheck out fail",
				Controller.StartCheckout(patron1.getPatronID(), f2.getCopyID()));
	}

	@Test
	public void test_startcheckin()
	{
		// Valid Patron
		Patron p1 = new Patron("P1", "Eric");
		// Valid Copy
		Copy c1 = new Copy("C1", "Fun with Objects");
		Copy c2 = new Copy("C2", "More fun with Objects");

		// Invalid Patron
		Patron p2 = new Patron("P1000", "Eric");
		// Invalid Copy
		Copy c3 = new Copy("C10000", "Fun fun fun");

		// Check out C1 to P1
		Copy.checkCopyOut(c1, p1);

		assertTrue("Valid patron and copy, startcheckin runs",
				Controller.startCheckIn(p1.getPatronID(), c1.getCopyID()));
		assertFalse("C2 is not checkout, startchekcin fails",
				Controller.startCheckIn(p1.getPatronID(), c2.getCopyID()));
		assertFalse("P1 is invalid patron, startcheckin fails",
				Controller.startCheckIn(p2.getPatronID(), c1.getCopyID()));
		assertFalse("C3 is invalid copy, startcheck in fails",
				Controller.startCheckIn(p2.getPatronID(), c3.getCopyID()));
	}

	@Test
	public void test_searchpatron()
	{
		// Valid Patron
		Patron p1 = new Patron("P1", "Eric");

		assertTrue("P1 is valid patron, searchpatron runs", Controller.searchPatron(p1.getPatronID()));
		assertFalse("P12 is invalid patron, searchpatron fails", Controller.searchPatron("12"));
	}

	@Test
	public void test_searchcopy()
	{
		// Valid Copy
		Copy c1 = new Copy("C1", "Fun with Objects");

		assertTrue("C1 is valid copy, searchcopy runs", Controller.searchCopy(c1.getCopyID()));
		assertFalse("C100 is invalid copy, searchcopy rails", Controller.searchCopy("C100"));
	}

	@Test
	public void test_sethold()
	{
		// Valid Patron
		Patron p1 = new Patron("P1", "Eric");
		// Invalid Patron
		Patron p2 = new Patron("F2", "Eric");

		assertTrue("P1 has hold", Controller.setHolds(p1.getPatronID(), "t"));
		assertTrue("P1 does not have hold", Controller.setHolds(p1.getPatronID(), "f"));
		assertFalse("P2 is invalid patron, sethold fails", Controller.setHolds(p2.getPatronID(), "t"));
	}

	@Test
	public void test_exit()
	{
		assertTrue(true);
	}

	@Test
	public void test_startcheckout_second_if_false()
	{
		Patron p2 = new Patron("F2", "Eric");
		assertFalse(
				Patron.verifyPatron(p2.getPatronID()) && (FakeDB.getPatronStore().get(p2.getPatronID()).getHasHolds()
						&& FakeDB.getPatronStore().get(p2.getPatronID()).getHasHolds()));
	}

	@Test
	public void test_startcheckout_gethashold()
	{
		// Valid Patron
		Patron p1 = new Patron("P1", "Eric");
		assertFalse(FakeDB.getPatronStore().get(p1.getPatronID()).getHasHolds());
	}

	@Test
	public void test_hasholds_false()
	{
		Patron p2 = new Patron("P2", "Eric");
		assertFalse(FakeDB.getPatronStore().get(p2.getPatronID()).getHasHolds());
	}

	@Test
	public void test_if_checkout_is_false()
	{
		Patron p1 = new Patron("P1", "Eric");
		Copy c1 = new Copy("C1", "Fun with Objects");

		boolean fail = Copy.checkCopyOut(c1, p1) == false;
		assertFalse(fail);
	}

	@Test
	public void test_startcheckout_if()
	{
		Patron p1 = new Patron("P1", "Eric");
		Patron p = FakeDB.getPatronStore().get(p1.getPatronID());

		Copy c1 = new Copy("C1", "Fun with Objects");
		Copy c = FakeDB.getCopyStore().get(c1.getCopyID());

		boolean processHolds = FakeDB.getPatronStore().get(p.getPatronID()).processHolds() == true;
		boolean getHasHolds = FakeDB.getPatronStore().get(p.getPatronID()).getHasHolds() == true;
		assertTrue(Controller.startCheckIn(p.getPatronID(), c.getCopyID()));
	}
}
