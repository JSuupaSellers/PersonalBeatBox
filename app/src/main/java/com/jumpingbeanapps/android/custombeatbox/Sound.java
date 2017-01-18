package com.jumpingbeanapps.android.custombeatbox;

import android.os.Parcelable;

import java.io.Serializable;

public class Sound implements Serializable {

    private String soundPath;
    private String name;


    public Sound(String soundPath){
        this.soundPath = soundPath;
        String[] components = soundPath.split("/");
        String filename = components[components.length - 1];
        name = filename.replace(".WAV","");
    }

    public String getSoundPath(){
        return soundPath;
    }

    public String getName(){
        return name;
    }
    public void setName(String s){
        name = s;
    }

    public void play(){

    }

}
