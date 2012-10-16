package m2.ihm.assistantsms;

import java.util.ArrayList;
import java.util.List;

import m2.ihm.assistantsms.adapter.ContactAdapter;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.view.Menu;

public class Contact extends ListActivity {

private List<String> listeContact;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        ListContact();
        ContactAdapter adapter = new ContactAdapter(this, listeContact);
        setListAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    private void ListContact() {
    	// notre tableau de contact
    	listeContact = new ArrayList<String>();
        // instance qui permet d'acceder au contenu d'autre application
    	ContentResolver ConnectApp = this.getContentResolver();
    	Uri uri =  ContactsContract.Data.CONTENT_URI;
        // String[] projection = new String[] {ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.NUMBER, ContactsContract.Contacts._ID };
        // on récupere les contacts dans un curseur
         Cursor cur = ConnectApp.query(uri, null, null, null, null);
        // this.startManagingCursor(cur);
 
         if (cur.moveToFirst()) {
             do {
                 String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                 String a1num = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                 String num = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                 String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                 if (a1num.compareTo("1") == 1)
                	 listeContact.add(name+"=>"+num);
                 else
                	 listeContact.add("coucou");
 
             } while (cur.moveToNext());
         }
     }
}
