import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class Controller_Test
{
	@Test
	public void test_initialize() {
		Worker worker = new Worker("Fan");
		assertTrue("", Controller.initialize(worker.getName()));
		assertFalse("", Controller.initialize("Mbah"));
	}

	
	@Test
	public void test_showmenu() {
		assertTrue("", Controller.showMenu());
	}
	
	@Test
	public void test_startcheckout() {
		Patron p1 = new Patron("P1", "Eric");
		Copy c1 = new Copy("C6", "Fun with Objects");
		Copy f2 = new Copy("FF3", "Fun with Objects");
		Patron p2 = new Patron("C1", "Rong");
		
		assertFalse("", Controller.StartCheckout(p2.getPatronID(), c1.getCopyID()));
		assertTrue("startcheckout successful",Controller.StartCheckout(p1.getPatronID(), c1.getCopyID()));
		assertFalse("startcheck out fail", Controller.StartCheckout(p1.getPatronID(), f2.getCopyID()));
		
		p1.setHasHolds(true);
		assertFalse("startcheck out fail", Controller.StartCheckout(p1.getPatronID(), f2.getCopyID()));

		
		
	}

	@Test
	public void test_startcheckin() {
		Patron p1 = new Patron ("P1","Eric");
		Copy c1 = new Copy("C1", "Fun with Objects");
		Copy c2 = new Copy ("C2", "More fun with Objects");
		Patron p2 = new Patron("P1000", "Eric");
		Copy.checkCopyOut(c1, p1);
		
		assertTrue("",Controller.startCheckIn(p1.getPatronID(), c1.getCopyID()));
		assertFalse("", Controller.startCheckIn(p1.getPatronID(), c2.getCopyID()));
		assertFalse("", Controller.startCheckIn(p2.getPatronID(), c1.getCopyID()));
	}
	
	@Test
	public void test_searchpatron() {
		Patron p1 = new Patron ("P1","Eric");
		assertTrue("", Controller.searchPatron(p1.getPatronID()));
		assertFalse("",Controller.searchPatron("12"));
	}
	
	@Test
	public void test_searchcopy() {
		Copy c1 = new Copy("C1", "Fun with Objects");
		assertTrue("", Controller.searchCopy(c1.getCopyID()));
		assertFalse("", Controller.searchCopy("C100"));
	}
	
	@Test
	public void test_exit() {
		assertTrue(true);
	}
}
