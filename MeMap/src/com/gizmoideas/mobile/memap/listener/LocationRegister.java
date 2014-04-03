package com.gizmoideas.mobile.memap.listener;

import java.util.Iterator;
import com.gizmoideas.mobile.memap.model.dto.NoteContent;
import com.gizmoideas.mobile.memap.model.pojo.Note;
import com.gizmoideas.mobile.memap.service.PositionReceiver;
import com.gizmoideas.mobile.memap.util.DataNotable;
import com.google.android.gms.maps.model.LatLng;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class LocationRegister implements LocationListener {	
	
	 private LocationManager locationManager;
	 private long minDistUpdate=100;//En Metros
	 private long minTimeUpdate=15000;//En Milisegundos
	 private long maxTimeAlert=-1;
	 private Context context;
	 PositionReceiver position;
	 PendingIntent locationIntent;
		
	public LocationRegister(Context context){
		this.context=context;
		Intent intent = new Intent(context, PositionReceiver.class);
		locationIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
		loadTypeLocation();
	}

	
	@Override
	public void onLocationChanged(Location arg0) {
		
		activateNotes(new LatLng(arg0.getLatitude(),arg0.getLongitude()));
		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub

	}
	
	public  void activateNotes(LatLng position){
		NoteContent.NOTE_MAP_ACTIVE.clear();
		Iterator<Note> it=NoteContent.NOTES.iterator();
		while (it.hasNext()) {			
			Note note=it.next();
			locationManager.addProximityAlert(note.getZone().getLatitude(),
											  note.getZone().getLatitude(),
											  note.getZone().getRadio(),
											  maxTimeAlert, 
											  locationIntent);		

			if (LocationManager.KEY_PROXIMITY_ENTERING.equals("true")){
				note.setStatus(DataNotable.ACTIVE_NOTIFICATION);
				NoteContent.NOTE_MAP_ACTIVE.put(note.getId()+"", note);
			}else{
				note.setStatus(DataNotable.INACTIVE_NOTIFICATION);
			}			
		}		
	}
	
	private void  loadTypeLocation(){
		locationManager=(LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria=new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setBearingAccuracy(Criteria.ACCURACY_HIGH);
		criteria.setCostAllowed(true);
		criteria.setPowerRequirement(Criteria.POWER_MEDIUM);
		
		locationManager.requestLocationUpdates(locationManager.getBestProvider(criteria, true),this.minTimeUpdate,this.minDistUpdate,this);		
	}

}
