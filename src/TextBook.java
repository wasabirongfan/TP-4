
public class TextBook {
	private String bookTitle;
	private String courseTitle;
	private String sectionNumber;
	
	
	public TextBook(String bookTitle, String courseTitle, String sectionNumber) {
		super();
		this.bookTitle = bookTitle;
		this.courseTitle = courseTitle;
		this.sectionNumber = sectionNumber;
	}


	public String getBookTitle() {
		return bookTitle;
	}


	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}


	public String getCourseTitle() {
		return courseTitle;
	}


	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}


	public String getSectionNumber() {
		return sectionNumber;
	}


	public void setSectionNumber(String sectionNumber) {
		this.sectionNumber = sectionNumber;
	}
	
	
	
	

}
