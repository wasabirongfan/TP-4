import java.util.Date;

public class Copy_1 {
	private String barCode;
	private Date borrowedDate;
	private Date dueDate;
	private  boolean isOverdue;
	
	
	public Copy_1(String barCode, Date borrowedDate, Date dueDate, boolean isOverdue) {
		super();
		this.barCode = barCode;
		this.borrowedDate = borrowedDate;
		this.dueDate = dueDate;
		this.isOverdue= isOverdue;
	}


	public String getBarCode() {
		return barCode;
	}


	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}


	public Date getBorrowedDate() {
		return borrowedDate;
	}


	public void setBorrowedDate(Date borrowedDate) {
		this.borrowedDate = borrowedDate;
	}


	public Date getDueDate() {
		return dueDate;
	}


	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}


	public boolean isOverdue() {
		return isOverdue;
	}


	public void setOverdue(boolean isOverdue) {
		this.isOverdue = isOverdue;
	}
	
	

}
