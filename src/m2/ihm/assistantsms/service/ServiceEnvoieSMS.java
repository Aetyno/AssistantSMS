package m2.ihm.assistantsms.service;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import resources.SMS;
import m2.ihm.assistantsms.adapter.SMSAdapter;
import m2.ihm.assistantsms.base_de_donnees.MaBaseSMSGestion;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.widget.Toast;

public class ServiceEnvoieSMS extends Service {
	
	private List<SMS> listeSMS;
	private MaBaseSMSGestion maBaseGestion;
	@Override
	public void onCreate(){
		//Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
	  //  Toast.makeText(this, "service onStartCommand", Toast.LENGTH_SHORT).show();
	    return super.onStartCommand(intent,flags,startId);
	}
	

	@Override
	 public void onStart(Intent intent, int startId) {
	  // TODO Auto-generated method stub
	  super.onStart(intent, startId);
	  String typeenvoie = "date";
	    Toast.makeText(this, "envoie de sms", Toast.LENGTH_SHORT).show();
	    
	    maBaseGestion = new MaBaseSMSGestion(this);
        maBaseGestion.open();
        if(typeenvoie.equals("date"))
        	envoieSMSDate();
        else if (typeenvoie.equals("localisation"))
        	envoieSMSLocalisation();
        
        maBaseGestion.close();
	    sendSMS("258963", "message");
	    Toast.makeText(this, "sms envoyer", Toast.LENGTH_SHORT).show();
	  
	 }
	
	private void envoieSMSLocalisation() {
		// TODO Auto-generated method stub
		
	}
	private void envoieSMSDate() {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Timestamp time = new Timestamp(cal.getTimeInMillis());
		listeSMS = maBaseGestion.getAllSMS();

		for(SMS sms:listeSMS){
			sendSMS(sms.getDestinataire(), sms.getMessage());
			maBaseGestion.updateSMS(sms.getID(),
									sms.getDestinataire(),
									sms.getDate(),
									sms.getLocalisation(),
									sms.getMessage(),
									1);
	    Toast.makeText(this, "sms envoyer", Toast.LENGTH_SHORT).show();
		}
	}
	public void sendSMS(String phoneNumber, String message)
	{     
	        SmsManager sms = SmsManager.getDefault();
	        sms.sendTextMessage(phoneNumber, null, message, null, null);        
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "onBind", Toast.LENGTH_SHORT).show();
		return null;
	}
	@Override
	 public boolean onUnbind(Intent intent) {
	  // TODO Auto-generated method stub
	  Toast.makeText(this, "MyAlarmService.onUnbind()", Toast.LENGTH_LONG).show();
	  return super.onUnbind(intent);
	 }
	
}
