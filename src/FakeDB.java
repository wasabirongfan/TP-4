 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Raymond Mbah & Rong Fan
 *
 */
public class FakeDB
{
	private static Map<String, Patron> patronStore;
	private static Map<String, Copy> copyStore;
	private static ArrayList<Worker> workerStore;

	static
	{
		patronStore = new HashMap<String, Patron>();
		copyStore = new HashMap<String, Copy>();
		workerStore = new ArrayList<Worker>();

		patronStore.put("P1", new Patron("P1", "Eric"));
		copyStore.put("C1", new Copy("C1", "Fun with Objects"));
		copyStore.put("C2", new Copy("C2", "More Fun with Objects"));

		workerStore.add(new Worker("Fan"));
		workerStore.add(new Worker("Raymond"));

	}

	public static Patron getPatron(String patronID)
	{
		return patronStore.get(patronID);
	}

	public static Copy getCopy(String copyID)
	{
		return copyStore.get(copyID);
	}

	public static Map<String, Patron> getPatronStore()
	{
		return patronStore;
	}

	public static void setPatronStore(Map<String, Patron> patronStore)
	{
		FakeDB.patronStore = patronStore;
	}

	public static Map<String, Copy> getCopyStore()
	{
		return copyStore;
	}

	public static void setCopyStore(Map<String, Copy> copyStore)
	{
		FakeDB.copyStore = copyStore;
	}

	public static ArrayList<Worker> getWorkerStore()
	{
		return workerStore;
	}

	public static void setWorkerStore(ArrayList<Worker> workerStore)
	{
		FakeDB.workerStore = workerStore;
	}

}
