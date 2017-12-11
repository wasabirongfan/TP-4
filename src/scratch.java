import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class scratch {
	
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
		//Calendar calendar = new GregorianCalendar(2013,3,1);
		Calendar calendar = new GregorianCalendar();//default is current date and time
		Calendar c1 = calendar;
		c1.add(Calendar.YEAR, -30); //set due date to 3 months from now
		
		System.out.println(sdf.format(calendar.getTime()));
		System.out.println("c1; " + sdf.format(calendar.getTime()));
		System.out.println("c1 > calendar " + calendar.getTime().after(c1.getTime()) );

		System.out.println("c1 > calendar " + sdf.format(calendar.getTime().after(c1.getTime())));


	}

}
