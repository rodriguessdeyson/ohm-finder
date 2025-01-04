package com.rad.services.observables;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.Serializable;

public class ResistorHistoryViewModel extends ViewModel implements Serializable {
    private MutableLiveData<Integer> resistorHistorySie = new MutableLiveData<Integer>();

    public MutableLiveData<Integer> getCurrentSize() {
        if (resistorHistorySie == null) { resistorHistorySie = new MutableLiveData<Integer>(); }
        return resistorHistorySie;
    }
}
