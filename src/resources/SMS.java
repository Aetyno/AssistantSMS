package resources;

import java.util.Date;

public class SMS {


	private String destinataire;
	
	private Date date;
	
	private String localisation;
	
	private String sms;

	
	public SMS(){
		
	}
	
	public SMS(String _destinataire, Date _date,
			String _localisation, String _message) {
		// TODO Auto-generated constructor stub
		destinataire=_destinataire;
		date=_date;
		localisation=_localisation;
		sms=_message;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getLocalisation() {
		return localisation;
	}
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}
	public String getSms() {
		return sms;
	}
	public void setSms(String sms) {
		this.sms = sms;
	}
	public String getDestinataire() {
		return destinataire;
	}
	public void setDestinataire(String destinataire) {
		this.destinataire = destinataire;
	}
}
