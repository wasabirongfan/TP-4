/**
 * @author Raymond Mbah & Rong Fan
 * 
 *         startup, initialization, and some kind of Presentation Logic for the
 *         running application. It should also provide online instruction and
 *         help when you run it, giving the Patron and Copy identifiers needed
 *         to run the application.
 *
 */

public class Controller {

	/** Initialization **/
	public static boolean initialize(String workerName) {

		if (Worker.verifyWorker(workerName)) {
			printPatronStore();
			printCopyStore();
			return true;

		} else {
			return false;
		}
		// System.out.println("worker names: " + FakeDB.getWorkerNames().toString());
		// String name = StdIn.readString();
		// while (Worker.verifyWorker(name) == false) {
		// StdOut.println("Incorrect worker name. Pelase Enter a valid you worker name:
		// ");
		// name = StdIn.readString();
		// }
		//
		// return true;
	}

	/** Display the main menu **/
	public static boolean showMenu() {
		System.out.println("Menu\n====");
		System.out.println("Enter 1: To checkout");
		System.out.println("Enter 2: To checkin");
		System.out.println("Enter 3: To search patron");
		StdOut.println("Enter 4: To search copy");
		StdOut.println("Enter 5: To exit");
		return true;

	}

	/** Search Patron **/
	public static boolean searchPatron(String pId) {

		// Patron p1 = new Patron(StdIn.readString(), StdIn.readString());

		if (Patron.verifyPatron(pId)) {
			return true;
		} else {
			return false;
		}

		// while (Patron.verifyPatron(p1.getPatronID()) == false) {
		// System.out.println("Patron Information:\n\tpatron with id: " +
		// p1.getPatronID() + " and name: "
		// + p1.getName() + " doesn't exist in our database");
		// StdOut.println("\nPlease enter Patron ID and name: e.g 'P1 Eric'");
		// p1 = new Patron(StdIn.readString(), StdIn.readString());
		// }
		// p1 = FakeDB.getPatronStore().get(p1.getPatronID());
		// StdOut.println(p1);
		// return false;
	}

	/** Search Copy **/
	public static boolean searchCopy(String cId) {

		if (Copy.verifyCopy(cId)) {
			return true;
		} else {
			return false;
		}
		// StdOut.println("\n...Searching copy...\n");
		// StdOut.println("\nPlease enter copy ID e.g : C1 ");
		//
		// String id = StdIn.readString();
		// String title = "";
		// Copy c = new Copy(id, title);
		//
		// while (Copy.verifyCopy(c.getCopyID()) == false) {
		// System.out.println("Error:\n\tcopy with id: " + c.getCopyID() + " and title:
		// " + c.getTitle()
		// + " doesn't exist in our database");
		// StdOut.println("\nPlease enter copy ID and title e.g : C1 ");
		// id = StdIn.readString();
		// title = "";
		// c = new Copy(id, title);
		//
		// }
		//
		// StdOut.println(FakeDB.getCopyStore().get(c.getCopyID()));
	}



	/** Start Check In Session **/
	public static boolean startCheckIn(String pId1, String cId1) {

		if (Patron.verifyPatron(pId1) && FakeDB.getPatronStore().get(pId1).hasHold() == true) {
			return false;
		} else if ((Copy.verifyCopy(cId1) && Patron.verifyPatron(pId1)) == false) {
			return false;
		}

		else {
			Patron p1 = FakeDB.getPatronStore().get(pId1);

			StdOut.println(FakeDB.getPatronStore().get(pId1).toString());
			// System.out.println(p1.toString());
			Copy c1 = FakeDB.getCopy(cId1);
			StdOut.println("......Checking in " + c1.getTitle() + " to " + p1.getName() + ".");

			if (Copy.checkCopyIn(FakeDB.getCopy(cId1), FakeDB.getPatronStore().get(pId1))) {
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
			} else {
				return false;
			}
		}

	}

	/** Start Check Out Session **/
	public static boolean StartCheckout(String pId, String cId) {
		/*
		 * //verifyPatron(FakeDB.getPatronStore().get(pId)); Patron p1 =
		 * FakeDB.getPatronStore().get(pId);
		 * 
		 * 
		 * 
		 * System.out.println(p1.toString()); String title = ""; Copy c = new Copy(cId,
		 * title); Copy c1 = FakeDB.getCopy(c.getCopyID());
		 * StdOut.println("......Checking out " + c1.getTitle() + " to " + p1.getName()
		 * + ".");
		 */

		if (Patron.verifyPatron(pId) && (FakeDB.getPatronStore().get(pId).hasHold()) == true) {
			return false;
		} else if ((Copy.verifyCopy(cId) && Patron.verifyPatron(pId)) == false) {// if copy or patron is not valid
			return false;
		}

		else {

			StdOut.println("^(((^^^" + cId);

			Patron p1 = FakeDB.getPatronStore().get(pId);

			StdOut.println(FakeDB.getPatronStore().get(pId).toString());
			System.out.println(p1.toString());
			String title = "";
			Copy c = new Copy(cId, title);
			Copy c1 = FakeDB.getCopy(c.getCopyID());
			StdOut.println("......Checking out " + c1.getTitle() + " to " + p1.getName() + ".");

			if (Copy.checkCopyOut(c1, p1)) {
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
			} else {
				return false;
			}

		}

	}

	/** Verify Patron **/
	/*
	 * private static void verifyPatron(Patron p1) { while (Patron.verifyPatron(p1)
	 * == false) {
	 * 
	 * System.out.println("Patron Information:\n\tpatron with id: " +
	 * p1.getPatronID() + " and name: " + p1.getName() +
	 * " doesn't exist in our database");
	 * StdOut.println("\nPlease enter Patron ID and name: e.g 'P1 Eric'"); } }
	 */
	/** Verify Copy **/

	/*
	 * private static boolean verifyCopy(Copy c) { String id; String title; while
	 * (Copy.verifyCopy(c) == false) { System.out.println("Error:\n\tcopy with id: "
	 * + c.getCopyID() + " and title: " + c.getTitle() +
	 * " doesn't exist in our database");
	 * StdOut.println("\nPlease enter copy ID and title e.g : C1 "); id =
	 * StdIn.readString();
	 * StdOut.println("Pleae enter title e.g : 'Fun with Objects' "); title =
	 * StdIn.readLine(); c = new Copy(id, title);
	 * 
	 * } return false; }
	 */
	/** Display Copies **/
	private static void printCopyStore() {
		for (String key2 : FakeDB.getCopyStore().keySet()) {
			StdOut.print("\n" + FakeDB.getCopyStore().get(key2) + "\n");
		}
	}

	/** Display Patrons **/
	private static void printPatronStore() {
		StdOut.print("Datebase information as displayed: \n==========================================");
		for (String key1 : FakeDB.getPatronStore().keySet()) {
			StdOut.print(FakeDB.getPatronStore().get(key1) + "\n");
		}
	}
}
