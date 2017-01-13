package com.jumpingbeanapps.android.custombeatbox;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

class SoundAdapter extends RecyclerView.Adapter<SoundAdapter.SoundHolder> {

    private List<Sound> sounds;

    SoundAdapter(List<Sound> sounds){
        this.sounds = sounds;
    }

    static class SoundHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        private Button button;
        private Sound sound;

        SoundHolder(View itemView) {
            super(itemView);
            button = (Button) itemView.findViewById(R.id.button_detail_view);

        }

        void bind(Sound sound){
            this.sound = sound;
            button.setText(sound.getName());
        }

        @Override
        public void onClick(View view) {
            sound.play();
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
        holder.bind(sounds.get(position));
    }

    @Override
    public int getItemCount() {
        return sounds.size();
    }
}
