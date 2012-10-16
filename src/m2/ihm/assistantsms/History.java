package m2.ihm.assistantsms;

import java.util.List;

import resources.SMS;

import m2.ihm.assistantsms.adapter.SMSAdapter;
import m2.ihm.assistantsms.base_de_donnees.MaBaseGestion;
import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;
import android.view.View;

public class History extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MaBaseGestion maBaseGestion = new MaBaseGestion(this);
        
        maBaseGestion.open();
        List<SMS> listSMS = maBaseGestion.getAllSMS();
        maBaseGestion.close();
        SMSAdapter adapter = new SMSAdapter(this, listSMS);
        setListAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_history, menu);
        return true;
    }
    
    public void clicSMS(View view){
    	
    }
}
