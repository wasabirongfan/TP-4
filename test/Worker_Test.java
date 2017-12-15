import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Worker_Test {

	@Test
	public void test_verifyWorker()
	{
		Worker worker1 = new Worker("Fan");
		Worker worker2 = new Worker("Rong");
		worker2.setName("Raymond");

		assertTrue("Fan is a worker", worker1.verifyWorker("Fan"));
		assertFalse("Rong is not a worker", worker1.verifyWorker("Rong"));
		assertTrue("Raymond is a worker", worker2.verifyWorker("Raymond"));
	}
	
}
