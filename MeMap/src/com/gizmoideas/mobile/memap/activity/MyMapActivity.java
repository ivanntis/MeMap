package com.gizmoideas.mobile.memap.activity;



import com.gizmoideas.mobile.memap.R;
import com.gizmoideas.mobile.memap.model.dto.NoteContent;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;



public class MyMapActivity extends FragmentActivity {

	private GoogleMap mMap;
	private LatLng positionNew;
	private Marker marketToDelete;
	private LatLng positionBeforeMove;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		//((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		setUpMapIfNeeded();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void setUpMapIfNeeded() {
	    // Do a null check to confirm that we have not already instantiated the map.
	    if (mMap == null) {	       
	    	mMap= ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
	        // Check if we were successful in obtaining the map.
	        if (mMap != null) {
	        	
	        	mMap.setMyLocationEnabled(true);	
	        	mMap.setOnMarkerClickListener(new OnMarkerClickListener(){

					@Override
					public boolean onMarkerClick(Marker arg0) {
						return false;
					}
	        		
	        	});	        	
	        	mMap.setOnMapLongClickListener(new OnMapLongClickListener(){
					@Override
					public void onMapLongClick(LatLng arg0) {						
						positionNew=arg0;
						loadCreateMarker();
					}
	        		
	        	});	        	
	        	mMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {					
					@Override
					public void onInfoWindowClick(Marker arg0) {
						openActivity(arg0.getTitle().substring(0, arg0.getTitle().indexOf(".")));
						
					}
				});	        	
	        	mMap.setOnMarkerDragListener(new OnMarkerDragListener(){
					@Override
					public void onMarkerDrag(Marker arg0) {						
					}
					@Override
					public void onMarkerDragEnd(Marker arg0) {						
						marketToDelete=arg0;
						loadRemoveMarker();	
					}
					
					@Override
					public void onMarkerDragStart(Marker arg0) {
						positionBeforeMove=arg0.getPosition();
						
					}	        		
	        	});
	        	
	        	mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
	        	LatLng cartagena = new LatLng(10.3631082,-75.5032412);
            	CameraPosition camPos = new CameraPosition.Builder()
									            	      .target(cartagena)  
									            	      .zoom(11)         
									            	      .bearing(0)      
									            	      .tilt(45)        
									            	      .build();            	 
            	CameraUpdate camUpd=CameraUpdateFactory.newCameraPosition(camPos);
            	mMap.moveCamera(camUpd);               	
            	NoteContent.makeMarkerList(mMap);
            		
	        }
	    }
	}
	
	public void openActivity(String id){
		Intent detailIntent = new Intent(this, NoteDetailActivity.class);
		detailIntent.putExtra(NoteDetailFragment.ARG_ITEM_ID, id);
		startActivity(detailIntent);
	}
	
	
	
	public void loadCreateMarker(){	
		
		Intent newNoteIntent = new Intent(this, NewNoteActivity.class);
		newNoteIntent.putExtra("longitude", positionNew.longitude+"");
		newNoteIntent.putExtra("latitude", positionNew.latitude+"");		
		startActivity(newNoteIntent);		
        //dialog.show(getSupportFragmentManager(), "NewNoteDialog");		
		/*	AlertDialog.Builder builder = new AlertDialog.Builder(this);  
			LayoutInflater inflater = this.getLayoutInflater();

		    // Inflate and set the layout for the dialog
		    // Pass null as the parent view because its going in the dialog layout
		    builder.setView(inflater.inflate(R.layout.fragment_new_mem, null))
				   //.setTitle("Nueva")  
				   //.setIcon(R.drawable.iconics)  
				   .setCancelable(true)
				   .setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){  
													public void onClick(DialogInterface dialog, int id) {  
														addMark(positionNew);
														dialog.cancel();
													}  
							        			}  
										) 
				   .setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){  
													public void onClick(DialogInterface dialog, int id) {  
														dialog.cancel();
													}  
							        			}  
						   			);  
			AlertDialog alert = builder.create();  
			alert.show(); */
		}
	
	public void removeMark(Marker removeMarker){
		removeMarker.remove();				
		NoteContent.removeItem(NoteContent.NOTEMAP.get(removeMarker.getTitle().substring(0, removeMarker.getTitle().indexOf("."))));		
		
	}
	
	public void moveMark(Marker moveMarker){
		NoteContent.moveMarker(mMap, NoteContent.NOTEMAP.get(moveMarker.getTitle().substring(0, moveMarker.getTitle().indexOf("."))));
	}
	
	public void loadRemoveMarker(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);  
		builder.setMessage("Qiuere mover nota?")
				//.setTitle("Eliminar!")  
				//.setIcon(R.drawable.iconics)  
				.setCancelable(false)
				.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						marketToDelete.setPosition(positionBeforeMove);
						dialog.cancel();
						
					}
				})
				.setPositiveButton("Eliminar", new DialogInterface.OnClickListener(){  
						public void onClick(DialogInterface dialog, int id) {  
							removeMark(marketToDelete);
							dialog.cancel();
						}  
		    		}  
				)  
				.setNegativeButton("Mover", new DialogInterface.OnClickListener(){  
						public void onClick(DialogInterface dialog, int id) {		
							moveMark(marketToDelete);
							dialog.cancel();
						}  
		    		}  
				);  
		AlertDialog alert = builder.create();  
		alert.show(); 
	}

	
	
	
		
	
	
	
}
