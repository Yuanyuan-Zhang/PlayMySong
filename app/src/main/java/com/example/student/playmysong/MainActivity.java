package com.example.student.playmysong;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            File myFile = new File("/sdcard/mySongs"); //try to check if the file exist

            if(myFile.exists()){  //if exists, play the song directly
                Intent intent = new Intent(this, MyReceiver.class);
                sendBroadcast(intent);
            }

            else{         //else let the user to click the button play the song
                Intent intent = new Intent(this, MyService.class);
                startService(intent);

            }
        }catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }

    }

    private void copy2sdcard() throws IOException {
        final int songID = R.raw.coldplay;

        String path = Environment.getExternalStorageDirectory() + "/mySongs";
        File dir = new File(path);
        if (dir.mkdirs() || dir.isDirectory()) {
            path = path + "/coldplay.mp3";
        }
        InputStream in = getResources().openRawResource(songID);
        FileOutputStream out = new FileOutputStream(path);
        byte[] buff = new byte[1024];
        int read = 0;
        try {
            while ((read = in.read(buff)) > 0) {
                out.write(buff, 0, read);
            }
        } finally {
            in.close();
            out.close();
        }
    }
    public void playSong(View v) throws IOException {
        copy2sdcard();
        Intent i = new Intent(this, MyService.class);
        startService(i);

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
}
