package m2.ihm.assistantsms.model;

import java.util.ArrayList;
import java.util.List;


public class AssistantSMS {
	
	private static List <SMS> listeSMS;// = initSMS();
	
	private static List <SMS> listeHistorique;// = initHistory();

	//private Parametre parametre;
	
	//private APropo apropo;
	
	/**
	 * Initialise une liste de SMS
	 * @return une liste de "SMS"
	 */
	
	private AssistantSMS(){
		listeSMS = null;
		/*Récupération valeurs enregistrées*/
		listeHistorique = null;
		/*Récupération valeurs enregistrées*/
	}
	
	public static List<SMS> getListeSMS(){
		if(listeSMS == null){
			listeSMS = new ArrayList<SMS>();
		}
		
		return listeSMS;
	}
	
	public static List<SMS> getListeHistorique(){
		if(listeHistorique == null){
			listeHistorique = new ArrayList<SMS>();
		}
		
		return listeHistorique;
	}
}
