package com.example.student.playmysong;

import android.app.IntentService;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import java.io.IOException;

/**
 * Created by student on 3/16/15.
 */
public class MyService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyService() {
        super("name");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        MediaPlayer mPlayer;
        mPlayer = new MediaPlayer();
        Uri myUri = Uri.parse("file:///sdcard/mySongs/coldplay.mp3");
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        Log.i("JAMIE", "Trying sth");
        try {
            mPlayer.setDataSource(getApplicationContext(), myUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mPlayer.start();
    }
}
