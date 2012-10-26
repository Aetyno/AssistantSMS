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
		
		TextView destinataire = null;
		TextView date = null;
		TextView localisation = null;
		TextView sms = null;
		  
		if( !listSMS.get(position).getLocalisation().equals("null") && listSMS.get(position).getDate()!= null){
			layoutItem = (LinearLayout) inflater.inflate(R.layout.sms_layout_1, parent, false);
			
			destinataire = (TextView)layoutItem.findViewById(R.id.destinataire);
			date = (TextView)layoutItem.findViewById(R.id.date);
			localisation = (TextView)layoutItem.findViewById(R.id.lieu);
			sms = (TextView)layoutItem.findViewById(R.id.sms);
			
			
			destinataire.setText(listSMS.get(position).getDestinataire());
			date.setText(listSMS.get(position).getDate().toString());
			localisation.setText(listSMS.get(position).getLocalisation());
			sms.setText(listSMS.get(position).getMessage());
			  
		}
		else if (listSMS.get(position).getDate() != null){
			layoutItem = (LinearLayout) inflater.inflate(R.layout.sms_layout_2, parent, false);
			
			destinataire = (TextView)layoutItem.findViewById(R.id.destinataire);
			date = (TextView)layoutItem.findViewById(R.id.date);
			sms = (TextView)layoutItem.findViewById(R.id.sms);
			
			destinataire.setText(listSMS.get(position).getDestinataire());
			date.setText(listSMS.get(position).getDate().toString());
			sms.setText(listSMS.get(position).getMessage());
		}
		else{
			layoutItem = (LinearLayout) inflater.inflate(R.layout.sms_layout_3, parent, false);
			
			destinataire = (TextView)layoutItem.findViewById(R.id.destinataire);
			localisation = (TextView)layoutItem.findViewById(R.id.lieu);
			sms = (TextView)layoutItem.findViewById(R.id.sms);
			
			destinataire.setText(listSMS.get(position).getDestinataire());
			localisation.setText(listSMS.get(position).getLocalisation());
			sms.setText(listSMS.get(position).getMessage());
		}
		 
		/*LinearLayout layoutItem;
		   if (convertView == null) {
		        layoutItem = (LinearLayout) inflater.inflate(R.layout.sms_layout, parent, false);
		  } else {
		  	layoutItem = (LinearLayout) convertView;
		  }
		       
		  TextView destinataire = (TextView)layoutItem.findViewById(R.id.destinataire);
		  TextView date = (TextView)layoutItem.findViewById(R.id.date);
		  TextView localisation = (TextView)layoutItem.findViewById(R.id.lieu);
		  TextView sms = (TextView)layoutItem.findViewById(R.id.sms);
		        
		  destinataire.setText(listSMS.get(position).getDestinataire());
		  if(listSMS.get(position).getDate() == null){
			  date.setText("null");
		  }
		  else{
			  date.setText(listSMS.get(position).getDate().toString());
		  }
		  localisation.setText(listSMS.get(position).getLocalisation());
		  sms.setText(listSMS.get(position).getMessage());
		  */
		  return layoutItem;
	}

}
