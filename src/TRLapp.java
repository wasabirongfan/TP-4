import java.util.InputMismatchException;

public class TRLapp {

	public static void main(String[] args) {
		// Controller.initialize();
		int choice = 0;
		while (true) {
			Controller.showMenu();
			try {
				
				String s = StdIn.readString();  //readString();
				StdOut.println("...choice entered = " + s);
				choice = Integer.parseInt(s);
				switch (choice) {
				case 1:
					Controller.StartCheckout();
					break;
				case 2:
					Controller.startCheckIn();
					break;
				case 3:
					Controller.exit();
					break;
				default:
					StdOut.println("Please enter a valid option");
				}
			}catch(NumberFormatException e) {
				StdOut.println("Please enter a valid option");
			}
		}
	}

}
