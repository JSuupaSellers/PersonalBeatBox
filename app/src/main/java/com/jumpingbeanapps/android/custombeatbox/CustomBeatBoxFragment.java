package com.jumpingbeanapps.android.custombeatbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class CustomBeatBoxFragment extends Fragment {

    //TODO: Add a new sound icon (offer select/record option)
    //TODO: Customize theme and colors option

    private BeatBox beatBox;

    private RecyclerView rv;
    private SoundAdapter soundAdapter;

    public static CustomBeatBoxFragment newInstance(){
        return new CustomBeatBoxFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_custom_beatbox, container, false);

        beatBox = new BeatBox();

        //<Test code> Will be removed soon
        for(int i = 0; i <= 4; i++){

            Sound sound = new Sound();
            String name = "";
            for(int j = 0; j <= i; j++){
                name += "aa";
            }
            sound.setName(name);
            beatBox.getSounds().add(sound);

        }
        //</Test code>

        rv = (RecyclerView) root.findViewById(R.id.recycler_view_custom_beatbox);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        final int[] colors = getContext().getResources().getIntArray(R.array.colors);
        soundAdapter = new SoundAdapter(beatBox.getSounds(), colors, getFragmentManager());
        rv.setAdapter(soundAdapter);

        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_custombeatbox, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){

            case R.id.delete_menu_item:

                //Update SoundHolder checkbox visibility
                for(SoundAdapter.SoundHolder holder:
                        soundAdapter.getSoundHolders()){
                    holder.setDeleteBoxVisible(true);
                }
                soundAdapter.notifyDataSetChanged();
                getActivity().invalidateOptionsMenu();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
