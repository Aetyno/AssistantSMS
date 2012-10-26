package m2.ihm.assistantsms.service;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import resources.SMS;
import m2.ihm.assistantsms.CreateSMS;
import m2.ihm.assistantsms.History;
import m2.ihm.assistantsms.R;
import m2.ihm.assistantsms.base_de_donnees.MaBaseSMSGestion;
import m2.ihm.assistantsms.base_de_donnees.MaBaseSettingsGestion;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
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

	       Toast.makeText(this, "service", Toast.LENGTH_LONG).show();
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

		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    	Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		for(SMS sms:listeSMS){
			
			Location smsLoc = new Location(sms.getLocalisation());
			float distance = location.distanceTo(smsLoc);
			if(distance < 1000){
				String destinataire[] = sms.getDestinataire().split(";");
				for(int i = 0; i < destinataire.length; i++){
					sendSMS(sms.getDestinataire(), sms.getMessage());
				Toast.makeText(getApplicationContext(), "envoie mess a "+sms.getDestinataire(), Toast.LENGTH_SHORT).show();
				}
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
	private void envoieSMSDate() {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Timestamp time = new Timestamp(cal.getTimeInMillis());
		listeSMS = maBaseGestion.getAllSMSPrepared();

		for(SMS sms:listeSMS){
			if(sms.getDate().equals(time)){
				String destinataire[] = sms.getDestinataire().split(",");
				for(int i = 0; i < destinataire.length; i++){
					sendSMS(destinataire[i], sms.getMessage());
				Toast.makeText(getApplicationContext(), "envoie mess a "+destinataire[i], Toast.LENGTH_SHORT).show();
				}maBaseGestion.updateSMS(sms.getID(),
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

	    Intent resultIntent = new Intent(this, History.class);
	    TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		stackBuilder.addParentStack(History.class);
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent =
			        stackBuilder.getPendingIntent( 0, PendingIntent.FLAG_UPDATE_CURRENT);
	 	mNotifyBuilder.setContentIntent(resultPendingIntent);
	    mNotificationManager.notify(ID_NOTIFICATION, mNotifyBuilder.build());
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
