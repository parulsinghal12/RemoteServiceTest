package com.example.app1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    static final String TAG = "APP1 MyBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(TAG,"mServiceBroadcastReceiver == ");
        String string =  new String("com.example.lenovo.myapplication.DWNLD_STATUS_SUCCESSFUL");

        if (action.equals(string))
            Toast.makeText(context,"success from TestApp 1", Toast.LENGTH_SHORT).show();


    }
}
