package resources;

import java.util.Date;

public class SMS {

	private String destinataire;
	private Date date;	
	private String localisation;	
	private String message;

	private int id;
	
	public SMS(){
		destinataire="";
		date = null;
		localisation ="";
		message = "";
		id=-1;		
	}
	
	public SMS(String _destinataire, Date _date,
			String _localisation, String _message) {
		// TODO Auto-generated constructor stub
		destinataire=_destinataire;
		date=_date;
		localisation=_localisation;
		message=_message;
		id=-1;
	}
	
	/** GETTER **/
	public Date getDate() {
		return date;
	}
	
	public String getDateString(){
		if(date != null){
			return date.toString();	
		}
		else return "";
	}
	
	public String getLocalisation() {
		return localisation;
	}
	
	public int getID(){
		return id;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getDestinataire() {
		return destinataire;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setDateString(String _date){
		date = new Date();
	}
	
	
	/** SETTER **/
	
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}
	
	public void setMessage(String sms) {
		this.message = sms;
	}
		
	public void setDestinataire(String destinataire) {
		this.destinataire = destinataire;
	}
	
	public void setID(int _id){
		id=_id;
	}
	
}
