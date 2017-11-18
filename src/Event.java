
public class Event {
	private String eventId;
	private String description;
	private String date;
	
	public Event(String eventId, String description, String date) {
		super();
		this.eventId = eventId;
		this.description = description;
		this.date = date;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	

}
