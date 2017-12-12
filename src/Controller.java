/**
 * @author Raymond Mbah & Rong Fan
 * 
 *         startup, initialization, and some kind of Presentation Logic for the
 *         running application. It should also provide online instruction and
 *         help when you run it, giving the Patron and Copy identifiers needed
 *         to run the app.
 *
 */
public class Controller
{

	public static void main(String[] args)
	{
		StdOut.print("Pelase Enter you worker name e.g Fan or Raymond: ");
		initialize(StdIn.readString());

		// presentation logic for running the application
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

	private static boolean initialize(String name)
	{
		System.out.println("worker names: " + FakeDB.getWorkerNames().toString());
		while (Worker.validateWorker(name) == false)
		{
			StdOut.println("Incorrect worker name. Pelase Enter a valid you worker name: ");
			name = StdIn.readString();
		}

		return true;
	}

	private static boolean startCheckIn()
	{
		StdOut.println("...Starting in session....");
		StdOut.println("Please enter Patron ID and name: e.g 'P1 Eric'");

		Patron p1 = new Patron(StdIn.readString(), StdIn.readString());
		Copy c1 = new Copy("C1", "Fun with Objects");

		Copy.checkCopyOut(c1, p1);

		while (Patron.verifyPatron(p1) == false)
		{
			System.out.println("Patron Information:\n\tpatron with id: " + p1.getPatronID() + " and name: "
					+ p1.getName() + " doesn't exist in our database");
			StdOut.println("\nPlease enter Patron ID and name: e.g 'P1 Eric'");
			p1 = new Patron(StdIn.readString(), StdIn.readString());
		}
		p1.getCopiesOut().toString();
		StdOut.println("Do you want to check in: " + p1.getCopiesOut() + "\n\nEnter [y] for yes, [n] for no");

		String checkInCopy = StdIn.readString();

		if (checkInCopy.toLowerCase().equals("y"))
		{
			StdOut.println("\nPlease enter copy ID and title e.g : 'C1 ,Fun with Objects' ");
			Copy c = new Copy(StdIn.readString(), StdIn.readString());
			StdOut.println("\ntesting if you can see me here! ");

			while (Copy.verifyCopy(c1) == false)
			{
				System.out.println("Error:\n\tcopy with id: " + c.getCopyID() + " and title: " + c.getTitle()
						+ " doesn't exist in our database");
				StdOut.println("\nPlease enter copy ID and title e.g : 'C1 ,Fun with Objects' ");
				c = new Copy(StdIn.readString(), StdIn.readString());
			}
			if (Copy.checkCopyIn(c1, p1))
			{
				System.out.printf(
						"|+++---------------------------------------------------------------------------------------------------+++|\n");
				System.out.printf(
						"|+++---------------------------------------------------------------------------------------------------+++|\n");
				StdOut.println("Check in successfull!.");
				System.out.println(c.toString());
				StdOut.println(p1.toString());
				System.out.printf(
						"|+++---------------------------------------------------------------------------------------------------+++|\n");
				System.out.printf(
						"|+++---------------------------------------------------------------------------------------------------+++|\n");

			}
			else
			{
				StdOut.println("copy is not checked out to the patron");
			}

		}
		else if (checkInCopy.toLowerCase().equals("n"))
		{
			StdOut.println(p1.getCopiesOut() + "Thank you for using TRL");
		}
		else
		{
			StdOut.println("Error checking in");

		}
		StdOut.println("\ntesting if you can see me here! ");

		return true;
	}

	private static boolean StartCheckout()
	{
		StdOut.println("...Starting checkout session....");

		StdOut.println("Please enter Patron ID and name: e.g 'P1 Eric'");
		Patron p1 = new Patron(StdIn.readString(), StdIn.readString());

		while (Patron.verifyPatron(p1) == false)
		{
			System.out.println("Patron Information:\n\tpatron with id: " + p1.getPatronID() + " and name: "
					+ p1.getName() + " doesn't exist in our database");
			StdOut.println("\nPlease enter Patron ID and name: e.g 'P1 Eric'");
			p1 = new Patron(StdIn.readString(), StdIn.readString());

		}

		System.out.println(p1.toString());
		StdOut.println("\nPlease enter copy ID and title e.g : 'C1 ,Fun with Objects' ");
		Copy c = new Copy(StdIn.readString(), StdIn.readString());

		while (Copy.verifyCopy(c) == false)
		{
			System.out.println("Error:\n\tcopy with id: " + c.getCopyID() + " and title: " + c.getTitle()
					+ " doesn't exist in our database");
			StdOut.println("\nPlease enter copy ID and title e.g : 'C1 ,Fun with Objects' ");
			c = new Copy(StdIn.readString(), StdIn.readString());

		}

		Copy c1 = FakeDB.getCopy(c.getCopyID());
		StdOut.println("......Checking out " + c1.getTitle() + " to " + p1.getName() + ".");

		// check if patron has holds before starting check out
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
		return true;
	}

}
