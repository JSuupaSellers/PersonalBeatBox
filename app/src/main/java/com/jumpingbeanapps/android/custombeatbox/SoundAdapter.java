package com.jumpingbeanapps.android.custombeatbox;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class SoundAdapter extends RecyclerView.Adapter<SoundAdapter.SoundHolder> {

    private static final String TAG = "SoundAdapter";

    private List<Sound> sounds;
    private int[] colors;
    private FragmentManager fm;

    private BeatBox beatBox;

    private boolean isDeleteOptionEnabled;

    SoundAdapter(List<Sound> sounds, int[] colors, FragmentManager fm, BeatBox beatBox) {
        this.sounds = sounds;
        this.colors = colors;
        this.fm = fm;
        this.beatBox = beatBox;

    }

    class SoundHolder extends RecyclerView.ViewHolder {

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
                    beatBox.play(sound);
                }
            });
            playSound.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    DialogFragment dialogFragment = SoundDialogFragment.newInstance(sounds);
                    dialogFragment.show(fm, "dialog");
                    return true;
                }
            });

            name = (TextView) itemView.findViewById(R.id.textview_sound_name);
            deleteBox = (CheckBox) itemView.findViewById(R.id.checkbox_delete_or_not);

        }

        void bind(Sound sound, int color) {

            if(deleteBox.isChecked()){
                sounds.remove(sound);
            }

            this.sound = sound;

            itemView.setBackgroundColor(color);
            setDeleteBoxVisible(isDeleteOptionEnabled);
            name.setText(sound.getName());
        }

        void setDeleteBoxVisible(boolean enableDeleteOption) {
            deleteBox.setVisibility(enableDeleteOption ? View.VISIBLE : View.GONE);
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
        final int currentCIndex = position % colors.length;
        final int color = colors[currentCIndex];
        holder.bind(sounds.get(position), color);
    }

    @Override
    public int getItemCount() {
        return sounds.size();
    }

    void setDeleteOptionEnabled(boolean isDeleteOptionEnabled) {
        this.isDeleteOptionEnabled = isDeleteOptionEnabled;
    }
}
