package com.appsmor.kleemusik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSeekBar;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class PlayMusiknya extends AppCompatActivity {

    ImageButton playbtn;
    MediaPlayer mPlayer;
    TextView songName, songStartTime, songAllTime;
    AppCompatSeekBar songPrgs;
    static int onTime = 0, startTime = 0, endTime = 0;
    Handler hdlr = new Handler();


    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_musiknya);
        songName = findViewById(R.id.title_song);
        songName.setText("Android Rion Song");

        mPlayer = MediaPlayer.create(this, R.raw.mons);

        playbtn = findViewById(R.id.bt_play);

        songStartTime = findViewById(R.id.tv_song_current_duration);
        songAllTime = findViewById(R.id.tv_song_total_duration);
        songPrgs = findViewById(R.id.seek_song_progressbar);

        endTime = mPlayer.getDuration();
        startTime = mPlayer.getCurrentPosition();
        if (onTime == 0) {
            songPrgs.setMax(endTime);
            onTime = 1;

        }

        songAllTime.setText(String.format("%d:%d", TimeUnit.MILLISECONDS.toMinutes(endTime),
                TimeUnit.MILLISECONDS.toSeconds(endTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(endTime))));
        songStartTime.setText(String.format("%d:%d", TimeUnit.MILLISECONDS.toMinutes(startTime),
                TimeUnit.MILLISECONDS.toSeconds(startTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(startTime))));
        songPrgs.setProgress(startTime);
        hdlr.postDelayed(UpdateSongTime, 100);

        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mPlayer.isPlaying()) {
                    mPlayer.start();
                    playbtn.setImageResource(R.drawable.ic_baseline_stop_circle_24);

                } else {
                    mPlayer.pause();
                    playbtn.setImageResource(R.drawable.playic);

                }

            }
        });
    }

    private Runnable UpdateSongTime = new Runnable() {
        @SuppressLint("DefaultLocale")
        @Override
        public void run() {
            startTime = mPlayer.getCurrentPosition();
            songStartTime.setText(String.format("%d:%d", TimeUnit.MILLISECONDS.toMinutes(startTime),
                    TimeUnit.MILLISECONDS.toSeconds(startTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(startTime))));
            songPrgs.setProgress(startTime);
            hdlr.postDelayed(this, 100);
        }
    };

}