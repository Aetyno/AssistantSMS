package m2.ihm.assistantsms;

import java.util.List;

import resources.SMS;

import m2.ihm.assistantsms.adapter.SMSAdapter;
import m2.ihm.assistantsms.base_de_donnees.MaBaseGestion;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Main extends ListActivity {
	private List<SMS> listeSMS;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MaBaseGestion maBaseGestion = new MaBaseGestion(this);
        
      /*  maBaseGestion.open();
        listeSMS = maBaseGestion.getAllSMS();
        maBaseGestion.close();
        SMSAdapter adapter = new SMSAdapter(this, listeSMS);
        setListAdapter(adapter);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	Intent intent;
    	switch (item.getItemId()) {
            case R.id.menu_create_sms:
                // app icon in action bar clicked; go home
                intent = new Intent(this, CreateSMS.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            case R.id.menu_history:
            	intent = new Intent(this, History.class);
                startActivity(intent);
            	return true;
            case R.id.menu_history_geo:
            	intent = new Intent(this, HistoryGeo.class);
                startActivity(intent);
            	return true;
            case R.id.menu_settings:
            	intent = new Intent(this, Setting.class);
                startActivity(intent);
            	return true;
            case R.id.menu_about:
            	intent = new Intent(this, About.class);
                startActivity(intent);
            	return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    public void clicSMS(View V){
    	Intent intent = new Intent(this, ModifySMS.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    
}
