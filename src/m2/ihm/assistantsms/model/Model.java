package m2.ihm.assistantsms.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import resources.SMS;

public class Model{
	private static List<SMS> listeSMS;
	private static List<SMS> listeHistorique;
	
	public Model(){
		listeSMS = new ArrayList<SMS>();
		listeHistorique = new ArrayList<SMS>();
	}
	
	public List<SMS> getListeSMS(){
		return listeSMS;
	}
	
	public List<SMS> getListeHistorique(){
		return listeHistorique;
	}
	
	/*
	public void addSMSListeSMS(String _destinataire, Date _date, String _localisation, String _message){
		SMS sms = new SMS(_destinataire,_date,_localisation,_message);
		listeSMS.add(sms);
	}*/
}
