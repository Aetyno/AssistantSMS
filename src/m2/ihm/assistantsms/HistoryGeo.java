package m2.ihm.assistantsms;

import java.util.List;
import java.util.Locale;

import resources.SMS;
import resources.ListItimizedOverlay;
import m2.ihm.assistantsms.base_de_donnees.MaBaseGestion;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

@SuppressLint("NewApi")
public class HistoryGeo extends MapActivity {

	private List<SMS> listeSMS;
	private MapView mapView;
	private MapController mc;
	private Geocoder geocoder;
	private GeoPoint location;
	private Drawable drawable ;
	private ListItimizedOverlay itemizedoverlay;
	private MyLocationOverlay myLocation = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		//initialise la liste des messages envoyés
		MaBaseGestion maBaseGestion = new MaBaseGestion(this);        
        maBaseGestion.open();
        listeSMS = maBaseGestion.getAllSMS();
        maBaseGestion.close();
        
        getActionBar().setDisplayHomeAsUpEnabled(true);
        //initialise google map
		this.mapView =  new MapView(this,this.getResources().getString(R.string.mapKey));
		this.mapView.setClickable(true);
 		this.mc = this.mapView.getController();
 		//localisation 
 		myLocation = new MyLocationOverlay(getApplicationContext(), mapView);
 		mapView.getOverlays().add(myLocation);
 		myLocation.enableMyLocation();
 		double latitude = 43.5878314;
 		double longitude = 1.4367727;
 		this.location = new GeoPoint((int) (latitude * 1000000.0),(int) (longitude * 1000000.0));
		this.mc.setCenter(this.location);
 		this.mc.setZoom(17);
 		this.mapView.setSatellite(false);
 		this.mapView.invalidate();
 		this.mapView.setBuiltInZoomControls(true);
 		
 		geocoder = new Geocoder(getApplicationContext(),Locale.getDefault());

    	drawable = this.getResources().getDrawable(R.drawable.ic_launcher);
    	itemizedoverlay = new ListItimizedOverlay(drawable);
 		if(listeSMS.size() > 0){
	 		for(SMS sms:listeSMS)
	 			AjoutMarqueur(sms);
			List<Overlay> mapOverlays = mapView.getOverlays();
			mapOverlays.add(itemizedoverlay);
		}
 		
		this.setContentView(this.mapView);
		
		
   	}
    
    public void AjoutMarqueur(SMS sms){
		//test marqueur toulouse
 		double latitude = 43.5878314;
 		double longitude = 43.5878314;
    	GeoPoint geoPoint = new GeoPoint((int) (latitude * 1000000.0),(int) (longitude * 1000000.0));
    	OverlayItem overlayitem = new OverlayItem(geoPoint, "Hello from", "Tahiti");
    	itemizedoverlay.addOverlayItem(overlayitem);
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
