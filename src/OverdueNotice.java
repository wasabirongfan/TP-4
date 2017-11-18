
public class OverdueNotice {
	
	private String dueBack;
	private String currentFine;
	
	public OverdueNotice(String dueBack, String currentFine) {
		super();
		this.dueBack = dueBack;
		this.currentFine = currentFine;
	}
	public String getDueBack() {
		return dueBack;
	}
	public void setDueBack(String dueBack) {
		this.dueBack = dueBack;
	}
	public String getCurrentFine() {
		return currentFine;
	}
	public void setCurrentFine(String currentFine) {
		this.currentFine = currentFine;
	}
	
	

}
