
public class TRLApp {

	public static void main(String[] args) {

		StdOut.println("Pelase Enter you worker name: ");
		Worker w = new Worker(StdIn.readLine());
		StdOut.println("Hi " + w.getName());

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

				if (!verifyPatron(p1))
					System.out.println("patron with id: " + p1.getPatronID() + " and name: " + p1.getName()
							+ " doesn't exist in our database");
				else {
					System.out.println(p1.toString());
					StdOut.println("\nPlease enter copy ID and title e.g : 'C1 ,Fun with Objects' ");
					Copy c = new Copy(StdIn.readString(), StdIn.readString());
					Copy c1 = FakeDB.getCopy(c.getCopyID());
					System.out.println(c1.toString());

					StdOut.println("Checking out " + c1.getTitle() + " to " + p1.getName());
					checkCopyOut(c1, p1);

				}

			}

		}

	}

	public static boolean verifyPatron(Patron p) {

		if (FakeDB.getPatronStore().containsKey(p.getPatronID())) {
			return true;
		}
		return false;

	}

	public static boolean checkCopyOut(Copy c, Patron p) {
		System.out.println("...checking out " + c.getTitle() + " to " + p.getName());
		// check if the copy is currently available before checking it out
		if (c instanceof Copy && c.getOutTo().getName() == null) {
			c.setOutTo(p);
			p.getCopiesOut().add(c);
			c.setDeuDAte("Dec 23 2017");
			// System.out.println(c.toString());
			System.out.println(c.toString());

			return true;
		}

		return false;
	}

	public boolean checkCopyIn(Copy c, Patron p) {
		System.out.println("...checking in " + c.getTitle());
		// check if the Patron returning the book actually checked it out
		if (c instanceof Copy && c.getOutTo() == p) {
			c.setOutTo(new Patron(null, null));
			p.getCopiesOut().remove(c);
			System.out.println(this.toString());
			System.out.println(c.toString());

			return true;
		}

		return false;
	}

}
