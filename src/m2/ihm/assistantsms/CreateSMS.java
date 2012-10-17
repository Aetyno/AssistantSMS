package m2.ihm.assistantsms;

import java.sql.Timestamp;

import resources.DatePickerFragment;
import resources.TimePickerFragment;
import m2.ihm.assistantsms.base_de_donnees.MaBaseGestion;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.support.v4.app.FragmentActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


@SuppressLint("NewApi")
public class CreateSMS extends FragmentActivity{
	private TimePickerFragment fragmentTime;
	private DatePickerFragment fragmentDate;

	private Button buttonDate = null;
	private Button buttonTime = null;
	private CheckBox checkboxtime = null;
	private EditText editTextContact = null;
	private EditText editTextLocalisation = null;
	private EditText editTextSMS = null;
    private static final int CONTACT_PICKER_RESULT = 1001;  

	private MaBaseGestion  maBaseGestion= new MaBaseGestion(this);

    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_sms);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        
        
    }

    private void init(){

        editTextContact = (EditText) findViewById(R.id.editTextContact);
        editTextLocalisation = (EditText) findViewById(R.id.editTextLocalisation);
        editTextSMS = (EditText) findViewById(R.id.editTextSMS);
		buttonDate = (Button)findViewById(R.id.picker_date);
		buttonTime = (Button)findViewById(R.id.picker_time);

        checkboxtime = (CheckBox) findViewById(R.id.checkbox_time);
        checkboxtime.setChecked(true);
        
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
            	Intent parentActivityIntent = new Intent(this, Main.class);
                parentActivityIntent.addFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(parentActivityIntent);
                finish();
                return true;
                
            case R.id.menu_accept:
            	maBaseGestion.open();
            	Timestamp timestamp = null;
            	if(checkboxtime.isChecked())
            		timestamp = new Timestamp(fragmentDate.getYear(),
						            				fragmentDate.getMonth(),
						            				fragmentDate.getDay(),
						            				fragmentTime.getHour(),
						            				fragmentTime.getMinute(),0,0);
            		
            	maBaseGestion.insertSMS(
		            			editTextContact.getText().toString(), 
		            			timestamp,
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
    	fragmentTime.show(getSupportFragmentManager(), "timePicker");
    	showDialog(999);
    }

	public void showDatePickerDialog(View v) {
		fragmentDate.show(getSupportFragmentManager(), "datePicker");
    	showDialog(998);
	}
	
	
}
