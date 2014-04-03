package com.gizmoideas.mobile.memap.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.gizmoideas.mobile.memap.model.pojo.Note;
import com.gizmoideas.mobile.memap.model.pojo.Zone;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.MarkerOptions;



public class NoteContent {

	public static  List<Note> NOTES = new ArrayList<Note>();
	public static  Map<String,Note> NOTEMAP = new HashMap<String,Note>();
	public static  Map<String,Note> NOTE_MAP_ACTIVE = new HashMap<String,Note>();

	static {
		 
		 
		 addItem(new Note("name1", "message1", new Date().getTime(), new Zone(1000, 10.4220761,-75.5529252, "UNDIFINED")));
		 addItem(new Note("name2", "message2", new Date().getTime(), new Zone(1000, 10.6220761,-75.6529252, "UNDIFINED")));
		 addItem(new Note("name3", "message3", new Date().getTime(), new Zone(1000, 10.7220761,-75.7529252, "UNDIFINED")));
	}

	
	public static void addItem(Note note) {
		NOTES.add(note);
		NOTEMAP.put(note.getId()+"", note);
	}	
	
	public static void removeItem(Note note) {
		NOTEMAP.remove(note.getId()+"");
		NOTES.remove(note);	
		
	}	
	
	public static void makeMarkerList(GoogleMap mMap){		 
		
		 Iterator<Note> iterator = NoteContent.NOTES.iterator();
		 while(iterator.hasNext()){
			 Note object=iterator.next();
			 mMap.addMarker(new MarkerOptions().position(object.getZone().position())
					 						   .title(object.getId()+". "+object.getName()).snippet(object.getMessage()).draggable(true)
					 						   
					 		);
			 mMap.addCircle(new CircleOptions().center(object.getZone().position())
					 						.radius(object.getZone().getRadio())
					 						.visible(true).fillColor(0X507102A8)
					 						.strokeColor(0X207102A8)
					 						.strokeWidth(4));
		 }	
		 		 
	}	
	
	public static void addMarker(GoogleMap mMap,Note newNote){
		NoteContent.addItem(newNote);
		mMap.addMarker(new MarkerOptions().position(newNote.getZone().position())
				   .title(newNote.getId()+". "+newNote.getName()).snippet(newNote.getMessage()).draggable(true)
				   
		);
		mMap.addCircle(new CircleOptions().center(newNote.getZone().position())
										.radius(newNote.getZone().getRadio())
										.visible(true)
										.fillColor(0X507102A8)
										.strokeColor(0X207102A8)
										.strokeWidth(4));
	}
	
	public static void moveMarker(GoogleMap mMap,Note editNote){
		mMap.addCircle(new CircleOptions().center(editNote.getZone().position())
				.radius(editNote.getZone().getRadio())
				.visible(true)
				.fillColor(0X507102A8)
				.strokeColor(0X207102A8)
				.strokeWidth(4));
	}
	
}
