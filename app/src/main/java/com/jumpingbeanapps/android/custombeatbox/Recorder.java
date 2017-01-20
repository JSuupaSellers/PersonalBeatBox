package com.jumpingbeanapps.android.custombeatbox;

import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;

public class Recorder extends AppCompatActivity{

    private static final String TAG = "AudioRecordTEST";
    private static final int REQUEST_RECORD_PERSMISSION = 200;
    private MediaRecorder recorder = null;
    private static String fileName = null;
    private boolean permissionToRecordAccepted = false;


    Recorder(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_RECORD_PERSMISSION:
                permissionToRecordAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if(!permissionToRecordAccepted) finish();
    }

    public void onRecord(boolean start){
        if(start){
            startRecording();
        }else{
            stopRecording();
        }
    }

    private void startRecording(){
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile(fileName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try{
            recorder.prepare();
        }catch(IOException e){
            Log.e(TAG,"start recording failed");
        }

        recorder.start();
    }

    private void stopRecording(){
        recorder.stop();
        recorder.release();
        recorder = null;
    }



}
