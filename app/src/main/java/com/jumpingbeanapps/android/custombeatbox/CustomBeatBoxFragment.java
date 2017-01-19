package com.jumpingbeanapps.android.custombeatbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class CustomBeatBoxFragment extends Fragment {

    //TODO: Add a new sound icon (offer select/record option)
    //TODO: Customize theme and colors option

    private boolean isOnDelete;

    private BeatBox beatBox;

    private RecyclerView rv;
    private SoundAdapter soundAdapter;

    public static CustomBeatBoxFragment newInstance() {
        return new CustomBeatBoxFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);

        beatBox = new BeatBox(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_custom_beatbox, container, false);


        //<Test code> Will be removed soon
//        for (int i = 0; i <= 4; i++) {
//
//            Sound sound = new Sound();
//            String name = "";
//            for (int j = 0; j <= i; j++) {
//                name += "aa";
//            }
//            sound.setName(name);
//            beatBox.getSounds().add(sound);
//
//        }
        //</Test code>

        rv = (RecyclerView) root.findViewById(R.id.recycler_view_custom_beatbox);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        final int[] colors = getContext().getResources().getIntArray(R.array.colors);
        soundAdapter = new SoundAdapter(beatBox.getSounds(), colors, getFragmentManager(),beatBox);
        rv.setAdapter(soundAdapter);

        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_custombeatbox, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.delete_menu_item:

                if (!isOnDelete) {
                    isOnDelete = true;

                    //Make checkboxes visible
                    for (SoundAdapter.SoundHolder holder :
                            soundAdapter.getSoundHolders()) {
                        holder.setDeleteBoxVisible(true);
                    }
                    //TODO: Implement "Select All" feature

                } else {

                    for (SoundAdapter.SoundHolder holder :
                            soundAdapter.getSoundHolders()) {

                        holder.setDeleteBoxVisible(false);

                        if (holder.isOnDelete()) {

                            holder.getDeleteBox().setChecked(false);
                            beatBox.getSounds().remove(holder.getAdapterPosition());
                        }

                    }

                    isOnDelete = false;
                }

                soundAdapter.notifyDataSetChanged();

                return true;
            //Instead of recording in the dialog fragment, id rather have a fragment
            //dedicated to adding and deleting sounds.
            //TODO: Implement sounds controller fragment to add and delete user sounds
            case R.id.add_sound_menu_item:

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
