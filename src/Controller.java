import java.util.ArrayList;

/**
 * @author mbahraymond & Fan Rong
 * Interfaces between the view and the model:
 * 	set up initialization
 * 
 * startup, initialization, and some kind of Presentation Logic for the running application. 
 * It should also provide online instruction and help when you run it, 
 *  giving the Patron and Copy identifiers needed to run the app. 
 *
 */
public class Controller {

	public static void main(String[] args) {

		StdOut.print("Pelase Enter you worker name e.g Fan or Raymond: ");
		ArrayList<Worker> workers = FakeDB.getWorkerStore();
		
		/*
		 * initialization: login
		 */
		//get worker names
		ArrayList<String> workerNames = new ArrayList<>() ;
		for (int i = 0; i < workers.size(); i++) {
			workerNames.add(workers.get(i).getName());
		}
		System.out.println("worker names = " + workerNames.toString());
		StdOut.println("Pelase Enter you worker name: ");
		String name = StdIn.readString();
		while(workerNames.contains(name) == false) {
			StdOut.println("Incorrect worker name.Pelase Enter you worker name: ");
			name = StdIn.readString();
		}
		
		//presentation logic for runnig the application
		String choice = "";
		while (!choice.toLowerCase().equals("o")) {
			StdOut.println("what will you like to do : Enter 'i' for checkin or 'o' for checkout");
			choice = StdIn.readString();
			if (!choice.toLowerCase().equals("o")) {
				StdOut.println(
						"checkout is the only available functionality for now. More functionality will be available later");
			} else {

				StdOut.println("...Starting checkout session....");

				StdOut.println("\nPlease enter Patron ID and name: e.g 'P1 Eric'");
				Patron p1 = new Patron(StdIn.readString(), StdIn.readString());
				// System.out.println(p1.toString());
				
				
				while (Patron.verifyPatron(p1) == false) {
					System.out.println("Patron Information:\n\tpatron with id: " + p1.getPatronID() + " and name: " + p1.getName()
							+ " doesn't exist in our database");
					StdOut.println("\nPlease enter Patron ID and name: e.g 'P1 Eric'");
					p1 = new Patron(StdIn.readString(), StdIn.readString());

				}
					
					System.out.println(p1.toString());
					StdOut.println("\nPlease enter copy ID and title e.g : 'C1 ,Fun with Objects' ");
					Copy c = new Copy(StdIn.readString(), StdIn.readString());
					
					while(Copy.verifyCopy(c) == false) {	
							System.out.println("Patron information:\n\tcopy with id: " + c.getCopyID() + " and title: " + c.getTitle()
							+ " doesn't exist in our database");
							StdOut.println("\nPlease enter copy ID and title e.g : 'C1 ,Fun with Objects' ");
							c = new Copy(StdIn.readString(), StdIn.readString());
								
					}
					Copy c1 = FakeDB.getCopy(c.getCopyID());
					System.out.println(c1.toString());
					StdOut.println("Checking out " + c1.getTitle() + " to " + p1.getName());
					Copy.checkCopyOut(c1, p1);

				}

			}


	}



}
