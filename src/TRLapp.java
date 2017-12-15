public class TRLapp {

	public static void main(String[] args) {
		String workerName = "";
		while (Controller.initialize(workerName) == false) {
			StdOut.println("Pelase Enter you worker name " + FakeDB.getWorkerNames().toString() + ":");
			workerName = StdIn.readString();

		}

		int choice = 0;
		while (true) {
			Controller.showMenu();
			try {

				String s = StdIn.readString();

				StdOut.println("...choice entered = " + s);
				choice = Integer.parseInt(s);
				switch (choice) {
				case 1:
					StdOut.println("Please enter patron ID: ");
					String pId = StdIn.readString();
					String option = "y";
					while (option.equals("y")) {
						StdOut.println("Please enter copy ID: ");
						String cId = StdIn.readString();
						Controller.StartCheckout(pId, cId);
						StdOut.println("Check out another copy? (y/n):");
						option = StdIn.readString();

					}
					break;
				case 2:
					StdOut.print("Please enter patron ID: ");
					String pId1 = StdIn.readString();
					StdOut.println(FakeDB.getPatronStore().get(pId1));
					String option1 = "y";
					while (option1.equals("y")) {
						StdOut.println("Please enter copy ID: ");
						String cId1 = StdIn.readString();
						Controller.startCheckIn(pId1, cId1);
						StdOut.println("Check in another copy(y/n)");
						option1 = StdIn.readString();
					}

					break;
				case 3:
					StdOut.println("\n...Searching patron...\n");
					StdOut.println("Please enter patron ID: ");
					String pId2 = StdIn.readString();
					if (Controller.searchPatron(pId2)) {
						StdOut.println(FakeDB.getPatronStore().get(pId2));
					} else {
						StdOut.println(
								("Patron Information:\n\tpatron with id: " + pId2 + " doesn't exist in our database"));
						StdOut.println("\nPlease enter Patron ID: ");
					}
					break;
				case 4:
					StdOut.println("\n...Searching copy...\n");
					StdOut.println("\nPlease enter copy ID e.g : C1 ");
					String cId2 = StdIn.readString();
					if (Controller.searchCopy(cId2)) {
						StdOut.println(FakeDB.getCopyStore().get(cId2));
					} else {
						StdOut.println(
								"Copy information: \n\tcopy with id: " + cId2 + " doesn't exist in our database");
						StdOut.println("\nPlease enter Copy ID: ");
					}
					break;
				case 5:
					StdOut.println("Please enter patron ID: ");
					String pId3 = StdIn.readString();
					StdOut.println("Please enter hold value [t/f]: ");

					String value = StdIn.readString();
					
					if( Controller.setHolds(pId3, value) ) {
						StdOut.println("holds updated!!!");
						StdOut.println(FakeDB.getPatronStore().get(pId3));

					}else{
						StdOut.println("Can't update holds");

					};
					break;
				
				case 6:
					System.exit(0);;
				default:
					StdOut.println("Please enter a valid option");
				}
			} catch (NumberFormatException e) {
				StdOut.println("Please enter a valid option");
			}
		}

	}

}
