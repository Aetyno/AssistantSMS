package m2.ihm.assistantsms;

import java.util.Date;

import m2.ihm.assistantsms.model.Model;
import m2.ihm.assistantsms.model.Singleton;

import resources.DatePickerFragment;
import resources.TimePickerFragment;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;

public class CreateSMS extends FragmentActivity implements OnClickListener{
	private DialogFragment newFragment1;
	private DialogFragment newFragment2;
	private ImageButton buttonContact = null;

    @SuppressWarnings("unused")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_sms);
        buttonContact = (ImageButton)findViewById(R.id.button_contact);
        // On spécifie que le le listener est notre classe
        buttonContact.setOnClickListener(this);

		
    }
    public void onClick(View v) {
		// TODO Auto-generated method stub
    	if(v == buttonContact) {
    		Intent monIntent = new Intent(this,Contact.class);
    		startActivity(monIntent);
    		}

	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_create_sms, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
    	switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                //intent = new Intent(this, Main.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //startActivity(intent);
                return true;
            case R.id.menu_accept:
            	((Model) Singleton.getModel()).addSMSListeSMS("Destinaire", new Date(), "localisation", "sms");
      
            	intent = new Intent(this, Main.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            	return true;
            case R.id.menu_cancel:
            	intent = new Intent(this, Main.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            	return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    public void test(View view){
    	Intent intent = new Intent(this, Main.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    
    public void onCheckboxClicked(View view){
    	boolean checked = ((CheckBox) view).isChecked();
    	switch(view.getId()){
    		case R.id.checkbox_map:
    			if(checked){
    			}
    			else{
    				
    			}
    			break;
    		case R.id.checkbox_time:
    			break;
    	}
    }
    
    public void showTimePickerDialog(View v) {
        newFragment1 = new TimePickerFragment();
        newFragment1.show(getSupportFragmentManager(), "timePicker");
    }
    
    public void showDatePickerDialog(View v) {
        newFragment2 = new DatePickerFragment();
        newFragment2.show(getSupportFragmentManager(), "datePicker");
    }

	
}
