package com.jumpingbeanapps.android.custombeatbox;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

class SoundAdapter extends RecyclerView.Adapter<SoundAdapter.SoundHolder> {

    private static final String TAG = "SoundAdapter";

    private List<Sound> sounds;
    private int[] colors;
    private Context context;

    //Color index
    private int currentCIndex;

    SoundAdapter(List<Sound> sounds, int[] colors, Activity context){
        this.sounds = sounds;
        this.colors = colors;
        this.context = context;

    }

     class SoundHolder extends RecyclerView.ViewHolder {

        private Sound sound;

        private ImageButton playSound;
        private TextView name;

        SoundHolder(View itemView) {
            super(itemView);
            //TODO: Add button state change when clicked
            playSound = (ImageButton) itemView.findViewById(R.id.imageButton_play_sound);
            playSound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sound.play();
                }
            });
            playSound.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View view){
                    FragmentManager fm =((Activity)context).getFragmentManager();
                    DialogFragment dialogFragment = SoundDialogFragment.newInstance();
                    dialogFragment.show(fm,"dialog");
                    return true;
                }
            });

            name = (TextView) itemView.findViewById(R.id.textview_sound_name);

        }

        void bind(Sound sound, int color){
            this.sound = sound;
            itemView.setBackgroundColor(color);
            name.setText(sound.getName());
        }

    }

    @Override
    public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.soundholder_detail_view, parent, false);

        return new SoundHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SoundHolder holder, int position) {
        if(position % colors.length == 0){
            currentCIndex = 0;
        }
        final int color = colors[currentCIndex];
        holder.bind(sounds.get(position), color);
        currentCIndex++;
    }

    @Override
    public int getItemCount() {
        return sounds.size();
    }
}
