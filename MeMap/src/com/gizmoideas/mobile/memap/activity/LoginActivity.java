package com.gizmoideas.mobile.memap.activity;

import com.gizmoideas.mobile.memap.R;
import com.gizmoideas.mobile.memap.service.PositionReceiver;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class LoginActivity extends Activity {

	PositionReceiver position;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		//super.registerReceiver(receiver, new IntentFilter("com.gizmoideas.mobile.memap"));
		//Intent intent = new Intent(this, BackgroundPosition.class);
		//super.startService(intent);			
		//position.setPosition(this);	
		position  = new PositionReceiver(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);		
		return true;
	}
	
	
	/*private BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle bundel=intent.getExtras();
			textView.setText(bundel.getString(BackgroundPosition.latitude));
			textView.setText(bundel.getString(BackgroundPosition.longitude));
			
		}
		

	   
	  };*/

	public void openMap(View view){
		
		Intent intent = new Intent(this,MyMapActivity.class);
		startActivity(intent);
	}
	
	public void openNotesList(View view){
		
		Intent intent = new Intent(this,NoteListActivity.class);
		startActivity(intent);
	}
	  
}

