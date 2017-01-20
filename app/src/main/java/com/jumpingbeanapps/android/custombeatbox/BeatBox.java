package com.jumpingbeanapps.android.custombeatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class BeatBox {
    private static final String TAG = "BeatBox";
    private static final String SAMPLE_FOLDER = "sample_sounds";
    private static final int MAX_SOUNDS = 5;
    private List<Sound> sounds = new ArrayList<>();
    private AssetManager assets;
    private String[] soundNames;
    private SoundPool soundPool;


    public BeatBox(Context context){
        assets = context.getAssets();
        soundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC,0);
        loadSampleSounds();
    }

    private void loadSampleSounds(){

        try{
            soundNames = assets.list(SAMPLE_FOLDER);

        }catch(IOException ioe){
            Log.e(TAG,"Could not find assets",ioe);
        }
        for(String filename : soundNames){
            try {
                String soundPath = SAMPLE_FOLDER + "/" + filename;
                Sound sound = new Sound(soundPath);
                load(sound);
                sounds.add(sound);
            }catch(IOException ioe){
                Log.e(TAG,"Could not load sound " + filename,ioe);
            }
        }

    }

    private void load(Sound sound)throws IOException{
        AssetFileDescriptor fileDescriptor = assets.openFd(sound.getSoundPath());
        int soundId = soundPool.load(fileDescriptor,1);
        sound.setSoundId(soundId);
    }

    public void play(Sound sound){
        Integer soundId = sound.getSoundId();
        if(soundId == null){
            return;
        }
        soundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    List<Sound> getSounds() {
        return sounds;
    }

    void setSounds(List<Sound> sounds) {
        this.sounds = sounds;
    }
}