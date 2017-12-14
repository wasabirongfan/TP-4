import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class Controller_Test
{
	Patron p = new Patron("P1", "Fan47");
	Copy c = new Copy("C3", "Fun with Classes");

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
	Calendar today = new GregorianCalendar();

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
		assertTrue(c instanceof Copy && c.getOutTo().getName() == "");
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
	public void test_verifyCopy()
	{
		Copy c1 = new Copy("C1", "Fun with Objects");
		Copy c2 = new Copy("F3", "Have fun anywhere");
		Copy c3 = new Copy("C3", "More fun with Obejcts");
		c3.setCopyID("C4");

		assertTrue("C1 exists", Copy.verifyCopy(c1));
		assertFalse("F3 does not exists", Copy.verifyCopy(c2));
		assertTrue("C4 does not exist", Copy.verifyCopy(c3));
	}

	@Test
	public void test_checkCopyOut()
	{

		Patron p1 = new Patron("P1", "Eric");
		Patron p2 = new Patron("P2", "Rong");

		Copy c = new Copy("C1", "Fun with Objects");
		Copy c1 = FakeDB.getCopy(c.getCopyID());
		assertTrue("copy C1 checked out to P1", Copy.checkCopyOut(c1, p1));
		assertFalse("copy C1 checked out to P1", Copy.checkCopyOut(c1, p2));

	}

	@Test
	public void test_checkCopyIn()
	{

		Patron p1 = new Patron("P1", "Eric");

		Copy c = new Copy("C1", "Fun with Objects");
		Copy c1 = FakeDB.getCopy(c.getCopyID());

		Patron p2 = new Patron("P2", "Fan47");
		Copy.checkCopyOut(c1, p2);

		assertFalse("copy C1 checked in to P1", Copy.checkCopyIn(c1, p1));
		assertTrue("copy C1 checked in to P2", Copy.checkCopyIn(c1, p2));
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
		Patron p = new Patron("P1", "Eric");

		Copy.checkCopyOut(c, p);
		// c.setOutTo(p);
		Calendar date = new GregorianCalendar();
		date.set(1500, 1, 1);
		c.setdueDate(date);
		StdOut.print(date.getTime());
		StdOut.print(c);
		StdOut.print(p);

		// date.add(date.YEAR, -10);
		// Copy c_test = p.getCopiesOut().get(0);
		// p.getCopiesOut().get(0).setdueDate(date);

		assertTrue("the copy is overdue", p.hasHold());

	}

	@Test
	public void test_verifyWorker()
	{
		Worker worker1 = new Worker("Fan");
		Worker worker2 = new Worker("Rong");

		assertTrue("Fan is a worker", worker1.verifyWorker("Fan"));
		assertFalse("Rong is not a worker", worker1.verifyWorker("Rong"));
	}

	@Test
	public void test_patronstore_setter_getter()
	{
		Patron p1 = new Patron("P1", "Eric");
		p1.setName("Fan47");
		p1.setPatronID("P2");
		ArrayList<Copy> checkout = new ArrayList<Copy>();
		p1.setCopiesOut(checkout);

		assertFalse("P1 name is Eric", p1.getName().equals("Eric"));
		assertTrue("P1 name is Fan47", p1.getName().equals("Fan47"));
		assertTrue("P1 ID is no longer P1", p1.getPatronID().equals("P2"));
		assertTrue("P1 checks out checkout", p1.getCopiesOut().equals(checkout));

	}

	@Test
	public void test_FakeDB_setter_getter()
	{
		Map<String, Patron> patronStore = new HashMap<String, Patron>();
		Patron p1 = new Patron("P1", "Eric");
		patronStore.put("P1", new Patron("P1", "Eric"));
		patronStore.put("P2", new Patron("P2", "Raymond47"));
		FakeDB.setPatronStore(patronStore);

		Map<String, Copy> copyStore = new HashMap<String, Copy>();
		Copy c1 = new Copy("C1", "Fun with Objects");
		copyStore.put("C1", new Copy("C1", "Fun with Objects"));
		copyStore.put("C2", new Copy("C2", "More Fun with Objects"));
		// FakeDB.setCopyStore(copyStore);

		ArrayList<Worker> workerStore = new ArrayList<Worker>();
		Worker worker1 = new Worker("Fan");
		workerStore.add(new Worker("Fan"));
		workerStore.add(new Worker("Raymond"));
		// FakeDB.setWorkerStore(workerStore);

		assertFalse("patronStore is empty", FakeDB.getPatronStore().isEmpty());
		assertTrue("P1 is the same Patron as p1", FakeDB.getPatron("P1").equals(p1));
		assertTrue("patronStore is the same as patronStore", FakeDB.getPatronStore().equals(patronStore));

		assertFalse("copyStore is empty", FakeDB.getCopyStore().isEmpty());
		assertTrue("C1 is the same Copy as c1", FakeDB.getCopy("C1").equals(c1));
		// assertTrue("copyStore is the same as patronStore",
		// FakeDB.getCopyStore().equals(copyStore));

		assertFalse("workerStore is empty", FakeDB.getWorkerStore().isEmpty());
		assertTrue("Fan is the same as worker1", FakeDB.getWorkerNames().get(0).equals("Fan"));
		// assertTrue("workerStore is the same as workerStore",
		// FakeDB.getWorkerStore().equals(workerStore));
	}

}
