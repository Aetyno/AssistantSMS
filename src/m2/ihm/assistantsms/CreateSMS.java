package m2.ihm.assistantsms;

import java.sql.Timestamp;
import java.util.Calendar;

import resources.DatePickerFragment;
import resources.TimePickerFragment;
import m2.ihm.assistantsms.base_de_donnees.MaBaseGestion;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import android.support.v4.app.FragmentActivity;

public class CreateSMS extends FragmentActivity{
	private TimePickerFragment newFragment1;
	private DatePickerFragment newFragment2;

	private Button buttonDate = null;
	private Button buttonTime = null;
	private CheckBox checkboxtime = null;
	private EditText editTextContact = null;
	private EditText editTextLocalisation = null;
	private EditText editTextSMS = null;
	private int hour;
	private int minute;
    private static final int CONTACT_PICKER_RESULT = 1001;  
    
	private static final int TIME_DIALOG_ID = 999;
	private static final int DATE_DIALOG_ID = 998;

	private MaBaseGestion  maBaseGestion= new MaBaseGestion(this);

    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_sms);
        checkboxtime = (CheckBox) findViewById(R.id.checkbox_time);
        checkboxtime.setChecked(true);
        init();
        
        
    }

    private void init(){

        editTextContact = (EditText) findViewById(R.id.editTextContact);
        editTextLocalisation = (EditText) findViewById(R.id.editTextLocalisation);
        editTextSMS = (EditText) findViewById(R.id.editTextSMS);
		buttonDate = (Button)findViewById(R.id.picker_date);
		buttonTime = (Button)findViewById(R.id.picker_time);

    	newFragment1 = new TimePickerFragment(buttonTime);
    	
		newFragment2 = new DatePickerFragment(buttonDate);
    }
	
	
    public void doLaunchContactPicker(View view) {  
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,  
                Contacts.CONTENT_URI);  
        contactPickerIntent.setType(Phone.CONTENT_TYPE);
        startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);  
    }  
    
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CONTACT_PICKER_RESULT) {
            if (resultCode == RESULT_OK) {
                Uri contactUri = data.getData();
                String[] projection = {Phone.NUMBER};
                Cursor cursor = getContentResolver()
                        .query(contactUri, projection, null, null, null);
                cursor.moveToFirst();

                int column = cursor.getColumnIndex(Phone.NUMBER);
                String number = cursor.getString(column);
                editTextContact.setText(number);
            }
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
            	maBaseGestion.insertSMS(
            			editTextContact.getText().toString(), 
            			new Timestamp(2012,9,16,15,57,0,0),
            			editTextLocalisation.getText().toString(), 
            			editTextSMS.getText().toString());
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
    public void showTimePickerDialog(View v) {
    	newFragment1.show(getSupportFragmentManager(), "timePicker");
    }

	public void showDatePickerDialog(View v) {
		newFragment2.show(getSupportFragmentManager(), "datePicker");
	}
	/*@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
			case TIME_DIALOG_ID:
				// set time picker as current time
				return new TimePickerDialog(this, timePickerListener, hour, minute,
						false);

			case DATE_DIALOG_ID:
				// set time picker as current time
				//return new DatePickerDialog(this, datePickerListener, hour, minute,
				//		false);
	
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
*/
	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}
	
}
