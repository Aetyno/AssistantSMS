package m2.ihm.assistantsms;

import m2.ihm.assistantsms.base_de_donnees.MaBaseSettingsGestion;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Switch;

public class Setting extends Activity {

	private MaBaseSettingsGestion maBaseSettingsGestion;
	Intent serviceSendSMS;
	
	private Switch switch1; 
	private Switch switch2;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        serviceSendSMS = new Intent(this, ServiceSendSMS.class);
        
        maBaseSettingsGestion = new MaBaseSettingsGestion(this);
        
        switch1 = (Switch) findViewById(R.id.switch1);
        switch2 = (Switch) findViewById(R.id.switch2);
        
        maBaseSettingsGestion.open();
        
        
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_setting, menu);
        return true;
    }
    
    public void switchService(View view){
    	maBaseSettingsGestion.open();
    	if(switch1.isChecked()){
        	maBaseSettingsGestion.setService(true);
        	startService(serviceSendSMS);
        }
        else{
        	maBaseSettingsGestion.setService(false);
        	stopService(serviceSendSMS);
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
