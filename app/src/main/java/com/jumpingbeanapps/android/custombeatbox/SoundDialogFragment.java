package com.jumpingbeanapps.android.custombeatbox;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Meg on 1/13/17.
 */

public class SoundDialogFragment extends DialogFragment {

    public static SoundDialogFragment newInstance(){
        return new SoundDialogFragment();
    }
    //TODO: Inner Detail View for Selecting a sound to add.
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragment_sound_dialog, container, false);
        return root;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(R.layout.fragment_sound_dialog);
        builder.setTitle("Pick A Sound...");
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dismiss();
            }
        });
        return builder.create();
    }
}
