/**
 * @author Raymond Mbah & Rong Fan
 * 
 * 
 * 
 */

public class Worker
{
	private String name;

	public Worker(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	/** Verify if worker is in the database */
	public static boolean verifyWorker(String name)
	{
		if (FakeDB.getWorkerNames().contains(name) == true)
		{
			return true;
		}
		return false;
	}

}
