package m2.ihm.assistantsms.adapter;

import java.util.List;

import m2.ihm.assistantsms.R;

import resources.SMS;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts.People;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ContactAdapter extends BaseAdapter{
	
	private List<String> listContact;
	
	private Context context;
	
	private LayoutInflater inflater;
	
	public ContactAdapter(Context context, List<String> aListP) {
		  this.context = context;
		  this.listContact = aListP;
		  this.inflater = LayoutInflater.from(this.context);
	}
	
	public int getCount() {
		return this.listContact.size();
	}

	public Object getItem(int position) {
		return this.listContact.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		 LinearLayout layoutItem;
		  if (convertView == null) {
		    layoutItem = (LinearLayout) inflater.inflate(R.layout.contact_layout, parent, false);
		  } else {
		  	layoutItem = (LinearLayout) convertView;
		  }
		       
		  TextView contact = (TextView)layoutItem.findViewById(R.id.contact);
		        
		  contact.setText(listContact.get(position));
		  
		  return layoutItem;
	}

}
