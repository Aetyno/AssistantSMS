package m2.ihm.assistantsms.model;

import java.util.ArrayList;
import java.util.List;

public class AssistantSMS {
	
	List <SMS> listeSMS = new ArrayList<SMS>();
	
	List <SMS> historique = new ArrayList<SMS>();

	private Parametre parametre;
	
	private APropo apropo;
	
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
