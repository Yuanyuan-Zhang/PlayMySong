package com.example.student.playmysong;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;


/**
 * Created by student on 3/20/15.
 */
public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "Boot Received", Toast.LENGTH_SHORT).show();
        Log.i("Jamie","hello");

        Intent myIntent=new Intent(context,MyService.class);
        context.startService(myIntent);

    }


}
