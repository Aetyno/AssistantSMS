package m2.ihm.assistantsms;

import java.util.List;

import resources.SMS;

import m2.ihm.assistantsms.adapter.SMSAdapter;
import m2.ihm.assistantsms.base_de_donnees.MaBaseSMSGestion;
import m2.ihm.assistantsms.service.ServiceEnvoieSMS;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class History extends ListActivity {

    @SuppressLint("NewApi")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MaBaseSMSGestion maBaseGestion = new MaBaseSMSGestion(this);
        maBaseGestion.open();
        List<SMS> listSMS = maBaseGestion.getAllSMSSent();
        maBaseGestion.close();
        SMSAdapter adapter = new SMSAdapter(this, listSMS);
        setListAdapter(adapter);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.cancel(ServiceEnvoieSMS.ID_NOTIFICATION);
		ServiceEnvoieSMS.initNBMess();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_history, menu);
        return true;
    }
    
    public void clicSMS(View view){
    	
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	Intent intent;
        switch (item.getItemId()) {
            case android.R.id.home:
                intent = new Intent(this, Main.class);
                intent.addFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            case R.id.menu_history_geo:
            	intent = new Intent(this, HistoryGeo.class);
                startActivity(intent);
                finish();
            	return true;
            case R.id.menu_main:
            	intent = new Intent(this, Main.class);
                startActivity(intent);
                finish();
            	return true;
            case R.id.menu_settings:
            	intent = new Intent(this, Setting.class);
                startActivity(intent);
                finish();
            	return true;
            case R.id.menu_about:
            	intent = new Intent(this, About.class);
                startActivity(intent);
                finish();
            	return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
