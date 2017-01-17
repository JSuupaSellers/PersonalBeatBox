package com.jumpingbeanapps.android.custombeatbox;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class SoundAdapter extends RecyclerView.Adapter<SoundAdapter.SoundHolder> {

    private static final String TAG = "SoundAdapter";

    private List<Sound> sounds;
    private int[] colors;
    private FragmentManager fm;

    private List<SoundHolder> soundHolders;

    //Color index
    private int currentCIndex;

    SoundAdapter(List<Sound> sounds, int[] colors, FragmentManager fm) {
        this.sounds = sounds;
        this.colors = colors;
        this.fm = fm;
        soundHolders = new ArrayList<>();

    }

    class SoundHolder extends RecyclerView.ViewHolder {

        private boolean isOnDelete;

        private Sound sound;

        private ImageButton playSound;
        private TextView name;
        private CheckBox deleteBox;

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
            playSound.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    DialogFragment dialogFragment = SoundDialogFragment.newInstance();
                    dialogFragment.show(fm, "dialog");
                    return true;
                }
            });

            name = (TextView) itemView.findViewById(R.id.textview_sound_name);
            deleteBox = (CheckBox) itemView.findViewById(R.id.checkbox_delete_or_not);
            deleteBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    isOnDelete = b;
                }
            });


        }

        void bind(Sound sound, int color) {
            this.sound = sound;
            itemView.setBackgroundColor(color);
            name.setText(sound.getName());
        }

        void setDeleteBoxVisible(boolean visible) {
            deleteBox.setVisibility(visible ? View.VISIBLE : View.GONE);
        }

        boolean isOnDelete() {
            return isOnDelete;
        }

        CheckBox getDeleteBox(){
            return deleteBox;
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
        if (position % colors.length == 0) {
            currentCIndex = 0;
        }

        //TODO: Fix color reassignment on list resize
        final int color = colors[currentCIndex];

        //Making sure that the references to all SoundHolders are up-to-date
        if (soundHolders.size() - 1 >= position) {

            if (soundHolders.get(position) != null) {
                soundHolders.remove(position);
            }
        }

        soundHolders.add(position, holder);
        holder.bind(sounds.get(position), color);
        currentCIndex++;
    }

    @Override
    public int getItemCount() {
        return sounds.size();
    }

    List<SoundHolder> getSoundHolders() {
        return soundHolders;
    }
}
