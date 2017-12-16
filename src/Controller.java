/**
 * @author Raymond Mbah & Rong Fan
 * 
 *         startup, initialization, and some kind of Presentation Logic for the
 *         running application. It should also provide online instruction and
 *         help when you run it, giving the Patron and Copy identifiers needed
 *         to run the application.
 *
 */

public class Controller
{

	/** Initialization **/
	public static boolean initialize(String workerName)
	{

		if (Worker.verifyWorker(workerName))
		{
			printPatronStore();
			printCopyStore();
			return true;

		}
		else
		{
			return false;
		}
	}

	/** Display the main menu **/
	public static boolean showMenu()
	{
		System.out.println("Menu\n====");
		System.out.println("Enter 1: To checkout");
		System.out.println("Enter 2: To checkin");
		System.out.println("Enter 3: To search patron");
		StdOut.println("Enter 4: To search copy");
		StdOut.println("Enter 5: To set hold");
		StdOut.println("Enter 6: To add copy");
		StdOut.println("Enter 7: To remove copy");
		StdOut.println("Enter 8: To add patron");
		StdOut.println("Enter 9: To remove patron");
		StdOut.println("Enter 10: To exit");
		return true;

	}

	/** Search Patron **/
	public static boolean searchPatron(String pId)
	{

		if (Patron.verifyPatron(pId))
		{
			StdOut.println(FakeDB.getPatronStore().get(pId));

			return true;
		}
		else
		{
			StdOut.println(
					("Patron Information:\n\tpatron with id: " + pId + " doesn't exist in our database"));
			//StdOut.println("\nPlease enter Patron ID (e.g. P1): ");
			return false;
		}
		

	}

	/** Search Copy **/
	public static boolean searchCopy(String cId)
	{

		if (Copy.verifyCopy(cId))
		{
			StdOut.println(FakeDB.getCopyStore().get(cId));
			return true;
		}
		else
		{
			StdOut.println(
			"Copy information: \n\tcopy with id: " + cId + " doesn't exist in our database");
			StdOut.println("\nPlease enter Copy ID: ");
			return false;
		}
	

	}

	/** Start Check In Session **/
	public static boolean startCheckIn(String pId1, String cId1)
	{

		if (Patron.verifyPatron(pId1) && FakeDB.getPatronStore().get(pId1).processHolds()
				&& FakeDB.getPatronStore().get(pId1).getHasHolds() == true)
		{
			return false;
		}
		else if ((Copy.verifyCopy(cId1) && Patron.verifyPatron(pId1)) == false)
		{
			return false;
		}

		else
		{
			Patron p1 = FakeDB.getPatronStore().get(pId1);

			StdOut.println(FakeDB.getPatronStore().get(pId1).toString());
			Copy c1 = FakeDB.getCopy(cId1);
			StdOut.println("......Checking in " + c1.getTitle() + " to " + p1.getName() + ".");

			if (Copy.checkCopyIn(FakeDB.getCopy(cId1), FakeDB.getPatronStore().get(pId1)))
			{
				System.out.printf(
						"|+++---------------------------------------------------------------------------------------------------+++|\n");
				System.out.printf(
						"|+++---------------------------------------------------------------------------------------------------+++|\n");
				StdOut.println("Check in successfull!.");
				System.out.println(FakeDB.getPatronStore().get(p1.getPatronID()).toString());
				StdOut.println(FakeDB.getPatronStore().get(p1.getPatronID()));
				System.out.printf(
						"|+++---------------------------------------------------------------------------------------------------+++|\n");
				System.out.printf(
						"|+++---------------------------------------------------------------------------------------------------+++|\n");
				return true;
			}
			else
			{
				return false;
			}
		}

	}

	/** Start Check Out Session **/
	public static boolean StartCheckout(String pId, String cId)
	{

		if (Patron.verifyPatron(pId) && (FakeDB.getPatronStore().get(pId).getHasHolds()
				&& FakeDB.getPatronStore().get(pId).getHasHolds()) == true)
		{
			StdOut.println("Sorry can't " + pId + " has holds");
			return false;
		}
		else if ((Copy.verifyCopy(cId) && Patron.verifyPatron(pId)) == false)
		{
			return false;
		}

		else
		{

			Patron p1 = FakeDB.getPatronStore().get(pId);

			StdOut.println(FakeDB.getPatronStore().get(pId).toString());
			System.out.println(p1.toString());
			String title = "";
			Copy c = new Copy(cId, title);
			Copy c1 = FakeDB.getCopy(c.getCopyID());
			StdOut.println("......Checking out " + c1.getTitle() + " to " + p1.getName() + ".");

			if (Copy.checkCopyOut(c1, p1))
			{
				System.out.printf(
						"|+++---------------------------------------------------------------------------------------------------+++|\n");
				System.out.printf(
						"|+++---------------------------------------------------------------------------------------------------+++|\n");
				StdOut.println("Check out successfull!.");
				System.out.println(c1.toString());
				StdOut.println(p1.toString());
				System.out.printf(
						"|+++---------------------------------------------------------------------------------------------------+++|\n");
				System.out.printf(
						"|+++---------------------------------------------------------------------------------------------------+++|\n");
				return true;
			}
			else
			{
				return false;
			}

		}

	}

	/** Display Copies **/
	private static void printCopyStore()
	{
		for (String key2 : FakeDB.getCopyStore().keySet())
		{
			StdOut.print("\n" + FakeDB.getCopyStore().get(key2) + "\n");
		}
	}

	/** Display Patrons **/
	private static void printPatronStore()
	{
		StdOut.print("Datebase information as displayed: \n==========================================");
		for (String key1 : FakeDB.getPatronStore().keySet())
		{
			StdOut.print(FakeDB.getPatronStore().get(key1) + "\n");
		}
	}

	/** Set Holds **/
	public static boolean setHolds(String pId, String value)
	{
		boolean b;
		if (Patron.verifyPatron(pId))
		{
			if (value.equals("t"))
			{
				b = true;
			}
			else
			{
				b = false;
			}
			FakeDB.getPatron(pId).setHasHolds(b);
			
			StdOut.println("...Holds updated...");
			StdOut.println(FakeDB.getPatronStore().get(pId));
			
			return true;
		}
		StdOut.println("...Sorry, can't update holds...");
		return false;
	}
	
	
	public static boolean addCopy(String cId, String title) {
		if (Copy.addCopy(cId, title)) {
			StdOut.println(title + " successfully added");
			StdOut.println(FakeDB.getCopyStore().get(cId));
			return true;
		} else {
			StdOut.println("...Sorry copy not added");
			return false;
		}
	}

	public static boolean removeCopy(String cId) {
		if (Copy.removeCopy(cId)) {
			StdOut.println(cId + " ....successfully removed");
			return true;
		} else {
			StdOut.println("Sorry copy not removed. Probably it doesn't exists");
			return false;
		}
		
	}

	public static boolean addPatron(String pId, String name) {
		if (Patron.addPatron(pId, name)) {
			StdOut.println(name + " successfully added");
			StdOut.println(FakeDB.getPatronStore().get(pId));
			return true;
		} else {
			StdOut.println("...Sorry Patron not added.");
			return false;
		}
		
	}

	public static boolean removePatron(String pId) {
		if (Patron.removePatron(pId)) {
			StdOut.println(pId + " ....successfully removed");
			return true;
		} else {
			StdOut.println("....Sorry Patron not removed. Probably it doesn't exists");
			return false;
		}
		
	}
}
