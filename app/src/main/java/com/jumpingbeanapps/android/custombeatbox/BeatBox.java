package com.jumpingbeanapps.android.custombeatbox;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class BeatBox {
    private static final String TAG = "BeatBox";
    private static final String SAMPLE_FOLDER = "sample_sounds";
    private List<Sound> sounds = new ArrayList<>();
    private AssetManager assets;
    private String[] soundNames;

    public BeatBox(Context context){
        assets = context.getAssets();

        loadSampleSounds();
    }

    private void loadSampleSounds(){

        try{
            soundNames = assets.list(SAMPLE_FOLDER);

        }catch(IOException ioe){
            Log.e(TAG,"Could not find assets",ioe);
        }
        for(String filename : soundNames){
            String soundPath = SAMPLE_FOLDER + "/" + filename;
            Sound sound = new Sound(soundPath);
            sounds.add(sound);
        }

    }

    List<Sound> getSounds() {
        return sounds;
    }

    void setSounds(List<Sound> sounds) {
        this.sounds = sounds;
    }
}
