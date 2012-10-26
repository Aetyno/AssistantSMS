package m2.ihm.assistantsms;

import m2.ihm.assistantsms.base_de_donnees.MaBaseSettingsGestion;
import m2.ihm.assistantsms.service.ServiceEnvoieSMS;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

@SuppressLint("NewApi")
public class Setting extends Activity {

	private MaBaseSettingsGestion maBaseSettingsGestion;
	
	private Switch switch1; 
	private Switch switch2;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        
        maBaseSettingsGestion = new MaBaseSettingsGestion(this);
        
        switch1 = (Switch) findViewById(R.id.switch1);
        switch2 = (Switch) findViewById(R.id.switch2);
        
        maBaseSettingsGestion.open();
        
        getActionBar().setDisplayHomeAsUpEnabled(true);
        if(maBaseSettingsGestion.isServiceOn()){
        	switch1.setChecked(true);
        }
        else{
        	switch1.setChecked(false);
        }
        
        
        
        if(maBaseSettingsGestion.isNotificationOn()){
        	switch2.setChecked(true);
        }
        else{
        	switch2.setChecked(false);
        }
        
        maBaseSettingsGestion.close();
        

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
	            case R.id.menu_history:
	            	intent = new Intent(this, History.class);
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
	            case R.id.menu_about:
	            	intent = new Intent(this, About.class);
	                startActivity(intent);
	                finish();
	            	return true;
	        }
	        return super.onOptionsItemSelected(item);
	    }
    
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_setting, menu);
        return true;
    }
    
    public void switchService(View view){
    	maBaseSettingsGestion.open();
    	if(switch1.isChecked()){
        	maBaseSettingsGestion.setService(true);
        }
        else{
        	maBaseSettingsGestion.setService(false);
        }
    	maBaseSettingsGestion.close();
    }
    
    public void switchNotification(View view){
    	maBaseSettingsGestion.open();
    	if(switch2.isChecked()){
        	maBaseSettingsGestion.setNotification(true);
        }
        else{
        	maBaseSettingsGestion.setNotification(false);
        }
    	maBaseSettingsGestion.close();
    }
}
