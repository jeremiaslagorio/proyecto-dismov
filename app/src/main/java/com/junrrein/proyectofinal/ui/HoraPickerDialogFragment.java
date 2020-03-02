package com.junrrein.proyectofinal.ui;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.time.LocalTime;

public class HoraPickerDialogFragment extends DialogFragment {

    private TimePickerDialog.OnTimeSetListener listener;

    public HoraPickerDialogFragment(TimePickerDialog.OnTimeSetListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LocalTime now = LocalTime.now();

        return new TimePickerDialog(requireActivity(),
                listener,
                now.getHour(),
                now.getMinute(),
                DateFormat.is24HourFormat(requireActivity()));
    }
}
