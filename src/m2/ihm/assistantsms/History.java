package m2.ihm.assistantsms;

import java.util.List;

import m2.ihm.assistantsms.adapter.SMSAdapter;
import m2.ihm.assistantsms.model.AssistantSMS;
import m2.ihm.assistantsms.model.SMS;
import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;

public class History extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<SMS> listSMS = AssistantSMS.getHistorique();
        SMSAdapter adapter = new SMSAdapter(this, listSMS);
        setListAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_history, menu);
        return true;
    }
}
