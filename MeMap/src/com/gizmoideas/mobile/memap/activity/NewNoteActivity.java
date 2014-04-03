package com.gizmoideas.mobile.memap.activity;

import java.util.Date;

import com.gizmoideas.mobile.memap.R;
import com.gizmoideas.mobile.memap.model.dto.NoteContent;
import com.gizmoideas.mobile.memap.model.pojo.Note;
import com.gizmoideas.mobile.memap.model.pojo.Zone;
import com.google.android.gms.maps.model.LatLng;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class NewNoteActivity extends Activity {
	
	private LatLng position;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_new_mem);
		position= new LatLng(Double.parseDouble(getIntent().getStringExtra("latitude")),Double.parseDouble(getIntent().getStringExtra("longitude")));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.login, menu);		
		return true;
	}
	
	public void  saveNote(View view){
		Intent mapNoteIntent = new Intent(this, MyMapActivity.class);		
		Note newNote=new Note("Nuevo Recordatorio", "nuevo Detalle de recordatoria", new Date().getTime(), new Zone(1000, position, "Undifined"));
		NoteContent.addItem(newNote);
		startActivity(mapNoteIntent);
		
	}
	
	public void cancelNote(View view){
		Intent mapNoteIntent = new Intent(this, MyMapActivity.class);
		startActivity(mapNoteIntent);
	}
	
	
}
