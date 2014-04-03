package com.gizmoideas.mobile.memap.service;



import java.util.Iterator;

import com.gizmoideas.mobile.memap.model.dto.NoteContent;
import com.gizmoideas.mobile.memap.model.pojo.Note;
import com.gizmoideas.mobile.memap.util.DataNotable;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
//import android.location.LocationManager;
import android.support.v4.content.WakefulBroadcastReceiver;

public class PositionReceiver extends WakefulBroadcastReceiver  implements LocationListener{

	//private LocationManager location;
	
	 private LocationManager locationManager;
	 private long minDistUpdate=100;//En Metros
	 private long minTimeUpdate=15000;//En Milisegundos
	 private long maxTimeAlert=-1;
	 PendingIntent locationIntent;
	 private Context context;
	
	 public PositionReceiver(Context context) {
			super();
			this.context=context;
			
			loadTypeLocation();
			// TODO Auto-generated constructor stub
			//this.context=context;
		}
	 
	@Override
	public void onReceive(Context context, Intent intent) {
		
		Intent service = new Intent(context, BackgroundPosition.class);
		startWakefulService(context, service);
		
	}

	//public void setPosition(){
		
		//Intent intent = new Intent(context, PositionReceiver.class);
		//locationIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
		//locationManager=(LocationManager)context.getSystemService(Context.LOCATION_SERVICE);		
		//locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,this.minTimeUpdate,this.minDistUpdate,locationIntent);
		
	//}

	
	
	//public  void activateNotes(PendingIntent intent){
	public  void activateNotes(){
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
		Intent intent = new Intent(this.context, PositionReceiver.class);
		locationIntent = PendingIntent.getBroadcast(this.context, 0, intent, 0);
		locationManager=(LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria=new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setBearingAccuracy(Criteria.ACCURACY_HIGH);
		criteria.setCostAllowed(true);
		criteria.setPowerRequirement(Criteria.POWER_MEDIUM);
		
		locationManager.requestLocationUpdates(locationManager.getBestProvider(criteria, true),this.minTimeUpdate,this.minDistUpdate,locationIntent);		
		
		ComponentName receiver = new ComponentName(this.context, StartServiceReceiver.class);
	    PackageManager pm = context.getPackageManager();

	    pm.setComponentEnabledSetting(receiver,
	            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
	            PackageManager.DONT_KILL_APP);  
	}

	@Override
	public void onLocationChanged(Location location) {
	  ;
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}


}
