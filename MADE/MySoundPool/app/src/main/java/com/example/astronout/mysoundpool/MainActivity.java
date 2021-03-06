package com.example.astronout.mysoundpool;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.btn_soundpool)
    Button btnSound;
    @BindView(R.id.btn_mediaplayer)
    Button btnMedia;
    @BindView(R.id.btn_mediaplayer_stop)
    Button btnMediaStop;
    SoundPool sp ;
    int wav;
    boolean spLoaded = false;

    Intent it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        btnSound.setOnClickListener(this);
        btnMedia.setOnClickListener(this);
        btnMediaStop.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sp = new SoundPool.Builder()
                    .setMaxStreams(10)
                    .build();
        } else {
            sp = new SoundPool(10, AudioManager.STREAM_MUSIC, 1);
        }

        sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            public void onLoadComplete(SoundPool soundPool, int sampleId,int status) {
                //Log.d("Load","true");
                spLoaded = true;
            }
        });

        wav = sp.load(this, R.raw.horse, 1);

        it = new Intent(this, MediaService.class);
        it.setAction(MediaService.ACTION_CREATE);
        it.setPackage(MediaService.ACTION_PACKAGE);
        startService(it);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_soundpool:
                if (spLoaded){
                    sp.play(wav, 1, 1, 0, 0, 1);
                }
                break;
            case R.id.btn_mediaplayer:
                it.setAction(MediaService.ACTION_PLAY);
                it.setPackage(MediaService.ACTION_PACKAGE);
                startService(it);

                break;
            case R.id.btn_mediaplayer_stop:
                it.setAction(MediaService.ACTION_STOP);
                it.setPackage(MediaService.ACTION_PACKAGE);
                startService(it);
                break;

            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(it);
    }
}
