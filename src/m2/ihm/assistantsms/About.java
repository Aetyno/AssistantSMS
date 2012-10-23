package m2.ihm.assistantsms;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class About extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_about, menu);
        return true;
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
            case R.id.menu_settings:
            	intent = new Intent(this, Setting.class);
                startActivity(intent);
                finish();
            	return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
