package com.jumpingbeanapps.android.custombeatbox;

import java.util.ArrayList;
import java.util.List;

class BeatBox {

    private List<Sound> sounds;

    BeatBox(){
        sounds = new ArrayList<>();
    }

    List<Sound> getSounds() {
        return sounds;
    }

    void setSounds(List<Sound> sounds) {
        this.sounds = sounds;
    }
}
