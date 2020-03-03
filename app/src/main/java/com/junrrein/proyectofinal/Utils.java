package com.junrrein.proyectofinal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

public class Utils {

    static public <T> void observarUnaSolaVez(LiveData<T> liveData,
                                              Observer<T> observer) {
        liveData.observeForever(new Observer<T>() {
            @Override
            public void onChanged(T t) {
                liveData.removeObserver(this);
                observer.onChanged(t);
            }
        });
    }
}
