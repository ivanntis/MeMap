package com.gizmoideas.mobile.memap.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StartServiceReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {		
		//PositionReceiver  position  =new PositionReceiver();
		//PositionReceiver  position  =new PositionReceiver(context);
		if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
        {
			//position.setPosition(context);
			//position.setPosition();
			PositionReceiver  position  =new PositionReceiver(context);
        }
	}

}
