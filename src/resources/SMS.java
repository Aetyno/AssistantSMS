package resources;

import java.util.Date;
import java.sql.Timestamp;

public class SMS {

	private String destinataire;
	private Timestamp date;	
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
	
	/** GETTER **/	
	public Timestamp getDate(){
		return date;
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
	
	public void setDate(Timestamp date) {
		this.date = date;
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
