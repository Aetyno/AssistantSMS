package m2.ihm.assistantsms;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import android.os.Bundle;
import android.view.Menu;

public class HistoryGeo extends MapActivity {

	private MapView mapView;
	private MapController mc;
	private GeoPoint location;
    @Override
    public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
       	
		this.mapView =  new MapView(this,this.getResources().getString(R.string.mapKey));
		this.mapView.setClickable(true);
 		this.mc = this.mapView.getController();
 		double latitude = 50.606;
 		double longitude = 3.15;
 		this.location = new GeoPoint((int) (latitude * 1000000.0),(int) (longitude * 1000000.0));
		this.mc.setCenter(this.location);
 		this.mc.setZoom(17);
 		this.mapView.setSatellite(true);
 		this.mapView.invalidate();

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

}
