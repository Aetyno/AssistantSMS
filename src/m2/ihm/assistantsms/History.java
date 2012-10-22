package m2.ihm.assistantsms;

import java.util.List;

import resources.SMS;

import m2.ihm.assistantsms.adapter.SMSAdapter;
import m2.ihm.assistantsms.base_de_donnees.MaBaseSMSGestion;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ListActivity;
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
        switch (item.getItemId()) {
            case android.R.id.home:
                // This is called when the Home (Up) button is pressed
                // in the Action Bar.
                Intent parentActivityIntent = new Intent(this, Main.class);
                parentActivityIntent.addFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(parentActivityIntent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
