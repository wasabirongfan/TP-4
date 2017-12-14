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

	public static void main1(String[] args)
	{
		initialize();

		String choice = "";
		while (!choice.toLowerCase().equals("o") && !choice.toLowerCase().equals("i"))
		{
			StdOut.println("what will you like to do: Enter 'i' for checkin or 'o' for checkout");
			choice = StdIn.readString();
			if (choice.toLowerCase().equals("i"))
			{
				startCheckIn();
			}
			else if (choice.toLowerCase().equals("o"))
			{
				StartCheckout();
			}
			else
				StdOut.println("check in (i) and checkout (o) are the only available functionality for now. "
						+ "More functionality will be available later");
		}

	}

	/** Initialization **/
	public static boolean initialize()
	{
		printPatronStore();
		printCopyStore();

		StdOut.print("Pelase Enter you worker name e.g Fan or Raymond: ");
		System.out.println("worker names: " + FakeDB.getWorkerNames().toString());
		String name = StdIn.readString();
		while (Worker.verifyWorker(name) == false)
		{
			StdOut.println("Incorrect worker name. Pelase Enter a valid you worker name: ");
			name = StdIn.readString();
		}

		return true;
	}

	/** Display the main menu **/
	public static void showMenu()
	{
		System.out.println("Menu\n====");
		System.out.println("Enter 1: To checkout");
		System.out.println("Enter 2: To checkin");
		System.out.println("Enter 3: To search patron");
		StdOut.println("Enter 4: To search copy");
		StdOut.println("Enter 5: To exit");

	}

	/** Search Patron **/
	public static void searchPatron()
	{

		StdOut.println("\n...Searching patron...\n");
		StdOut.println("Please enter Patron ID and name: e.g 'P1 Eric'");
		Patron p1 = new Patron(StdIn.readString(), StdIn.readString());

		while (Patron.verifyPatron(p1) == false)
		{
			System.out.println("Patron Information:\n\tpatron with id: " + p1.getPatronID() + " and name: "
					+ p1.getName() + " doesn't exist in our database");
			StdOut.println("\nPlease enter Patron ID and name: e.g 'P1 Eric'");
			p1 = new Patron(StdIn.readString(), StdIn.readString());

		}

		p1 = FakeDB.getPatronStore().get(p1.getPatronID());
		StdOut.println(p1);
	}

	/** Search Copy **/
	public static void searchCopy()
	{
		StdOut.println("\n...Searching copy...\n");
		StdOut.println("\nPlease enter copy ID e.g : C1 ");

		String id = StdIn.readString();
		String title = "";
		Copy c = new Copy(id, title);

		while (Copy.verifyCopy(c) == false)
		{
			System.out.println("Error:\n\tcopy with id: " + c.getCopyID() + " and title: " + c.getTitle()
					+ " doesn't exist in our database");
			StdOut.println("\nPlease enter copy ID and title e.g : C1 ");
			id = StdIn.readString();
			title = "";
			c = new Copy(id, title);

		}

		StdOut.println(FakeDB.getCopyStore().get(c.getCopyID()));
	}

	/** Exit **/
	public static void exit()
	{
		StdOut.println("...QUITTING TRLApp...");
		System.exit(1);
	}

	/** Start Check In Session **/
	public static boolean startCheckIn()
	{
		StdOut.println("...Starting check in session....");

		StdOut.println("Please enter Patron ID and name: e.g 'P1 Eric'");

		Patron p1 = new Patron(StdIn.readString(), StdIn.readString());

		verifyPatron(p1);

		String option = "y";
		while (option.equals("y"))
		{
			p1 = FakeDB.getPatronStore().get(p1.getPatronID());
			System.out.println(p1.toString());

			StdOut.println("\nPlease enter copy ID e.g : C1 ");
			String id = StdIn.readString();
			String title = "";
			Copy c = new Copy(id, title);

			c = verifyCopy(c);

			Copy c1 = FakeDB.getCopyStore().get(c.getCopyID());
			StdOut.println("......Checking in " + c1.getTitle() + " from " + p1.getName() + ".");

			if (p1.hasHold() == true)
			{
				StdOut.print(p1.getName() + " has hold(s)");
				return false;
			}
			else
			{

				if (Copy.checkCopyIn(c1, p1))
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
				}
				else
				{

				}

			}

			StdOut.println("Check In another copy? (y/n):");
			option = StdIn.readString();

		}

		return true;
	}

	/** Start Check Out Session **/
	public static boolean StartCheckout()
	{
		StdOut.println("...Starting checkout session....");

		StdOut.println("\nPlease enter Patron ID and name: e.g 'P1 Eric'");
		Patron p1 = new Patron(StdIn.readString(), StdIn.readString());

		verifyPatron(p1);

		p1 = FakeDB.getPatronStore().get(p1.getPatronID());

		String option = "y";
		while (option.equals("y"))
		{

			System.out.println(p1.toString());

			StdOut.println("\nPlease enter copy ID e.g : C1 ");
			String id = StdIn.readString();
			String title = "";
			Copy c = new Copy(id, title);

			c = verifyCopy(c);

			Copy c1 = FakeDB.getCopy(c.getCopyID());
			StdOut.println("......Checking out " + c1.getTitle() + " to " + p1.getName() + ".");

			if (p1.hasHold() == true)
			{
				StdOut.print(p1.getName() + " has hold(s)");
			}
			else
			{

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
				}
				else
				{

				}

			}

			StdOut.println("Check out another copy? (y/n):");
			option = StdIn.readString();

		}

		return true;
	}

	/** Verify Patron **/
	private static void verifyPatron(Patron p1)
	{
		while (Patron.verifyPatron(p1) == false)
		{

			System.out.println("Patron Information:\n\tpatron with id: " + p1.getPatronID() + " and name: "
					+ p1.getName() + " doesn't exist in our database");
			StdOut.println("\nPlease enter Patron ID and name: e.g 'P1 Eric'");
		}
	}

	/** Verify Copy **/
	private static Copy verifyCopy(Copy c)
	{
		String id;
		String title;
		while (Copy.verifyCopy(c) == false)
		{
			System.out.println("Error:\n\tcopy with id: " + c.getCopyID() + " and title: " + c.getTitle()
					+ " doesn't exist in our database");
			StdOut.println("\nPlease enter copy ID and title e.g : C1 ");
			id = StdIn.readString();
			StdOut.println("Pleae enter title e.g : 'Fun with Objects' ");
			title = StdIn.readLine();
			c = new Copy(id, title);

		}
		return c;
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
		for (String key1 : FakeDB.getPatronStore().keySet())
		{
			StdOut.print(FakeDB.getPatronStore().get(key1) + "\n");
		}
	}
}
