package m2.ihm.assistantsms.service;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import resources.SMS;
import m2.ihm.assistantsms.CreateSMS;
import m2.ihm.assistantsms.History;
import m2.ihm.assistantsms.R;
import m2.ihm.assistantsms.adapter.SMSAdapter;
import m2.ihm.assistantsms.base_de_donnees.MaBaseSMSGestion;
import m2.ihm.assistantsms.base_de_donnees.MaBaseSettingsGestion;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.telephony.SmsManager;
import android.widget.Toast;

public class ServiceEnvoieSMS extends Service {
	
	private List<SMS> listeSMS;
	private MaBaseSMSGestion maBaseGestion;
	public static int ID_NOTIFICATION = 1988;
	private static int numMessages;
	
	@Override
	public void onCreate(){
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
	    return super.onStartCommand(intent,flags,startId);
	}
	

	@Override
	 public void onStart(Intent intent, int startId) {
	    // TODO Auto-generated method stub
	    super.onStart(intent, startId);
	    Bundle bundle = intent.getExtras();
	    intent.setClass( this , CreateSMS.class );
	    String typeenvoie = "";
	    if(bundle != null)
	    	typeenvoie = (String) bundle.getString("Key");
	    
	    maBaseGestion = new MaBaseSMSGestion(this);
        maBaseGestion.open();
        if(typeenvoie.equals("date"))
        	envoieSMSDate();
        else if (typeenvoie.equals("localisation"))
        	envoieSMSLocalisation();
        
        maBaseGestion.close();
	  
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
		listeSMS = maBaseGestion.getAllSMSPrepared();

		for(SMS sms:listeSMS){
			if(sms.getDate().equals(time)){
				sendSMS(sms.getDestinataire(), sms.getMessage());
				maBaseGestion.updateSMS(sms.getID(),
										sms.getDestinataire(),
										sms.getDate(),
										sms.getLocalisation(),
										sms.getMessage(),
										1);
				
		        MaBaseSettingsGestion maBaseSettingsGestion= new MaBaseSettingsGestion(this);
		        maBaseSettingsGestion.open();
		    	if(maBaseSettingsGestion.isNotificationOn()){
			        createNotify(sms);
		        }
		    	maBaseSettingsGestion.close();
			}
		}
	}
	public void sendSMS(String phoneNumber, String message)
	{     
	        SmsManager sms = SmsManager.getDefault();
	        sms.sendTextMessage(phoneNumber, null, message, null, null);        
	}
	 //Méthode qui créer la notification
    private void createNotify(SMS sms){
    	
    	NotificationManager mNotificationManager =
    	        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    	
    	++numMessages;
    	NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(this)
    	    .setContentTitle(numMessages+" message envoyés")
    	    .setContentText("Vous avez envoyé un message")
    	    .setSmallIcon(R.drawable.ic_content_unread);
    	
    	    mNotifyBuilder.setContentText("")
    	        .setNumber(numMessages);
    	    mNotificationManager.notify(
    	    		ID_NOTIFICATION,
    	            mNotifyBuilder.build());
    }
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "onBind", Toast.LENGTH_SHORT).show();
		return null;
	}
	@Override
	 public boolean onUnbind(Intent intent) {
	  return super.onUnbind(intent);
	 }
	@Override
	public void onDestroy(){
		MaBaseSettingsGestion maBaseSettingsGestion = new MaBaseSettingsGestion(this);
		maBaseSettingsGestion.open();
    	if(!maBaseSettingsGestion.isServiceOn()){
        	super.onDestroy();
        }
    	maBaseSettingsGestion.close();
	}
	public static void initNBMess(){
		
		numMessages = 0;
	}
	
}
