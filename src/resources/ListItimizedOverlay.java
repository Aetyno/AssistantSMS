package resources;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class ListItimizedOverlay extends ItemizedOverlay<OverlayItem>
{
	 private Context context;
	 private ArrayList<OverlayItem> arrayListOverlayItem = new ArrayList<OverlayItem>();
	 
	 public ListItimizedOverlay(Drawable defaultMarker)
	 {
		 super(boundCenterBottom(defaultMarker));
	 }
	 public ListItimizedOverlay(Drawable defaultMarker, Context pContext)
	 {
		 super(boundCenterBottom(defaultMarker));
		 this.context = pContext;
	 }
	 @Override
	 protected OverlayItem createItem(int i)
	 {
		 return arrayListOverlayItem.get(i);
	 }
	
	 @Override
	 public int size()
	 {
		 return arrayListOverlayItem.size();
	 }
	
	 public void addOverlayItem(OverlayItem overlay)
	 {
		  arrayListOverlayItem.add(overlay);
		  populate();
	 }
	 public void remove()
	 {
		  arrayListOverlayItem.clear();
		  populate();
	 }
	 @Override
	 protected boolean onTap(int index)
	 {
			  OverlayItem item = arrayListOverlayItem.get(index);
			 AlertDialog.Builder dialog = new AlertDialog.Builder(context);
			 dialog.setTitle(item.getTitle());
			 dialog.setMessage(item.getSnippet());
			 dialog.show();
			 return true;
	 }
	 @Override
	 public boolean onTouchEvent(MotionEvent event, MapView mapView)
	 {
		 //---when user lifts his finger---
		 if (event.getAction() == 1) {
			 GeoPoint p = mapView.getProjection().fromPixels(
			 (int) event.getX(),
			 (int) event.getY());
			 MapController mc = mapView.getController();
				mc.setCenter(p);
				mc.setZoom(10);
		 }
		 return false;
	 }

}
