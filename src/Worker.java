/**
 * @author Raymond Mbah & Rong Fan
 *
 */

public class Worker
{
	private String name;

	public Worker(String workerName)
	{
		this.name = workerName;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String workerName)
	{
		this.name = workerName;
	}
	
	public static boolean validateWorker(String workerName) {
		if (FakeDB.getWorkerNames().contains(workerName) == true) {
			return true;
		}
		return false;
	}

}
