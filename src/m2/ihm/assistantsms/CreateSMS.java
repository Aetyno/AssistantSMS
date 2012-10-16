package m2.ihm.assistantsms;

import java.util.Calendar;
import java.util.Date;

import m2.ihm.assistantsms.model.Model;
import m2.ihm.assistantsms.model.Singleton;
import m2.ihm.assistantsms.base_de_donnees.MaBaseGestion;

import resources.DatePickerFragment;
import resources.SMS;
import resources.TimePickerFragment;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;

public class CreateSMS extends FragmentActivity implements OnClickListener{
	private DialogFragment newFragment1;
	private DialogFragment newFragment2;
	private Button buttonDate = null;
	private Button buttonTime = null;
	private CheckBox checkboxtime = null;
	private int hour;
	private int minute;
    private static final int CONTACT_PICKER_RESULT = 1001;  


	private MaBaseGestion  maBaseGestion= new MaBaseGestion(this);
	
    @SuppressWarnings("unused")

	static final int TIME_DIALOG_ID = 999;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_sms);
        checkboxtime = (CheckBox) findViewById(R.id.checkbox_time);
        checkboxtime.setChecked(true);
        setCurrentTimeOnView();
       
    }


	private void setCurrentTimeOnView() {
		buttonDate = (Button)findViewById(R.id.picker_date);
		buttonTime = (Button)findViewById(R.id.picker_time);
		buttonTime.setOnClickListener(this);
		final Calendar c = Calendar.getInstance();
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);
		buttonTime.setText(new StringBuilder().append(pad(hour)).append(":")
				.append(pad(minute)));
	}

	@SuppressWarnings("deprecation")
	public void onClick(View v) {
		// TODO Auto-generated method stub
		showDialog(TIME_DIALOG_ID);
	}
    public void doLaunchContactPicker(View view) {  
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,  
                Contacts.CONTENT_URI);  
        startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);  
    }  
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_create_sms, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        Toast toast;
    	switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                //intent = new Intent(this, Main.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //startActivity(intent);
                return true;
                
            case R.id.menu_accept:
            	maBaseGestion.open();
            	maBaseGestion.insertSMS(new SMS("Destinaire", new Date(), "localisation", "sms"));
            	maBaseGestion.close();
            	
            	//((Model) Singleton.getModel()).addSMSListeSMS("Destinaire", new Date(), "localisation", "sms");
      
            	toast = Toast.makeText(getApplicationContext(), "Message enregistré", Toast.LENGTH_SHORT);
            	toast.show();
            	
            	intent = new Intent(this, Main.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            	return true;
            	
            case R.id.menu_cancel:

            	toast = Toast.makeText(getApplicationContext(), "Annulation", Toast.LENGTH_SHORT);
            	toast.show();
            	
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
    			if(checked){
    				
    			}
    			else{

    				buttonDate.setText("JJ-MM-AAAA");
    				buttonTime.setText("HH:MM");
    			}
    	}
    }
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
			case TIME_DIALOG_ID:
				// set time picker as current time
				return new TimePickerDialog(this, timePickerListener, hour, minute,
						false);
	
			}
		return null;
	}

	private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int selectedHour,
				int selectedMinute) {
			hour = selectedHour;
			minute = selectedMinute;

			// set current time into textview
			buttonTime.setText(new StringBuilder().append(pad(hour))
					.append(":").append(pad(minute)));


		}
	};

	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}
	
}
