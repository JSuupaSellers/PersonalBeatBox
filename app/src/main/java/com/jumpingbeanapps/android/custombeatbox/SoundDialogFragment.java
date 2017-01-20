package com.jumpingbeanapps.android.custombeatbox;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class SoundDialogFragment extends DialogFragment {

    private static final int REQUEST_RECORD_PERSMISSION = 200;
    private String[] permissions = {Manifest.permission.RECORD_AUDIO};

    private Button recordButton;
    private Recorder recorder;
    private boolean startRecording = true;
    private static String fileName = null;

    public static SoundDialogFragment newInstance(List<Sound> sounds){
        SoundDialogFragment frag = new SoundDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable("sounds", (ArrayList<Sound>) sounds);
        frag.setArguments(args);
        return frag;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        fileName = getContext().getCacheDir().getAbsolutePath();
        fileName += "/audiorecordtest.3gp";
        recorder = new Recorder(fileName);
        ActivityCompat.requestPermissions((Activity) getContext(), permissions, REQUEST_RECORD_PERSMISSION);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_sound_dialog,container);
    }
    //TODO: Inner Detail View for Selecting a sound to add.
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        ListView lv = (ListView) view.findViewById(R.id.sound_list_view);

        //Test Code
        ArrayList<String> myStringArray = new ArrayList<>();
        ArrayList<Sound> soundList = (ArrayList<Sound>) getArguments().getSerializable("sounds");

        for(Sound sound : soundList){
            myStringArray.add(sound.getName());
        }

        recordButton = (Button) view.findViewById(R.id.record_button);
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recorder.onRecord(startRecording);
                if(startRecording){
                    recordButton.setText("Recording");
                }else{
                    recordButton.setText("Record");
                }
                startRecording = !startRecording;
            }
        });
        //Test Code

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.simple_list_item, myStringArray);
        lv.setAdapter(adapter);
    }

}
