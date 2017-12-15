import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class FakeDB_Test {

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
		//FakeDB.setCopyStore(copyStore);

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
