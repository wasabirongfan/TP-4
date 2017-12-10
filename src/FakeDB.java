 
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
		patronStore.put("P2", new Patron("P2", "Raymond47"));
		patronStore.put("P3", new Patron("P3", "Fan47"));

		copyStore.put("C1", new Copy("C1", "Fun with Objects"));
		copyStore.put("C2", new Copy("C2", "More Fun with Objects"));
		copyStore.put("C3", new Copy("C3", "Fun with Classes"));
		copyStore.put("C4", new Copy("C3", "Fun with Classes4"));
		copyStore.put("C5", new Copy("C3", "Fun with Classes5"));
		copyStore.put("C6", new Copy("C3", "Fun with Classes6"));
		copyStore.put("C7", new Copy("C3", "Fun with Classes7"));
		copyStore.put("C8", new Copy("C3", "Fun with Classes8"));
		copyStore.put("C9", new Copy("C3", "Fun with Classes9"));
		copyStore.put("C10", new Copy("C3", "Fun with Classes10"));
		copyStore.put("C12", new Copy("C3", "Fun with Classes11"));
		copyStore.put("C13", new Copy("C3", "Fun with Classes12"));
		copyStore.put("C14", new Copy("C3", "Fun with Classes13"));


		workerStore.add(new Worker("Fan"));
		workerStore.add(new Worker("Raymond"));
		workerStore.add(new Worker("Level"));


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
