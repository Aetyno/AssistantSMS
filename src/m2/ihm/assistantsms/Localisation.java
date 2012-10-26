package m2.ihm.assistantsms;

import java.io.IOException;
import java.util.List;

import resources.*;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;


public class Localisation extends MapActivity implements OnTouchListener {

	private MapView mapView;
	private EditText localisation = null;
	private Geocoder geocoder ;
	private ListItimizedOverlay itemizedoverlay;
	private MapController mc;
	
    @SuppressLint("NewApi")
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	

    	geocoder = new Geocoder(this);
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_localisation);
        
        mapView = (MapView) this.findViewById(R.id.mapViewLocalisation);
        mapView.setBuiltInZoomControls(true);
        mapView.setOnTouchListener(this);
        
        localisation = (EditText) findViewById(R.id.editTextLocalisationMap);
        Drawable drawable = this.getResources().getDrawable(R.drawable.ic_launcher);
		itemizedoverlay = new ListItimizedOverlay(drawable,this);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_localisation, menu);
        return true;
    }
    public void recherche(View v) {
    	List<Address> listadress;
		try {
			listadress = geocoder.getFromLocationName(localisation.getText().toString(), 1);
			
			if(listadress.size() > 0){
				double longitude = listadress.get(0).getLongitude();
				double latitude = listadress.get(0).getLatitude();
				GeoPoint geoPoint = new GeoPoint((int) (latitude * 1000000.0),(int) (longitude * 1000000.0));
				marqueurLocalisation(geoPoint);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onTouch(View v, MotionEvent event) {
		 if (event.getAction() == 1) {
			 GeoPoint p = mapView.getProjection().fromPixels(
			 (int) event.getX(),
			 (int) event.getY());
			 marqueurLocalisation(p);
			 List<Address> listadress;
			try {
				listadress = geocoder.getFromLocation(p.getLatitudeE6()/1000000.0,
						 			p.getLongitudeE6()/1000000.0, 1);
				if(listadress.size() > 0)
				 this.localisation.setText(listadress.get(0).toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 return false;
	}
	private void marqueurLocalisation(GeoPoint geoPoint){
		itemizedoverlay.remove();
		OverlayItem overlayitem = new OverlayItem(geoPoint, 
    			"", "");
    	itemizedoverlay.addOverlayItem(overlayitem);
		mc = mapView.getController();
		mc.setCenter(geoPoint);

		List<Overlay> mapOverlays = mapView.getOverlays();
		mapOverlays.add(itemizedoverlay);
	}
	
}
