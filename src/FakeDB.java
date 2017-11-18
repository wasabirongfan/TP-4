import java.util.HashMap;
import java.util.Map;

public class FakeDB
{
	private static Map<String, Patron> patronStore;
	private static Map<String, Copy> copyStore;

	static // the following runs once when class is loaded: "static initializer"
	{
		patronStore = new HashMap<String, Patron>();
		copyStore = new HashMap<String, Copy>();

		patronStore.put("P1", new Patron("P1", "Eric"));
		copyStore.put("C1", new Copy("C1", "Fun with Objects"));
		copyStore.put("C2", new Copy("C2", "More Fun with Objects"));
	}

	public static Patron getPatron(String patronID)
	{
		return patronStore.get(patronID);
	}

	public static Copy getCopy(String copyID)
	{
		return copyStore.get(copyID);
	}

	public static Map<String, Patron> getPatronStore() {
		return patronStore;
	}

	public static void setPatronStore(Map<String, Patron> patronStore) {
		FakeDB.patronStore = patronStore;
	}

	public static Map<String, Copy> getCopyStore() {
		return copyStore;
	}

	public static void setCopyStore(Map<String, Copy> copyStore) {
		FakeDB.copyStore = copyStore;
	}
	
	

}
