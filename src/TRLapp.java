public class TRLapp
{

	public static void main(String[] args)
	{
		String workerName = "";
		while (Controller.initialize(workerName) == false)
		{
			StdOut.println("Pelase Enter you worker name " + FakeDB.getWorkerNames().toString() + ":");
			workerName = StdIn.readString();

		}

		int choice = 0;
		while (true)
		{
			Controller.showMenu();
			try
			{
				StdOut.println("\n======================================================================\n");
				StdOut.println("Please enter your choice: ");
				String s = StdIn.readString();

				StdOut.println("\n======================================================================\n");
				StdOut.println("Choice entered = " + s);

				choice = Integer.parseInt(s);
				switch (choice)
				{
				case 1:
					StdOut.println("\n======================================================================\n");
					StdOut.println("Please enter patron ID (e.g. P1): ");

					String pId = StdIn.readString();
					String option = "y";
					while (option.equals("y"))
					{
						StdOut.println("\n======================================================================\n");
						StdOut.println("Please enter copy ID (e.g. C1): ");

						String cId = StdIn.readString();
						Controller.StartCheckout(pId, cId);

						StdOut.println("\n======================================================================\n");
						StdOut.println("Check out another copy? (y/n):");
						option = StdIn.readString();

					}
					break;
				case 2:
					StdOut.println("\n======================================================================\n");
					StdOut.println("Please enter patron ID (e.g. P1): ");

					String pId1 = StdIn.readString();
					StdOut.println(FakeDB.getPatronStore().get(pId1));
					String option1 = "y";
					while (option1.equals("y"))
					{
						StdOut.println("\n======================================================================\n");
						StdOut.println("Please enter copy ID (e.g. C1): ");
						String cId1 = StdIn.readString();
						Controller.startCheckIn(pId1, cId1);

						StdOut.println("\n======================================================================\n");
						StdOut.println("Check in another copy(y/n)");
						option1 = StdIn.readString();
					}

					break;
				case 3:
					StdOut.println("\n======================================================================\n");
					StdOut.println("\n...Searching Patron...\n");
					StdOut.println("Please enter patron ID (e.g. P1): ");
					String pId2 = StdIn.readString();
					Controller.searchPatron(pId2);
					break;
				case 4:
					StdOut.println("\n======================================================================\n");
					StdOut.println("\n...Searching Copy...\n");
					StdOut.println("\nPlease enter copy ID (e.g. C1): ");
					String cId2 = StdIn.readString();
					Controller.searchCopy(cId2);
					break;
				case 5:
					StdOut.println("\n======================================================================\n");
					StdOut.println("Please enter Patron ID (e.g. P1): ");
					String pId3 = StdIn.readString();

					StdOut.println("\n======================================================================\n");
					StdOut.println("Please enter hold value [t/f]: ");

					String value = StdIn.readString();
					Controller.setHolds(pId3, value);
					;
					break;

				case 6:
					StdOut.println("\n======================================================================\n");
					StdOut.println("Please enter Copy ID (e.g. C14): ");

					String cId = StdIn.readString();
					StdOut.println("\n======================================================================\n");
					StdOut.println("Please enter Copy title: e.g java2");
					String title = StdIn.readString();
					Controller.addCopy(cId, title);
					break;

				case 7:
					StdOut.println("\n======================================================================\n");
					StdOut.println("Please enter Copy ID (e.g. C14): ");
					cId = StdIn.readString();
					Controller.removeCopy(cId);
					break;
				case 8:
					StdOut.println("\n======================================================================\n");
					StdOut.println("Please enter Patron ID (e.g. P14): ");
					pId = StdIn.readString();

					StdOut.print("\n======================================================================\n");
					StdOut.println("Please enter Patron name: e.g rong47");
					String name = StdIn.readString();
					Controller.addPatron(pId, name);
					break;

				case 9:
					StdOut.println("\n======================================================================\n");
					StdOut.println("Please enter Patron ID (e.g. P14): ");
					pId = StdIn.readString();
					Controller.removePatron(pId);
					break;

				case 10:
					StdOut.println("\n======================================================================\n");
					StdOut.println("\nExiting app....\nThank you for using TRL. ");
					System.exit(0);
				default:
					StdOut.println("\n======================================================================\n");
					StdOut.println("Please enter a valid option: ");
				}
			}
			catch (NumberFormatException e)
			{
				StdOut.println("\n======================================================================\n");
				StdOut.println("Please enter a valid option");
			}

		}

	}

}
