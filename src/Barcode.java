
public class Barcode {
	
	private String encoding;
	private String checkDigit;
	
	public Barcode(String encoding, String checkDigit) {
		super();
		this.encoding = encoding;
		this.checkDigit = checkDigit;
	}
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public String getCheckDigit() {
		return checkDigit;
	}
	public void setCheckDigit(String checkDigit) {
		this.checkDigit = checkDigit;
	}
	
	

}
