package resources;

import java.util.Date;

public class SMS {

	private String destinataire;
	private Date date;	
	private String localisation;	
	private String message;

	private int id;
	
	private int isSent;
	
	public SMS(){
		destinataire="";
		date = null;
		localisation ="";
		message = "";
		id=-1;
		isSent=0;
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
	
	public int getIsSent(){
		return isSent;
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
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setDateString(String _date){
		date = new Date();
	}
	
	public void setID(int _id){
		id=_id;
	}
	
	public void setIsSent(int _isSent){
		isSent = _isSent;
	}
	
	
	/** OTHER **/
	public boolean isSent(){
		return isSent != 0;
	}
	
}
