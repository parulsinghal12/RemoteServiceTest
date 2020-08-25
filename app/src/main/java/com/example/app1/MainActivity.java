package com.example.app1;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "MainActivity";
    static final String BR_ACTION = "com.example.lenovo.myapplication.DWNLD_STATUS_SUCCESSFUL";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Log.d(TAG,"myintentervice trying to start from App1");
                //start myintentervice
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.example.lenovo.myapplication",".services.backend.MyIntentService"));
                startService(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(serviceUpdateReceiver,new IntentFilter(BR_ACTION));

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(serviceUpdateReceiver);
    }

    MyBroadcastReceiver serviceUpdateReceiver = new MyBroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
            super.onReceive(context, intent);
            String action = intent.getAction();
            Log.d(TAG,"mServiceBroadcastReceiver == ");
            if (action.equals(BR_ACTION)) {
                Toast.makeText(MainActivity.this,"downloaded noti received in App 1",Toast.LENGTH_SHORT).show();
            }
        }
    };
}