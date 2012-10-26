package m2.ihm.assistantsms;

import java.sql.Timestamp;
import java.util.Calendar;

import resources.DatePickerFragment;
import resources.TimePickerFragment;
import m2.ihm.assistantsms.base_de_donnees.MaBaseSMSGestion;
import m2.ihm.assistantsms.service.ServiceEnvoieSMS;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.support.v4.app.FragmentActivity;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


@SuppressLint("NewApi")
public class CreateSMS extends FragmentActivity{
	
	private TimePickerFragment fragmentTime;
	private DatePickerFragment fragmentDate;

	private Button buttonDate = null;
	private Button buttonTime = null;
	
	private ImageButton buttonMap = null;	
	//private ImageButton buttonCalendrier = null;	
	
	private CheckBox checkBoxTime = null;
	private CheckBox checkBoxMap = null;
	
	private EditText editTextContact = null;
	private EditText editTextLocalisation = null;
	private EditText editTextSMS = null;
    private static final int CONTACT_PICKER_RESULT = 1001;  
    final Context context = this;

	private MaBaseSMSGestion  maBaseGestion= new MaBaseSMSGestion(this);
	private AlarmManager alarmManager;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_sms);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        
        
    }

    private void init(){

        editTextContact = (EditText) findViewById(R.id.editTextContact);
        editTextContact.setText("");
        editTextLocalisation = (EditText) findViewById(R.id.editTextLocalisation);
        editTextLocalisation.setText("");
        editTextLocalisation.setEnabled(false);
        editTextSMS = (EditText) findViewById(R.id.editTextSMS);
        editTextSMS.setText("");
		
        buttonDate = (Button)findViewById(R.id.picker_date);
		buttonTime = (Button)findViewById(R.id.picker_time);

		buttonMap = (ImageButton)findViewById(R.id.button_map);
		buttonMap.setEnabled(false);
		
		
        checkBoxTime = (CheckBox) findViewById(R.id.checkbox_time);
        checkBoxTime.setChecked(true);
        checkBoxMap= (CheckBox) findViewById(R.id.checkbox_map);
        
        
    	fragmentTime = new TimePickerFragment(buttonTime);
		fragmentDate = new DatePickerFragment(buttonDate);
    	fragmentTime.ModifierButton();
		fragmentDate.ModifierButton();
		
		
		
		
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
                if(editTextContact.getText().equals(("")))
                	editTextContact.setText(number + ", ");
                else
                	editTextContact.setText(editTextContact.getText() + number + ", ");
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
            	Intent parentActivityIntent = new Intent(this, Main.class);
                parentActivityIntent.addFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(parentActivityIntent);
                //finish();
                return true;
                
            case R.id.menu_accept:
            	if(repectCondition()){
	            	maBaseGestion.open();
	            	Timestamp timestamp = null;
	            	if(checkBoxTime.isChecked()){
	            		timestamp = new Timestamp(fragmentDate.getYear()-1900,
							            				fragmentDate.getMonth(),
							            				fragmentDate.getDay(),
							            				fragmentTime.getHour(),
							            				fragmentTime.getMinute(),0,0);
	            	}
	            	if(checkBoxTime.isChecked() && checkBoxMap.isChecked()){
	            		maBaseGestion.insertSMS(
		            			editTextContact.getText().toString(), 
		            			timestamp,
		            			editTextLocalisation.getText().toString(), 
		            			editTextSMS.getText().toString());
	            				addAlarmDate(timestamp);
	            				addAlarmLocalisation(editTextLocalisation.getText().toString());
            	
	            	}
	            	else{ 
	            		if(checkBoxTime.isChecked()){
		            		maBaseGestion.insertSMS(
			            			editTextContact.getText().toString(), 
			            			timestamp,
			            			"null", 
			            			editTextSMS.getText().toString());
		            				addAlarmDate(timestamp);
		            	}
		            	else{
		            		maBaseGestion.insertSMS(
		            			editTextContact.getText().toString(), 
		            			null,
		            			editTextLocalisation.getText().toString(), 
		            			editTextSMS.getText().toString());
	        					addAlarmLocalisation(editTextLocalisation.getText().toString()); 
		            	}
	            	}
	            	
	            	maBaseGestion.close();
	            	
	            	//((Model) Singleton.getModel()).addSMSListeSMS("Destinaire", new Date(), "localisation", "sms");
	      
	            	
	            	toast = Toast.makeText(getApplicationContext(), "Message enregistré", Toast.LENGTH_SHORT);
	            	toast.show();
	            	
	            	intent = new Intent(this, Main.class);
	                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                startActivity(intent);
	                
	                //finish();
            	}
            	
            	return true;
            	
            case R.id.menu_cancel:

            	toast = Toast.makeText(getApplicationContext(), "Annulation", Toast.LENGTH_SHORT);
            	toast.show();
            	
            	intent = new Intent(this, Main.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                //finish();
            	return true;
            	
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    private void addAlarmLocalisation(String localisation) {
		// TODO Auto-generated method stub
		
	}

	private void addAlarmDate(Timestamp timestamp){
    	alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    	 Intent intent = new Intent(this, ServiceEnvoieSMS.class);
    	  Bundle n = new Bundle();
    	  n.putString("Key", "date");
    	  intent.putExtras(n);

    	  PendingIntent pendingIntent = PendingIntent.getService(this, 0,
    	    intent, PendingIntent.FLAG_ONE_SHOT);
    	  
    	  Calendar cal = Calendar.getInstance();
  		  cal.set(Calendar.SECOND, 0);
  		  cal.set(Calendar.MILLISECOND, 0);
    	  alarmManager.set(AlarmManager.RTC_WAKEUP,
    	    timestamp.getTime(), pendingIntent);
    }
    
    private boolean repectCondition() {
    	String error = null;
    	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
    	
    	boolean test = true;
    	
    	
    	error="Pour enregistrer le message vous devez : \n";
    	
    	if(editTextContact.getText().toString().equals("")){
    		error+="Mettre au moins un contact\n";
    		test=false;
    	}
    	
    	if (!checkBoxMap.isChecked() && !checkBoxTime.isChecked()){
    		error+="Donner une date et/ou un lieu d'envoie\n";
    		test=false;
    	}
    	{
	    	if(checkBoxMap.isChecked() && editTextLocalisation.getText().toString().equals("")){
	    		error+="Remplir le lieu d'envoie\n";
	    		test = false;
	    	}
	    	
	    	if(checkBoxTime.isChecked()){
				Timestamp timestamp = new Timestamp(fragmentDate.getYear()-1900,
	    											fragmentDate.getMonth(),
	    											fragmentDate.getDay(),
	    											fragmentTime.getHour(),
	    											fragmentTime.getMinute(),0,0);
				final Calendar c = Calendar.getInstance();
				if(c.getTimeInMillis() > timestamp.getTime()){
					test=false;
					error+="Donner une date future\n";
				}	
	    	}
    	}
    		
    	if(editTextSMS.getText().toString().equals("")){
    		error+="Ecrire un message\n";
    		test=false;
    	}
    	
    	
		
    	if(!test){
    		// set title
    		alertDialogBuilder.setTitle("Informations incomplètes");

    		// set dialog message
    		alertDialogBuilder
    			.setMessage(error)
    			.setCancelable(false)
    			.setPositiveButton("Ok", null);
    			             
    		// create alert dialog
    		AlertDialog alertDialog = alertDialogBuilder.create();

    		// show it
    		alertDialog.show();
    	}
		
    	
		return test;
	}

	public void test(View view){
    	Intent intent = new Intent(this, Localisation.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    
    public void onCheckboxClicked(View view){
    	boolean checked = ((CheckBox) view).isChecked();
    	switch(view.getId()){
    		case R.id.checkbox_map:
    			if(checked){
    				buttonMap.setEnabled(true);
    				editTextLocalisation.setEnabled(true);
    			}
    			else{
    				buttonMap.setEnabled(false);
    				editTextLocalisation.setEnabled(false);    				
    			}
    			break;
    		case R.id.checkbox_time:
    			if(checked){
    				buttonDate.setEnabled(true);
    				buttonTime.setEnabled(true);
    				fragmentDate.ModifierButton();
    				fragmentTime.ModifierButton();
    			}
    			else{
    				buttonDate.setEnabled(false);
    				buttonTime.setEnabled(false);
    			}
    	}
    }
    public void showTimePickerDialog(View v) {
    	fragmentTime.show(getSupportFragmentManager(), "timePicker");
    	showDialog(999);
    }

	public void showDatePickerDialog(View v) {
		fragmentDate.show(getSupportFragmentManager(), "datePicker");
    	showDialog(998);
	}
	
	
}
