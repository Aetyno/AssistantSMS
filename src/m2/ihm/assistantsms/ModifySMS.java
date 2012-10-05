package m2.ihm.assistantsms;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ModifySMS extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_sms);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_modify_sms, menu);
        return true;
    }
}
