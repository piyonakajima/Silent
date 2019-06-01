package com.edapiyo.silent;

import android.os.VibrationEffect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.util.Log;
import android.os.Vibrator;

import static android.os.VibrationEffect.DEFAULT_AMPLITUDE;


public class MainActivity extends AppCompatActivity{
    ToggleButton mToggleButton;
    boolean mChecked;
    String TAG="MainActivity";
    Vibrator mVibrator;
    float mBpm;
    int mVibLength,mTopVibLength;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        mTopVibLength = 150;
        mVibLength = 100;
        mToggleButton = findViewById(R.id.toggleButton);
        mToggleButton.setTextOn("PLAY");
        mToggleButton.setTextOff("STOP");
        mToggleButton.setChecked(false);

        mToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                Log.v(TAG,"onCheckedChanged " + isChecked);

                if (isChecked == true) {
                    //calculate BPM
                    mBpm = 100;
                    float bps = mBpm / 60;
                    long mspb = (long)(1/ bps * 1000);
                    Log.v(TAG,"BPM:" + mBpm +  ", bps:" + bps +", mspb:" + mspb );
                    //Vib
                     Log.v(TAG,"Vibrator is started");
                     long pattern[] = {mspb-mVibLength,mTopVibLength,
                             mspb-mTopVibLength,mVibLength,
                             mspb-mVibLength,mVibLength,
                             mspb-mVibLength,mVibLength
                             };
                     mVibrator.vibrate(pattern,0);

                } else if (isChecked == false) {
                    Log.v(TAG,"Vibrator is canceled");
                    mVibrator.cancel();
                }
            }
        });
        Log.v(TAG,"onCreate()");
    }
}
