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
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.Toast;


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

		mc = mapView.getController();
        mc.setZoom(10);
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
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        Intent intent;
	        Toast toast;
	    	
	    	switch (item.getItemId()) {
	            case android.R.id.home:
	            	Intent parentActivityIntent = new Intent(this, CreateSMS.class);
	                parentActivityIntent.addFlags(
	                        Intent.FLAG_ACTIVITY_CLEAR_TOP |
	                        Intent.FLAG_ACTIVITY_NEW_TASK);
	                startActivity(parentActivityIntent);
	                finish();
	                return true;
	                
	            case R.id.menu_accept:
	            	if( localisation.getText().toString().equals("")){
	            		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
	          			alertDialogBuilder.setTitle("Erreur");
	        			alertDialogBuilder
	        				.setMessage("Vous n'avez pas de localisation")
	        				.setCancelable(false)
	        				.setPositiveButton("Yes", null);
	        			AlertDialog alertDialog = alertDialogBuilder.create();
	        			alertDialog.show();
	            	}
	            	else {

	            		EditText localisationCreate = (EditText) findViewById(R.id.editTextLocalisation);
	            		localisationCreate.setText(localisation.getText().toString());
	            	}
	            	intent = new Intent(this, CreateSMS.class);
	                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                startActivity(intent);
	            	finish();
	            	return true;
	            	
	            case R.id.menu_cancel:

	            	toast = Toast.makeText(getApplicationContext(), "Annulation", Toast.LENGTH_SHORT);
	            	toast.show();
	            	
	            	intent = new Intent(this, Main.class);
	                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                startActivity(intent);
	                finish();
	            	return true;
	            
	            default:
	                return super.onOptionsItemSelected(item);
	        }
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
