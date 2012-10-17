package m2.ihm.assistantsms;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

@SuppressLint("NewApi")
public class HistoryGeo extends MapActivity {

	private MapView mapView;
	private MapController mc;
	private GeoPoint location;
    @Override
    public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        
		this.mapView =  new MapView(this,this.getResources().getString(R.string.mapKey));
		this.mapView.setClickable(true);
 		this.mc = this.mapView.getController();
 		//localisation TOULOUSE
 		double latitude = 43.5878314;
 		double longitude = 1.4367727;
 		this.location = new GeoPoint((int) (latitude * 1000000.0),(int) (longitude * 1000000.0));
		this.mc.setCenter(this.location);
 		this.mc.setZoom(17);
 		this.mapView.setSatellite(true);
 		this.mapView.invalidate();
 		this.mapView.setBuiltInZoomControls(true);

		this.setContentView(this.mapView);
   	}

	public GeoPoint getLocation() {
		return location;
	}

	public void setLocation(GeoPoint location) {
		this.location = location;
		this.mc.setCenter(this.location);
		this.mapView.invalidate();
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_history_geo, menu);
        return true;
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
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
    		mapView.setSatellite(true);
    		return true;
    	} else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
    		mapView.setSatellite(false);
    		return true;
    	} 
    	return super.onKeyDown(keyCode, event);
    }	
}
