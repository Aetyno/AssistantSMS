package m2.ihm.assistantsms;

import java.io.IOException;
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
import android.location.Address;
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
	//private Geocoder geocoder = new Geocoder(this, Locale.getDefault());;
	private GeoPoint location;
	private ListItimizedOverlay itemizedoverlay;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {

		MaBaseGestion maBaseGestion = new MaBaseGestion(this);        
        maBaseGestion.open();
        listeSMS = maBaseGestion.getAllSMS();
        maBaseGestion.close();
        
        getActionBar().setDisplayHomeAsUpEnabled(true);
       
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history_geo);
		
		mapView = (MapView) this.findViewById(R.id.mapView);
		mapView.setBuiltInZoomControls(true);
		
		Drawable drawable = this.getResources().getDrawable(R.drawable.ic_launcher);
		itemizedoverlay = new ListItimizedOverlay(drawable,this);
		
		//toulouse
		double latitude = 43.5878314;
 		double longitude =1.4367727;
 		location = new GeoPoint((int) (latitude * 1000000.0),(int) (longitude * 1000000.0));
    			
		if(listeSMS.size() > 0)
			for(SMS sms:listeSMS)
	 			AjoutMarqueur(sms);
		List<Overlay> mapOverlays = mapView.getOverlays();
		mapOverlays.add(itemizedoverlay);
		
		mc = mapView.getController();
		mc.setCenter(location);
		mc.setZoom(10);
   	}
    
    public void AjoutMarqueur(SMS sms){
    	/*try {
			List<Address> listadress = geocoder.getFromLocationName("Toulouse", 1);
			double longitude = listadress.get(0).getLongitude();
			double latitude = listadress.get(0).getLatitude(); GeoPoint geoPoint = new GeoPoint((int) (latitude * 1000000.0),(int) (longitude * 1000000.0));
	    	location = geoPoint;
	    	OverlayItem overlayitem = new OverlayItem(geoPoint, "Hello from", "Tahiti");
	    	itemizedoverlay.addOverlayItem(overlayitem);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		double latitude = 43.5878314;
 		double longitude =1.4367727;
		GeoPoint geoPoint = new GeoPoint((int) (latitude * 1000000.0),(int) (longitude * 1000000.0));
    	location = geoPoint;
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
            	return true;
            case R.id.menu_main:
            	intent = new Intent(this, Main.class);
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
