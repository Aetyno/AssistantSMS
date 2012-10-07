package m2.ihm.assistantsms.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AssistantSMS {
	
	static List <SMS> listeSMS = new ArrayList<SMS>();
	
	List <SMS> historique = new ArrayList<SMS>();

	private Parametre parametre;
	
	private APropo apropo;
	
	/**
	 * Initialise une liste de SMS
	 * @return une liste de "SMS"
	 */
	public static List<SMS> getAListOfSMS() {
		SMS sms = new SMS();
		sms.setDestinataire("03003030");
		sms.setLocalisation("Toutlouse");
		sms.setSms("coucou");
		sms.setDate(new Date()) ;
		listeSMS.add(sms);
		sms = new SMS();
		sms.setDestinataire("077770");
		sms.setLocalisation("sedan");
		sms.setSms("hello");
		sms.setDate(new Date()) ;
		listeSMS.add(sms);
		
		
		return listeSMS;
	}	
	
	public APropo getApropo() {
		return apropo;
	}

	public List<SMS> getListeSMS() {
		return listeSMS;
	}

	public List<SMS> getHistorique() {
		return historique;
	}

	public Parametre getParametre() {
		return parametre;
	}
	
	
}
