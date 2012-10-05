package m2.ihm.assistantsms.model;

public class Parametre {
	
	private boolean service = true;
	
	private boolean notification = true;

	public boolean isService() {
		return service;
	}

	public void setService(boolean service) {
		this.service = service;
	}

	public boolean isNotification() {
		return notification;
	}

	public void setNotification(boolean notification) {
		this.notification = notification;
	} 
}
