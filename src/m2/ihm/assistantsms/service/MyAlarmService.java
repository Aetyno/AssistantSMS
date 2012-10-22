package m2.ihm.assistantsms.service;

import java.util.List;

import m2.ihm.assistantsms.base_de_donnees.MaBaseSMS;
import m2.ihm.assistantsms.base_de_donnees.MaBaseSMSGestion;

import resources.SMS;
import android.app.Activity;
import android.app.IntentService;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.widget.Toast;

public class MyAlarmService extends IntentService {

	public MyAlarmService(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	String smsNumberToSend, smsTextToSend;

	 @Override
	 public void onCreate() {
	  
	  Toast.makeText(this, "MyAlarmService.onCreate()", Toast.LENGTH_LONG).show();
	 }

	 @Override
	 public IBinder onBind(Intent arg0) {
	  Toast.makeText(this, "MyAlarmService.onBind()", Toast.LENGTH_LONG).show();
	  return null;
	 }
	 
	 @Override
	 public void onDestroy() {
	   super.onDestroy();
	   Toast.makeText(this, "MyAlarmService.onDestroy()", Toast.LENGTH_LONG).show();
	 }

	 @Override
	 public void onStart(Intent intent, int startId) {
	  super.onStart(intent, startId);
	  	  
	
	  
	  MaBaseSMSGestion maBaseGestion = new MaBaseSMSGestion(this);
	  List<SMS> listeSMS;
	  maBaseGestion.open();
      listeSMS = maBaseGestion.getAllSMS();
      maBaseGestion.close();
      
      for(SMS sms:listeSMS)
    	  conditionDEnvoie(sms);
	 }

	 @Override
	 public boolean onUnbind(Intent intent) {
	  Toast.makeText(this, "MyAlarmService.onUnbind()", Toast.LENGTH_LONG).show();
	  return super.onUnbind(intent);
	 }
	 private void conditionDEnvoie(SMS sms){
		 if(sms.getDate() == null)
			 envoiesmslocalisation(sms);
		 else if(sms.getLocalisation().equals(""))
			 envoiesmsTime(sms);
		 else
			 envoieSMSTime_Localisation(sms);
	 }
	 private void envoieSMSTime_Localisation(SMS sms) {
		// TODO Auto-generated method stub
		
	}

	private void envoiesmsTime(SMS sms) {
		// TODO Auto-generated method stub
		
	}

	private void envoiesmslocalisation(SMS sms) {
		// TODO Auto-generated method stub
		
	}

	private void sendSMS(String phoneNumber, String message)
	    {        
		//information sur l'état du sms
	       /* String SENT = "SMS_SENT";
	        String DELIVERED = "SMS_DELIVERED";
	 
	        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
	            new Intent(SENT), 0);
	 
	        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
	            new Intent(DELIVERED), 0);
	 
	        //---when the SMS has been sent---
	        registerReceiver(new BroadcastReceiver(){
	            @Override
	            public void onReceive(Context arg0, Intent arg1) {
	                switch (getResultCode())
	                {
	                    case Activity.RESULT_OK:
	                        Toast.makeText(getBaseContext(), "SMS sent", 
	                                Toast.LENGTH_SHORT).show();
	                        break;
	                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
	                        Toast.makeText(getBaseContext(), "Generic failure", 
	                                Toast.LENGTH_SHORT).show();
	                        break;
	                    case SmsManager.RESULT_ERROR_NO_SERVICE:
	                        Toast.makeText(getBaseContext(), "No service", 
	                                Toast.LENGTH_SHORT).show();
	                        break;
	                    case SmsManager.RESULT_ERROR_NULL_PDU:
	                        Toast.makeText(getBaseContext(), "Null PDU", 
	                                Toast.LENGTH_SHORT).show();
	                        break;
	                    case SmsManager.RESULT_ERROR_RADIO_OFF:
	                        Toast.makeText(getBaseContext(), "Radio off", 
	                                Toast.LENGTH_SHORT).show();
	                        break;
	                }
	            }
	        }, new IntentFilter(SENT));
	 
	        //---when the SMS has been delivered---
	        registerReceiver(new BroadcastReceiver(){
	            @Override
	            public void onReceive(Context arg0, Intent arg1) {
	                switch (getResultCode())
	                {
	                    case Activity.RESULT_OK:
	                        Toast.makeText(getBaseContext(), "SMS delivered", 
	                                Toast.LENGTH_SHORT).show();
	                        break;
	                    case Activity.RESULT_CANCELED:
	                        Toast.makeText(getBaseContext(), "SMS not delivered", 
	                                Toast.LENGTH_SHORT).show();
	                        break;                        
	                }
	            }
	        }, new IntentFilter(DELIVERED));   */     
	 
	        SmsManager sms = SmsManager.getDefault();
	        sms.sendTextMessage(phoneNumber, null, message, null, null);  
	       // sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);        
	    }

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		
	}

	
}
