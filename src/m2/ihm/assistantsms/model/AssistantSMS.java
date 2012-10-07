package m2.ihm.assistantsms.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AssistantSMS {
	
	static List <SMS> listeSMS = initSMS();
	
	static List <SMS> historique = initHistory();

	private Parametre parametre;
	
	private APropo apropo;
	
	/**
	 * Initialise une liste de SMS
	 * @return une liste de "SMS"
	 */
	public static List<SMS> initSMS() {
		listeSMS = new ArrayList<SMS>();
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
	public static List<SMS> initHistory() {
		historique = new ArrayList<SMS>();
		SMS sms = new SMS();
		sms.setDestinataire("03003030");
		sms.setLocalisation("Toutlouse");
		sms.setSms("coucou sa va bien");
		sms.setDate(new Date()) ;
		historique.add(sms);
		sms = new SMS();
		sms.setDestinataire("077770");
		sms.setLocalisation("sedan");
		sms.setSms("hello a+");
		sms.setDate(new Date()) ;
		historique.add(sms);
		
		
		return historique;
	}
	public APropo getApropo() {
		return apropo;
	}

	public static List<SMS> getListeSMS() {
		return listeSMS;
	}

	public  static List<SMS> getHistorique() {
		return historique;
	}

	public Parametre getParametre() {
		return parametre;
	}
	
	
}
