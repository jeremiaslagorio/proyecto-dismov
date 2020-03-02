package com.junrrein.proyectofinal.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.time.LocalDate;

public class FechaPickerFragment extends DialogFragment {

    private DatePickerDialog.OnDateSetListener listener;

    FechaPickerFragment(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LocalDate today = LocalDate.now();

        return new DatePickerDialog(requireActivity(),
                listener,
                today.getYear(),
                today.getMonthValue() - 1,
                today.getDayOfMonth());
    }
}
