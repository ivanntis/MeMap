package com.gizmoideas.mobile.memap.service;

import com.gizmoideas.mobile.memap.R;
import com.gizmoideas.mobile.memap.activity.NoteDetailActivity;
import com.gizmoideas.mobile.memap.activity.NoteDetailFragment;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class BackgroundPosition extends IntentService {
	
	public static String latitude="LATITUDE";
	public static String longitude="LONGITUDE";
	private static int count=0;

	public BackgroundPosition() {
		super("MeMapService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		try{		
			count+=1;
				sendNotification("latitude:  longitude: "+count	,latitude,longitude);			 
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
		
	private void sendNotification(String notify,String latitude,String longitude){
		NotificationCompat.Builder mBuilder =
		        new NotificationCompat.Builder(this)
		        .setSmallIcon(R.drawable.ic_launcher)
		        .setContentTitle("MeMap notification")
		        .setContentText(notify)
		        .setAutoCancel(true);
		// Creates an explicit intent for an Activity in your app
		/*Intent resultIntent = new Intent(this, LoginActivity.class);
		resultIntent.putExtra("latitude", latitude);
		resultIntent.putExtra("longitude", longitude);
		resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);*/
		
		Intent resultIntent = new Intent(this, NoteDetailActivity.class);
		resultIntent.putExtra(NoteDetailFragment.ARG_ITEM_ID, "1");
		resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
		// The stack builder object will contain an artificial back stack for the
		// started Activity.
		// This ensures that navigating backward from the Activity leads out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		// Adds the back stack for the Intent (but not the Intent itself)
		/*stackBuilder.addParentStack(LoginActivity.class);*/
		stackBuilder.addParentStack(NoteDetailActivity.class);
		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent =
		        stackBuilder.getPendingIntent(
		            0,
		            PendingIntent.FLAG_UPDATE_CURRENT
		        );
		mBuilder.setContentIntent(resultPendingIntent);
		NotificationManager mNotificationManager =
		    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		// mId allows you to update the notification later on.
		mNotificationManager.notify(0, mBuilder.build());

	}
}
