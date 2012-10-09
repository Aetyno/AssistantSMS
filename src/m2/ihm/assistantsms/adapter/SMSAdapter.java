package m2.ihm.assistantsms.adapter;

import java.util.List;

import resources.SMS;

import m2.ihm.assistantsms.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SMSAdapter extends BaseAdapter  {

	private List<SMS> listSMS;
	
	private Context context;
	
	private LayoutInflater inflater;
	
	public SMSAdapter(Context context, List<SMS> aListP) {
		  this.context = context;
		  this.listSMS = aListP;
		  this.inflater = LayoutInflater.from(this.context);
	}
	
	public int getCount() {
		return this.listSMS.size();
	}

	public Object getItem(int position) {
		return this.listSMS.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		 LinearLayout layoutItem;
		  //(1) : Réutilisation des layouts
		  if (convertView == null) {
		  	//Initialisation de notre item à partir du  layout XML "personne_layout.xml"
		    layoutItem = (LinearLayout) inflater.inflate(R.layout.sms_layout, parent, false);
		  } else {
		  	layoutItem = (LinearLayout) convertView;
		  }
		  
		  //(2) : Récupération des TextView de notre layout      
		  TextView destinataire = (TextView)layoutItem.findViewById(R.id.destinataire);
		  TextView date = (TextView)layoutItem.findViewById(R.id.date);
		  TextView localisation = (TextView)layoutItem.findViewById(R.id.lieu);
		  TextView sms = (TextView)layoutItem.findViewById(R.id.sms);
		        
		  //(3) : Renseignement des valeurs       
		  destinataire.setText(listSMS.get(position).getDestinataire());
		  date.setText(listSMS.get(position).getDate().toString());
		  localisation.setText(listSMS.get(position).getLocalisation());
		  sms.setText(listSMS.get(position).getSms());
		  
		 

		  //On retourne l'item créé.
		  return layoutItem;
	}

}
