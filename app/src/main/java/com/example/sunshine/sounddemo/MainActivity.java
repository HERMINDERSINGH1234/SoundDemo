package com.example.sunshine.sounddemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.media.AudioManager;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    AudioManager audioManager;
    MediaPlayer mplayer;

    public void playAudio(View view){

        mplayer.start();
    }

    public void pauseAudio(View view){
        mplayer.pause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //MediaPlayer mplayer=MediaPlayer.create(this,R.raw.daru);
        //MediaPlayer mplayer=MediaPlayer.create(this,R.raw.daimond);
         // ek baar mein koi ek hi aayega because if there are more than one  do dono Salla sound mix
        // ho gayega aur dono gaaana ek sath bajega , aur koi gaaan samajh mein hi nahi aaayega..
     //mplayer.start();
        mplayer=MediaPlayer.create(this,R.raw.daimond);

        audioManager=(AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVolume=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        SeekBar volumeControl= (SeekBar) findViewById(R.id.seekBar);

        volumeControl.setMax(maxVolume);
        volumeControl.setProgress(curVolume);

        volumeControl.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);

            }
        });

         final SeekBar scrubber=(SeekBar)findViewById(R.id.scrubber);

        scrubber.setMax(mplayer.getDuration());

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                scrubber.setProgress(mplayer.getCurrentPosition());
            }
        },0,1000);
        scrubber.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                mplayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }



}
