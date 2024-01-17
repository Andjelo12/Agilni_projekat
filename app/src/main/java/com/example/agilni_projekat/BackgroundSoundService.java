package com.example.agilni_projekat;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;
import androidx.annotation.Nullable;
public class BackgroundSoundService extends Service {
    int length;
    MediaPlayer mediaPlayer,mediaPlayer2, mediaPlayer3;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.game_music);
        mediaPlayer.setLooping(true); // Set looping
        mediaPlayer.setVolume(100, 100);
        mediaPlayer2 = MediaPlayer.create(this, R.raw.game_playing_music);
        mediaPlayer2.setLooping(true); // Set looping
        mediaPlayer2.setVolume(100, 100);
        mediaPlayer3 = MediaPlayer.create(this, R.raw.congratulations_effect);
        mediaPlayer3.setLooping(true); // Set looping
        mediaPlayer3.setVolume(100, 100);
    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        String TrackNo=intent.getStringExtra("track");
        if (TrackNo.equals("1"))
            mediaPlayer.start();
        else if (TrackNo.equals("2"))
            mediaPlayer2.start();
        else {
            mediaPlayer3.start();
        }
        return startId;
    }
    public void onStart(Intent intent, int startId) {
    }
    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer2.stop();
        mediaPlayer2.release();
        mediaPlayer3.stop();
        mediaPlayer3.release();
    }
    @Override
    public void onLowMemory() {
    }
}