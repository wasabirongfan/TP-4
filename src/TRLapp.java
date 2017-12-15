public class TRLapp
{

	public static void main(String[] args)
	{
		Controller.initialize();
		int choice = 0;
		while (true)
		{
			Controller.showMenu();
			try
			{

				String s = StdIn.readString();

				StdOut.println("...choice entered = " + s);
				choice = Integer.parseInt(s);
				switch (choice)
				{
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
						Controller.startCheckIn(pId1,cId1);
						StdOut.println("Check in another copy(y/n)");
						option1 = StdIn.readString();
					}
					
					break;
				case 3:
					Controller.searchPatron();
					break;
				case 4:
					Controller.searchCopy();
					break;
				case 5:
					Controller.exit();
				default:
					StdOut.println("Please enter a valid option");
				}
			}
			catch (NumberFormatException e)
			{
				StdOut.println("Please enter a valid option");
			}
		}

	}

}
