package com.example.ch0chip.myapplication;

import android.app.Activity;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;


public class MainActivity extends Activity implements View.OnClickListener {
    MediaPlayer mp = null;
    Button play_button;

    //https://www.dropbox.com/s/0w9uxoovxgbj485/%E3%81%8B%E3%82%8F%E3%82%8B%E3%81%BE%E3%81%A1.mp3
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        mp = MediaPlayer.create(this, R.raw.system7);
        //mp = MediaPlayer.create(this, Uri.parse("https://www.dropbox.com/s/0w9uxoovxgbj485/%E3%81%8B%E3%82%8F%E3%82%8B%E3%81%BE%E3%81%A1.mp3"));

        play_button = (Button) findViewById(R.id.PlayButton);
        play_button.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (mp.isPlaying()) { //再生中
            play_button.setText("Music Start");
            mp.stop();
            try {
                mp.prepare();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else { //停止中
            play_button.setText("Music Stop");
            mp.start();
            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
            String url;
            url = Settings.System.DEFAULT_NOTIFICATION_URI.toString();
            Uri ringtoneUri = Uri.parse(url);
            mmr.setDataSource(this, ringtoneUri);
            //String filePath = Environment.getExternalStorageDirectory().getPath() + "/sample.mp3";
            TextView text =  (TextView) findViewById(R.id.TextView);
            text.setText(mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST));
        }
    }
}